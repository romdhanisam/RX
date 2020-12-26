DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;

CREATE TABLE persons (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  age INT DEFAULT NULL,
  creation_date DATE NOT NULL,
  updating_date DATE NOT NULL,
  deleting_date DATE
);

CREATE TABLE authors (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  creation_date DATE NOT NULL,
  updating_date DATE NOT NULL,
  deleting_date DATE
);

CREATE TABLE books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  creation_date DATE NOT NULL,
  updating_date DATE NOT NULL,
  deleting_date DATE
);


