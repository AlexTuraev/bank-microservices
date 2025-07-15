-- liquibase formatted sql

-- changeset alex turaev:1

-- товары
create table if not exists persistent_logins(
    username  varchar(64) not null,
    series    varchar(64) not null
    primary key,
    token     varchar(64) not null,
    last_used timestamp   not null
    );
alter table persistent_logins
    owner to "user";