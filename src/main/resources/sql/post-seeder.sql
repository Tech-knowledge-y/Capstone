USE capstone_db;

INSERT INTO user (birthdate, email, gender, password, username)
VALUES
  ('19880201', 'graham@graham.com', 'm', 'password', 'graham');

INSERT INTO posts (date, latitude, longitude, title, user_id)
VALUES
  ('20180917', '45.2', '123.5', 'post_title', 1);