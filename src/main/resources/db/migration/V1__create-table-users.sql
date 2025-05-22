CREATE TABLE tb_users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(65) NOT NULL,
    document VARCHAR(25) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(75) NOT NULL,
    balance NUMERIC(10, 2) NOT NULL,
    user_type VARCHAR(20) NOT NULL
);
