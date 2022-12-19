-- liquibase formatted sql

-- changeset Shkril:1

CREATE TABLE users
(
    id         INTEGER NOT NULL PRIMARY KEY,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    phone      VARCHAR(255),
    role_enum  INTEGER

);