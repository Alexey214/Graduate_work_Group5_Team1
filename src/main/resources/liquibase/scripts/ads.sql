-- liquibase formatted sql

-- changeset Shkril:1

CREATE TABLE IF NOT EXISTS ads
(
    id          BIGSERIAL PRIMARY KEY,
    description text NOT NULL
    image       bytea NOT NULL,
    price       int  NOT NULL,
    title       varchar,
    user_id     integer REFERENCES users (id)
    );