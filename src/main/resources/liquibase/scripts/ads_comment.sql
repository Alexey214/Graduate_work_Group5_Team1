-- liquibase formatted sql

-- changeset Shkril:1

CREATE TABLE IF NOT EXISTS ads_comment
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    text       TEXT         NOT NULL,
    user_id    BIGSERIAL PRIMARY KEY,
    ads_id     BIGSERIAL PRIMARY KEY
);