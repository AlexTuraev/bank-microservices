-- liquibase formatted sql

-- changeset alex turaev:1

create table if not exists users(
    id bigserial primary key,
    login varchar(255) unique not null,
    password_hash text,
    name varchar(255) not null,
    birthdate date
    );

-- changeset alex turaev:2

create type currency_type as enum('rub', 'dollar', 'euro');

-- для номера счета
-- create sequence number_sequence
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;

create table if not exists bank_account(
    number bigserial primary key,
    users_id bigint,
    currency currency_type not null,
    value numeric(10, 2) default 0,

    foreign key (users_id) references users(id) on delete cascade
    );