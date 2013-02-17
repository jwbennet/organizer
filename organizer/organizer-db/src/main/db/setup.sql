CREATE DATABASE organizer;

CREATE TABLE samples (
    id BIGINT,
    nm VARCHAR(32),
    val VARCHAR(32),
    ver_nbr BIGINT
);

CREATE TABLE notes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    text TEXT,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE people (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(40) NOT NULL,
    active VARCHAR(1) NOT NULL,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE phone_numbers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(5) NOT NULL,
    number VARCHAR(20) NOT NULL,
    extension VARCHAR(8),
    prm VARCHAR(1) NOT NULL,
    active VARCHAR(1) NOT NULL,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE email_addresses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(5) NOT NULL,
    email VARCHAR(100) NOT NULL,
    prm VARCHAR(1) NOT NULL,
    active VARCHAR(1) NOT NULL,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE names (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(5) NOT NULL,
    first_nm VARCHAR(40) NOT NULL,
    middle_nm VARCHAR(40),
    last_nm VARCHAR(80) NOT NULL,
    prm VARCHAR(1) NOT NULL,
    active VARCHAR(1) NOT NULL,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE addresses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(5) NOT NULL,
    line1 VARCHAR(45) NOT NULL,
    line2 VARCHAR(45),
    line3 VARCHAR(45),
    city VARCHAR(30) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    prm VARCHAR(1) NOT NULL,
    active VARCHAR(1) NOT NULL,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE logs (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(5) NOT NULL,
    subject VARCHAR(40) NOT NULL,
    location VARCHAR(40),
    agenda TEXT,
    dt DATETIME NOT NULL,
    duration INT,
    crte_dt DATETIME,
    updt_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE person_to_note (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    note_id BIGINT NOT NULL
);

CREATE TABLE person_to_phone_number (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    phone_number_id BIGINT NOT NULL
);

CREATE TABLE person_to_email_address (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    email_address_id BIGINT NOT NULL
);

CREATE TABLE person_to_name (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    name_id BIGINT NOT NULL
);

CREATE TABLE person_to_address (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL
);

CREATE TABLE log_to_person (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    log_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL
);

CREATE TABLE log_to_note (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    log_id BIGINT NOT NULL,
    note_id BIGINT NOT NULL
);

CREATE VIEW note AS (
    SELECT p.username,
           n.*
      FROM people p,
           notes n,
           person_to_note p2n
      WHERE p.id = p2n.person_id AND
            n.id = p2n.note_id
);

CREATE VIEW phone AS (
    SELECT p.username,
           pn.*
      FROM people p,
           phone_numbers pn,
           person_to_phone_number p2pn
      WHERE p.id = p2pn.person_id AND
            pn.id = p2pn.phone_number_id
);

CREATE VIEW email AS (
    SELECT p.username,
           em.*
      FROM people p,
           email_addresses em,
           person_to_email_address p2em
      WHERE p.id = p2em.person_id AND
            em.id = p2em.email_address_id
);

CREATE VIEW nm AS (
    SELECT p.username,
           n.*
      FROM people p,
           names n,
           person_to_name p2n
      WHERE p.id = p2n.person_id AND
            n.id = p2n.name_id
);

CREATE VIEW addr AS (
    SELECT p.username,
           a.*
      FROM people p,
           addresses a,
           person_to_address p2a
      WHERE p.id = p2a.person_id AND
            a.id = p2a.address_id
);
