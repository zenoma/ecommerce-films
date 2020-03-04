-- Indexes for primary keys have been explicitly created.

DROP TABLE Book;
DROP TABLE User;
DROP TABLE SessionMovie;
DROP TABLE Room;
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

CREATE INDEX UserIndexByUserName ON User(userName);

CREATE TABLE City (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	CONSTRAINT CityPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE INDEX CityIndexByName ON City(name);

CREATE TABLE Cinema (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
	cityId BIGINT NOT NULL, 
	CONSTRAINT CinemaPK PRIMARY KEY (id),
	CONSTRAINT CinemaCityFK FOREIGN KEY (cityId) 
        REFERENCES City (id)
) ENGINE = InnoDB;

CREATE INDEX CinemaIndexByName ON Cinema(name);

CREATE TABLE Room (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL, 
    capacity INT NOT NULL,
    cinemaId BIGINT NOT NULL,
	CONSTRAINT RoomPK PRIMARY KEY (id),
	CONSTRAINT RoomCinemaFK FOREIGN KEY (cinemaId) 
        REFERENCES Cinema (id)
) ENGINE = InnoDB;

CREATE INDEX RoomIndexByName ON Room(name);

CREATE TABLE Movie (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(60) NOT NULL,
    synopsis VARCHAR(2000) NOT NULL, 
    duration SMALLINT NOT NULL, 
    CONSTRAINT MoviePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE INDEX MovieIndexByTitle ON Movie (title);

CREATE TABLE SessionMovie(
    id BIGINT NOT NULL AUTO_INCREMENT,
    movieId BIGINT NOT NULL,
    roomId BIGINT NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
	seats SMALLINT NOT NULL,
    date DATETIME NOT NULL,
	CONSTRAINT SessionPK PRIMARY KEY (id),
	CONSTRAINT SessionMovieFK FOREIGN KEY (movieId) 
        REFERENCES Movie (id),
	CONSTRAINT SessionRoomFK FOREIGN KEY (roomId) 
        REFERENCES Room (id)
);

CREATE TABLE Book(
    id BIGINT NOT NULL AUTO_INCREMENT,
    tickets INT NOT NULL,
    movieSessionId BIGINT NOT NULL,
    credit_card BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    date DATETIME NOT NULL,
	CONSTRAINT BookPK PRIMARY KEY (id),
	CONSTRAINT BookMovieSessionFK FOREIGN KEY (movieSessionId) 
        REFERENCES MovieSession (id),
	CONSTRAINT BookUserFK FOREIGN KEY (userId) 
        REFERENCES User (id)
);