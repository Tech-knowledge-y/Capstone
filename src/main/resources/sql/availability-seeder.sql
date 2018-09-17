USE capstone_db;

INSERT INTO user (birthdate, email, gender, password, username)
    VALUES
      ('19880201', 'graham@graham.com', 'm', 'password', 'graham');

INSERT INTO availability (day_of_week, time_end, time_start, week, year)
    VALUES
      ('Wednesday', 7, 12, 35, 2018, 1);