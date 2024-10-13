```mariadb
CREATE DATABASE TaskManager;
USE TaskManager;

CREATE TABLE User (
    id int primary key auto_increment,
    firstName text not null,
    lastName text not null,
    username text not null,
    password text not null,
    admin boolean not null default false
);

# Seed db with some users
INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('John', 'Doe', 'johndoe', 'hashed_password_1', false);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Jane', 'Smith', 'janesmith', 'hashed_password_2', true);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Alice', 'Johnson', 'alicej', 'hashed_password_3', false);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Bob', 'Williams', 'bobbyw', 'hashed_password_4', false);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Charlie', 'Brown', 'charlieb', 'hashed_password_5', true);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('David', 'Miller', 'davidm', 'hashed_password_6', false);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Eve', 'Davis', 'eved', 'hashed_password_7', false);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Frank', 'Garcia', 'frankg', 'hashed_password_8', true);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Grace', 'Martinez', 'gracem', 'hashed_password_9', false);

INSERT INTO User (firstName, lastName, username, password, admin) 
    VALUES ('Hank', 'Lopez', 'hankl', 'hashed_password_10', true);
```