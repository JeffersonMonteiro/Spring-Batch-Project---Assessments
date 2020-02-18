DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    userId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    userName VARCHAR(20),
    dept VARCHAR(20),
    account VARCHAR(20)
);