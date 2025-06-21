-- liquibase formatted sql

-- changeset alex turaev:1

create table if not exists accounts(
    id bigserial primary key,
    login varchar(255) unique not null,
    password_hash text,
    name varchar(255) not null,
    birthdate date
    );

-- changeset alex turaev:2

create type currency_type as enum('rub', 'dollar', 'euro');

create table if not exists bank_account(
    id bigserial primary key,
    number bigint unique not null,
    accounts_id bigint,
    currency currency_type not null,
    value numeric(10, 2) default 0,

    foreign key (accounts_id) references accounts(id) on delete cascade
    );