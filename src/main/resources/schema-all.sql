DROP TABLE tbUsers EXISTS;

CREATE TABLE tbUser (
    user_id BIGINT NOT NULL PRIMARY KEY,
    user_name VARCHAR(30),
    dept VARCHAR(20),
    account DECIMAL
);