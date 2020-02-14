CREATE TABLE users  (
    userId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(20),
    dept VARCHAR(20),
    account DECIMAL
);