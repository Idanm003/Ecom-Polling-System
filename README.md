# Polling System

- A Spring Boot REST API studies project representing a company poll system. Registered users answer multiple-choice (4 options) questions, and the company can query statistics on the answers. Built as a single backend service with an in-memory H2 database, using a layered architecture (controller → service → repository) with JDBC, row mappers and request/response objects.
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

| Method | Path                 | Description                                       |
|--------|----------------------|---------------------------------------------------|
| GET    | `/users/{id}`        | Get a single user                                 |
| GET    | `/users/all`         | Get every user                                    |
| POST   | `/users/create`      | Create a user and get the new id back             |
| PUT    | `/users/update`      | Update a user (put the `userId` in the body)      |
| DELETE | `/users/delete/{id}` | Delete a user **along with all of their answers** |

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

| Method | Path                     | Description                                          |
|--------|--------------------------|------------------------------------------------------|
| GET    | `/questions/{id}`        | Get a single question                                |
| GET    | `/questions/all`         | Get every question                                   |
| POST   | `/questions/create`      | Create a question and get the new id back            |
| PUT    | `/questions/update`      | Update a question (put the `questionId` in the body) |
| DELETE | `/questions/delete/{id}` | Delete a question and its answers                    |

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

| Method | Path                                           | Description                                                                |
|--------|------------------------------------------------|----------------------------------------------------------------------------|
| POST   | `/answers/submit`                              | Submit an answer (registered users only, and just one answer per question) |
| GET    | `/answers/question/{questionId}/options-count` | How many users picked each option                                          |
| GET    | `/answers/question/{questionId}/count`         | How many users answered the question in total                              |
| GET    | `/answers/user/{userId}`                       | What the user answered for each question they've submitted                 |
| GET    | `/answers/user/{userId}/count`                 | How many questions the user has answered                                   |
| GET    | `/answers/all/options-count`                   | Every question, with the option counts for each                            |

```json
{ 
  "userId": 1, 
  "questionId": 2, 
  "selectedOption": 4
}
```
`selectedOption` has to be between 1 and 4.

## Error Handling

Error handling is simple. The service layer throws a `Exception` with a readable message whenever something is off (missing fields, unknown ids, a duplicate email, an answer that's already been submitted). Each controller wraps its calls in a try/catch and hands that message back as plain text with an `Error: ` prefix, for example:

- `Error: User with id 99 not found - only registered users can submit answers`
- `Error: Selected option must be between 1 and 4`

The one exception to the try/catch pattern is `GET /users/{id}` and `GET /questions/{id}`. The service returns `null` for an unknown id, so the controller checks for that itself and replies with something like `Error: User with id 1 not found`. `GET /users/all`, `GET /questions/all` and `GET /answers/all/options-count` don't catch anything.

## Database

The tables are defined in `schema.sql` and filled with starter data from `data.sql`.

- `users` – user_id, first_name, last_name, email (unique), age, address, joined_date
- `questions` – question_id, question_title, first_option … fourth_option
- `user_answers` – answer_id, user_id (FK), question_id (FK), selected_option. Each (user_id, question_id) pair is unique, and rows cascade on delete

## Project Structure

```
src/main/java/PollingSystem/
├── PollingSystemApplication.java
├── config/                          # PollTablesConfig (table names from application.yaml)
├── controller/{user,questions,answers}/
├── service/{user,questions,answers}/
├── repository/{user,questions,answers}/
├── mapper/{user,questions,answers}/
├── model/{user,questions,answers}/
├── requests/answers/                # AnswerRequest
├── responses/answers/               # UserAnswersResponse, AnswerDetailsResponse
└── responses/questions/             # QuestionResultsResponse
```
