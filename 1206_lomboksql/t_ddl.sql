-- SQL dialect is not configured ->

--DDL
--CREATE TABLE: 새로운 테이블을 데이터베이싀에 추가
--CREATE TABLE table_name(
  --  column_name_1 integer,
    --column_name_2 integer,
    --column_name_3 integer,
--...
--);

--id, name, email
CREATE TABLE students (
    id integer,
    name VARCHAR(32),
    email TEXT
);

--id, username, first_naem, last_name, email
CREATE TABLE students_2 (
  id integer,
  username TEXT,

  first_name TEXT,
  last_name TEXT,
  email TEXT


);
--Constraints

-- NOT Null Constraint
CREATE TABLE students_not_null (
  id integer,
  username TEXT,
  first_name TEXT,
  last_name TEXT,
  email TEXT NOT NULL --email은 null이 될 수 없다.
);

-- UNIQUE Constraint
CREATE TABLE student_unique (
    id INTEGER,
    username TEXT UNIQUE, --username은 레코드별로 고유하다.
    first_name TEXT,
    last_name TEXT,
    email TEXT
);
-- PRIMARY KEY + AUTOINCREMENT
CREATE TABLE students_pka (
 -- id INTEGER PRIMARY KEY
 id INTEGER PRIMARY KEY AUTOINCREMENT,
 username TEXT,
 first_name TEXT,
 last_name TEXT,
 email TEXT

);

--Final
CREATE TABLE students_final (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE ,
    first_name TEXT,
    last_name,
    email TEXT NOT NULL
);

-- ALTER TABLE
-- RENAME TO
ALTER TABLE students_2 RENAME TO students_3;
ALTER TABLE students_3 RENAME TO students_2;

--RENAME COLUMN
ALTER TABLE students_2 RENAME COLUMN first_name TO given_name;
ALTER TABLE students_2 RENAME COLUMN given_name TO first_name;
ALTER TABLE students_2 RENAME COLUMN last_name TO sur_name;
ALTER TABLE students_2 RENAME COLUMN sur_name TO last_name;

-- ADD COLUMN
ALTER TABLE students_2 ADD COLUMN phone VARCHAR(64);
ALTER TABLE students_2 ADD COLUMN phone_2 VARCHAR(64) NOT NULL;
ALTER TABLE students_2 ADD COLUMN phone_3 VARCHAR(64) NOT NULL DEFAULT '';

-- DROP COLUMN
ALTER TABLE students_2 DROP COLUMN phone_3;

-- DROP TABLE
DROP TABLE students_2;
DROP TABLE IF EXISTS students_2;