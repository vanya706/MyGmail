CREATE TABLE IF NOT EXISTS "user"
(
    id          SERIAL        NOT NULL,
    username    VARCHAR(255)  NOT NULL,
    password    CHAR(60)      NOT NULL,
    role        VARCHAR(255)  NOT NULL,
    locked      BOOLEAN       NOT NULL,
    active      BOOLEAN       NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS outbox_message
(
    id                  SERIAL            NOT NULL,
    title               VARCHAR           NOT NULL DEFAULT '',
    body                TEXT              NOT NULL DEFAULT '',
    date                TIMESTAMP         NOT NULL,
    marked              BOOLEAN           NOT NULL DEFAULT FALSE,
    message_content_id  SERIAL            NOT NULL,
    receiver_usernames   VARCHAR(255)[]    NOT NULL,
    sender_user_id      SERIAL            NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (sender_user_id) REFERENCES "user"(id)
);

CREATE TABLE IF NOT EXISTS inbox_message
(
    id                  SERIAL      NOT NULL,
    title               VARCHAR     NOT NULL DEFAULT '',
    body                TEXT        NOT NULL DEFAULT '',
    date                TIMESTAMP   NOT NULL,
    read                BOOLEAN     NOT NULL DEFAULT FALSE,
    marked              BOOLEAN     NOT NULL DEFAULT FALSE,
    message_content_id  SERIAL      NOT NULL,
    receiver_user_id    SERIAL      NOT NULL,
    sender_user_id      SERIAL      NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (receiver_user_id) REFERENCES "user"(id),
    FOREIGN KEY (sender_user_id) REFERENCES "user"(id)
);