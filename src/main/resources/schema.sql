CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR (300) NOT NULL DEFAULT '',
    last_name VARCHAR (300) NOT NULL DEFAULT '',
    email VARCHAR (300) NOT NULL UNIQUE,
    age INT NOT NULL,
    address VARCHAR (300) NOT NULL DEFAULT '',
    joined_date DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Answer options are managed by the AnswerOption enum in Java.
-- To add a 5th option:
--   1. Add FIFTH(5) to the AnswerOption enum
--   2. Add a "fifth_option VARCHAR(300) NOT NULL" column here
--   3. Add option5 field to Question model class
--   4. Update getOptionText() switch statement in Question class
--   5. Update the CHECK constraint below to include 5
--   6. Update Question entity getters/setters
CREATE TABLE questions (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    question_title VARCHAR (500) NOT NULL,
    first_option VARCHAR (300) NOT NULL,
    second_option VARCHAR (300) NOT NULL,
    third_option VARCHAR (300) NOT NULL,
    fourth_option VARCHAR (300) NOT NULL
);

-- Validation of selected_option is primarily handled by AnswerOption enum in Java.
-- This CHECK constraint ensures database integrity. When adding options:
--   UPDATE the CHECK constraint to match AnswerOption enum (e.g., BETWEEN 1 AND 5 for 5 options)
CREATE TABLE user_answers (
    answer_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    question_id INT NOT NULL,
    selected_option INT NOT NULL CHECK (selected_option BETWEEN 1 AND 4),
    CONSTRAINT fk_answer_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES questions(question_id) ON DELETE CASCADE,
    CONSTRAINT unique_user_question UNIQUE (user_id, question_id)
);
