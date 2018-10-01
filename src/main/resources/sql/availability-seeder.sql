USE capstone_db;

INSERT INTO user (birthdate, email, gender, password, username)
VALUES
  ('19880201', 'graham@graham.com', 'm', 'password', 'Graham'),
  ('20170808', 'jolene@jojo.com', 'f', 'password', 'Jolene');

INSERT INTO availability (day_of_week, time_start, time_end, week, year, user_id)
VALUES
  ('Wednesday', '7', '12', '50', '2018', 1);