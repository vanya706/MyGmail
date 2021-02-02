CREATE TABLE IF NOT EXISTS "user"
(
    id          SERIAL        NOT NULL,
    username    VARCHAR(255)  NOT NULL,
    password    VARCHAR(255)  NOT NULL,
    role        VARCHAR(255)  NOT NULL,
    locked      BOOLEAN       NOT NULL,
    active      BOOLEAN       NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY(id),
    CONSTRAINT user_username_unique UNIQUE(username)
);

CREATE TABLE IF NOT EXISTS message
(
    id                  SERIAL      NOT NULL,
    read                BOOLEAN     NOT NULL DEFAULT FALSE,
    marked              BOOLEAN     NOT NULL DEFAULT FALSE,
    title               VARCHAR     NOT NULL DEFAULT '',
    body                TEXT        NOT NULL DEFAULT '',
    date                TIMESTAMP   NOT NULL,
    receiver_user_id    SERIAL      NOT NULL,
    sender_user_id      SERIAL      NOT NULL,
    CONSTRAINT message_id_pk PRIMARY KEY(id),
    CONSTRAINT message_user_id_fk FOREIGN KEY(sender_user_id) REFERENCES "user"(id)
)