create table if not exists `users`
(
    `id`          bigint       not null auto_increment,
    `username`    varchar(255) not null,
    `password`    varchar(255) not null,
    `role`        varchar(255) not null,
    `lock`        boolean      not null,
    `active`      boolean      not null,
    primary key (`id`),
    unique (`username`)
)