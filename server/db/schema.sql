DROP DATABASE IF EXISTS outsidehacks;

CREATE DATABASE outsidehacks;

\c outsidehacks;

CREATE TABLE location (
 id SERIAL PRIMARY KEY,
 lat VARCHAR(255),
 long VARCHAR(255),
 ip VARCHAR(255),
 time bigint
);
