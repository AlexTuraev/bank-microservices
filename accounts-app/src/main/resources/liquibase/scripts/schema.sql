-- liquibase formatted sql

-- changeset alex turaev:1

create table if not exists accounts(
    id bigserial primary key,
    login varchar(255) unique not null,
    password_hash text,
    name varchar(255) not null,
    birthdate date
    );