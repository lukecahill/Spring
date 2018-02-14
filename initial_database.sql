CREATE DATABASE springdatabase;

CREATE USER 'luke'@'localhost' IDENTIFIED BY 'ThePassword';
GRANT ALL ON springdatabase.* TO 'luke'@'localhost';