create database SampleDB; 

use SampleDB;
 
 
CREATE TABLE users (
    user_id int identity(1,1) not null,
    username varchar(45) NOT NULL,
    password varchar(45) NOT NULL,
    fullname varchar(45) NOT NULL,
    email varchar(45) NOT NULL,
    PRIMARY KEY (user_id)
);