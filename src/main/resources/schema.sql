CREATE DATABASE appdb;
\c appdb;

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_rh (

    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    nome VARCHAR(255) NOT NULL UNIQUE,

    email VARCHAR(255) NOT NULL UNIQUE,

    senha VARCHAR(255) NOT NULL,

    role VARCHAR(50) NOT NULL
);

CREATE TABLE tb_employee (

    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    nome VARCHAR(255) NOT NULL UNIQUE,

    email VARCHAR(255) NOT NULL UNIQUE,

    senha VARCHAR(255) NOT NULL,

    position VARCHAR(255) NOT NULL,

    role VARCHAR(50) NOT NULL
);

CREATE INDEX idx_rh_email
ON tb_rh(email);

CREATE INDEX idx_employee_email
ON tb_employee(email);

INSERT INTO tb_rh (
    nome,
    email,
    senha,
    role
)
VALUES (
    'Administrador RH',
    'rh@empresa.com',
    '123456',
    'RH'
);

INSERT INTO tb_employee (
    nome,
    email,
    senha,
    position,
    role
)
VALUES (
    'João Silva',
    'joao@empresa.com',
    '123456',
    'Desenvolvedor Backend',
    'EMPLOYEE'
);

SELECT * FROM tb_rh;

SELECT * FROM tb_employee;