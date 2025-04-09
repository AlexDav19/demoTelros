--liquibase formated sql

-- changeset alex:1
CREATE TABLE IF NOT EXISTS photo(
    id SERIAL PRIMARY KEY NOT NULL,
    data OID,
    file_path character varying(128) NOT NULL,
    file_size BIGINT,
    media_type character varying(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY NOT NULL,
    last_name character varying(128) NOT NULL,
    first_name character varying(128) NOT NULL,
    sur_name character varying(128),
    birthday date NOT NULL,
    email character varying(128) NOT NULL,
    telephone character varying(128) NOT NULL,
    photo_id BIGINT REFERENCES photo(id)
);