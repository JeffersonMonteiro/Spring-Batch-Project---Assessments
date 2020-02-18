DROP TABLE tbUsers IF EXISTS;

CREATE TABLE tbUsers (
    user_id BIGINT NOT NULL PRIMARY KEY,
    user_name VARCHAR(30),
    dept VARCHAR(20),
    account DECIMAL
);

DROP TABLE tbReadUsers IF EXISTS;

CREATE TABLE tbReadUsers(
    user_id BIGINT NOT NULL PRIMARY KEY,
    user_name VARCHAR(30),
    dept VARCHAR(20),
    account DECIMAL
);

INSERT INTO tbReadUsers (user_id, user_name, dept, account) VALUES (1, 'Rey', '13', 228), (2, 'Han', '93', 742), (3, 'Leia', '57', 584);