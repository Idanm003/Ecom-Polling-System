INSERT INTO users (first_name, last_name, email, age, address, joined_date)
VALUES ('Idan', 'Magen', 'idan@example.com', 28, 'Tel Aviv', '2023-01-15'),
       ('Dana', 'Shahar', 'dana@example.com', 34, 'Haifa', '2022-06-01'),
       ('Tom', 'Cohen', 'tom@example.com', 22, 'Jerusalem', '2024-03-20'),
       ('Nadia', 'Levi', 'nadia@example.com', 41, 'Eilat', '2021-11-05');

INSERT INTO questions (question_title, first_option, second_option, third_option, fourth_option)
VALUES ('Between the following, what do you most love to do?', 'Watch TV', 'Play the computer', 'Hanging out with friends', 'Travel the world'),
       ('Where is your preferred place to travel?', 'USA', 'France', 'South America', 'Thailand'),
       ('How often do you shop online?', 'Daily', 'Weekly', 'Monthly', 'Rarely');

INSERT INTO user_answers (user_id, question_id, selected_option)
VALUES (1, 1, 4),
       (1, 2, 4),
       (2, 1, 3),
       (2, 2, 2),
       (3, 1, 2),
       (4, 2, 4);
