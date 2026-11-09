# Polling System

A Spring Boot REST API project representing a company poll system. Registered users answer multiple-choice questions (currently 4 options, managed by an `AnswerOption` enum for easy extension), and the company can query statistics on the answers. Built as a single backend service with an in-memory H2 database, using a layered architecture (controller → service → repository) with JDBC, row mappers and request/response objects.
## Tech Stack

- Java 17
- Spring Boot 4.1.1 (Web MVC, JDBC)
- H2 in-memory database (in MySQL compatibility mode), with the H2 console turned on
- Gradle (the wrapper is included, so no install needed)

## Getting Started

```
./gradlew bootRun
```

The app comes up on `http://localhost:8080`. If you want to poke around the database, the H2 console lives at `/h2-console` (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, empty password).

## API Endpoints

### Users – `/users`

| Method | Path                | Description                                       |
|--------|---------------------|---------------------------------------------------|
| GET    | `/user/{id}`        | Get a single user                                 |
| GET    | `/user/all`         | Get every user                                    |
| POST   | `/user/create`      | Create a user and get the new id back             |
| PUT    | `/user/update`      | Update a user (put the `userId` in the body)      |
| DELETE | `/user/delete/{id}` | Delete a user **along with all of their answers** |

```json
{ 
  "firstName": "Avi",
  "lastName": "Ron", 
  "email": "avi@example.com", 
  "age": 30, "address": "Ramat Gan", 
  "joinedDate": "2024-05-01"
}
```
If you leave out `joinedDate`, it defaults to today.

### Questions – `/questions`

| Method | Path                    | Description                                          |
|--------|-------------------------|------------------------------------------------------|
| GET    | `/question/{id}`        | Get a single question                                |
| GET    | `/question/all`         | Get every question                                   |
| POST   | `/question/create`      | Create a question and get the new id back            |
| PUT    | `/question/update`      | Update a question (put the `questionId` in the body) |
| DELETE | `/question/delete/{id}` | Delete a question and its answers                    |

```json
{ 
  "questionTitle": "Where is your preferred place to travel?", 
  "option1": "USA", 
  "option2": "France", 
  "option3": "South America", 
  "option4": "Thailand"
}
```

### Answers – `/answers`

| Method | Path                                          | Description                                                                |
|--------|-----------------------------------------------|----------------------------------------------------------------------------|
| POST   | `/answer/submit`                              | Submit an answer (registered users only, and just one answer per question) |
| GET    | `/answer/question/{questionId}/options-count` | How many users picked each option                                          |
| GET    | `/answer/question/{questionId}/count`         | How many users answered the question in total                              |
| GET    | `/answer/user/{userId}`                       | What the user answered for each question they've submitted                 |
| GET    | `/answer/user/{userId}/count`                 | How many questions the user has answered                                   |
| GET    | `/answer/all/options-count`                   | Every question, with the option counts for each                            |

```json
{ 
  "userId": 1, 
  "questionId": 2, 
  "selectedOption": 4
}
```

**Answer Options:** The number of allowed answer options (currently 4) is managed by the `AnswerOption` enum in `model/answer/AnswerOption.java`. This makes it easy to extend to a 5th option:
1. Add `FIFTH(5)` to the enum
2. Add a `fifth_option` column to the `questions` table in `schema.sql`
3. Add the `option5` field to the `Question` model class
4. Update the Question's `getOptionText()` method
5. Update the CHECK constraint in `schema.sql` to `BETWEEN 1 AND 5`
6. The service layer validation and iteration logic will automatically use the new enum value

`selectedOption` must be one of the valid `AnswerOption` values (currently 1–4).

## Error Handling

Error handling is straightforward. The service layer throws an `Exception` with a readable message whenever something is wrong (missing fields, unknown ids, invalid answer options, duplicate email, answer already submitted). Each controller wraps its calls in a try/catch and returns the message as plain text with an `Error: ` prefix, for example:

- `Error: User with id 99 not found - only registered users can submit answers`
- `Error: Selected option must be one of: [FIRST, SECOND, THIRD, FOURTH]`
- `Error: User with id 1 already answered question with id 2`

**Note on validation:** Answer option validation uses the `AnswerOption.isValid()` method, which checks against the enum values. This ensures consistency across the codebase and makes adding new options straightforward (just add to the enum).

The exception to the try/catch pattern is `GET /users/{id}` and `GET /questions/{id}`. The service returns `null` for an unknown id, so the controller checks for that itself and replies with something like `Error: User with id 1 not found`. `GET /users/all`, `GET /questions/all` and `GET /answers/all/options-count` don't catch anything.

## Architecture & Design

### Layered Architecture
- **Controllers** – HTTP endpoints, request/response handling, basic error catching
- **Services** – Business logic, validation, enum-driven option handling
- **Repositories** – Data access layer with JDBC and row mappers
- **Mappers** – Convert ResultSet rows to model objects
- **Models** – POJO entities and value objects (Question, Answer, User, etc.)

### Answer Options as an Enum
The `AnswerOption` enum (`model/answer/AnswerOption.java`) is the single source of truth for valid answer values. This design:
- Eliminates hard-coded "1–4" checks scattered throughout the code
- Makes option count validation automatic via `AnswerOption.isValid()`
- Allows service layer iteration to use `AnswerOption.values()` instead of hard-coded loops
- Makes extending to 5+ options a straightforward enum edit with minimal ripple effects

## Database

The tables are defined in `schema.sql` and filled with starter data from `data.sql`.

- `users` – user_id, first_name, last_name, email (unique), age, address, joined_date
- `questions` – question_id, question_title, first_option … fourth_option (4 options managed by AnswerOption enum)
- `user_answers` – answer_id, user_id (FK), question_id (FK), selected_option (validates via AnswerOption enum). Each (user_id, question_id) pair is unique, and rows cascade on delete

**Adding a 5th Option:** When ready, add `FIFTH(5)` to the `AnswerOption` enum. The service layer will automatically iterate over 5 options in `countOptions()` and validate against all 5 in `createAnswer()`. Update the schema CHECK constraint from `BETWEEN 1 AND 4` to `BETWEEN 1 AND 5`.

## Project Structure

```
src/main/java/PollingSystem/
├── PollingSystemApplication.java
├── config/                          # PollTablesConfig (table names from application.yaml)
├── controller/{user,question,answer}/
├── service/{user,question,answer}/
├── repository/{user,question,answer}/
├── mapper/{user,question,answer}/
├── model/{user,question,answer}/
│   └── answer/AnswerOption.java     # Enum: FIRST(1), SECOND(2), THIRD(3), FOURTH(4)
├── requests/answer/                 # AnswerRequest
├── responses/answer/                # UserAnswersResponse, AnswerDetailsResponse
├── responses/question/              # QuestionResultsResponse
├── exceptions/                      # GlobalExceptionHandler, NotFoundException
└── mappers/                         # AnswerMapper, QuestionMapper, UserMapper
```
