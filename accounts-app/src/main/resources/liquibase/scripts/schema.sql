-- liquibase formatted sql

-- changeset alex turaev:1

-- товары
create table if not exists accounts(
    id bigserial primary key,
    login varchar(255) not null,
    password_hash text
    );