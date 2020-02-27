-- Indexes for primary keys have been explicitly created.

DROP TABLE User;
DROP TABLE Cinema;
DROP TABLE City;
DROP TABLE Movie;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE City (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	CONSTRAINT CityPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE INDEX CityIndexByName ON City (name);

CREATE TABLE Cinema (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	cityId BIGINT NOT NULL, 
	CONSTRAINT CinenamPK PRIMARY KEY (id),
	CONSTRAINT CinemaCityFK FOREIGN KEY (cityId) 
        REFERENCES City (id)
) ENGINE = InnoDB;

CREATE INDEX CinemaIndexByName ON Cinema (name);





CREATE TABLE Movie (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(60) NOT NULL,
    synopsis VARCHAR(2000) NOT NULL, 
    duration SMALLINT NOT NULL, 
    CONSTRAINT MoviePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE INDEX MovieIndexByTitle ON Movie (title);
