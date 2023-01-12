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

-- changeset Shkril:2

CREATE TABLE ads
(
    id          INTEGER NOT NULL,
    description VARCHAR(255),
    image       VARCHAR(255),
    price       INTEGER,
    title       VARCHAR(255),
    user_id     INTEGER,
    CONSTRAINT pk_ads PRIMARY KEY (id)
);

ALTER TABLE ads
    ADD CONSTRAINT FK_ADS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Shkril:3

CREATE TABLE ads_comment
(
    id         INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    text       VARCHAR(255),
    ads_id     INTEGER,
    user_id    INTEGER,
    CONSTRAINT pk_adscomment PRIMARY KEY (id)
);

ALTER TABLE ads_comment
    ADD CONSTRAINT FK_ADSCOMMENT_ON_ADS FOREIGN KEY (ads_id) REFERENCES ads (id);

ALTER TABLE ads_comment
    ADD CONSTRAINT FK_ADSCOMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset golenevav:4

create sequence ads_id_seq;

create sequence ads_comment_id_seq;

create sequence user_id_seq;

-- changeset shilovkirill:5
CREATE TABLE ads_photo
(
    ads_id     BIGINT NOT NULL,
    file_path  VARCHAR(255),
    file_size  BIGINT NOT NULL,
    media_type VARCHAR(255),
    data       BYTEA,
    CONSTRAINT pk_adsphoto PRIMARY KEY (ads_id)
);

ALTER TABLE ads_photo
    ADD CONSTRAINT FK_ADSPHOTO_ON_ADS FOREIGN KEY (ads_id) REFERENCES ads (id);