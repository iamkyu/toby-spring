DROP TABLE IF EXISTS SQLMAP;

CREATE TABLE IF NOT EXISTS SQLMAP (
  KEY_ VARCHAR(100) PRIMARY KEY,
  SQL_ VARCHAR(100) NOT NULL
);


DROP TABLE IF EXISTS USERS;

CREATE TABLE IF NOT EXISTS USERS (
    id varchar(10) primary key,
    name varchar(20) not null,
    password varchar(20) not null,
    email varchar(20) not null,
    level tinyint not null,
    login int not null,
    recommend int not null
);