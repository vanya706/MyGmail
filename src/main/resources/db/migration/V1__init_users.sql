create table if not exists "user"
(
    id          serial        not null,
    username    varchar(255)  not null,
    password    varchar(255)  not null,
    role        varchar(255)  not null,
    locked      boolean       not null,
    active      boolean       not null,
    primary key (id),
    unique (username)
);