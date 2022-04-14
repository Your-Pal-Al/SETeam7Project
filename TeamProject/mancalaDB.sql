DROP TABLE users;

CREATE TABLE users(
    username varchar(30),
    password varbinary(16));

ALTER TABLE users
    add constraint users_username_pk primary key(username);