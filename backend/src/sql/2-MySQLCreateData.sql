-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

INSERT INTO City(name)
	VALUES ("A Coruña");

INSERT INTO City(name)
	VALUES ("Lugo");

INSERT INTO Cinema(name, cityID)
	VALUES ("Marineda", 1);

INSERT INTO Cinema(name, cityID)
	VALUES ("Espacio Coruña", 1);

INSERT INTO Cinema(name, cityID)
	VALUES ("Muralla", 2);

INSERT INTO Cinema(name, cityID)
	VALUES ("As Termas", 2);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 1", 120, 1);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 2", 100, 1);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 3", 80, 1);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 1", 120, 2);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 2", 100, 2);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 3", 80, 2);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 1", 120, 3);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 2", 100, 3);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 3", 80, 3);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 1", 120, 4);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 2", 100, 4);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 3", 80, 4);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("Batman Begins", "This is a synopsis",180);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("The Dark Knight", "This is a longer synopsis",200);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("The Dark Knight Rises", "This is a more longer synopsis",220);


# Sesiones de Marineda
INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,1,5.0,0,"2020-03-01 22:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,1,5.0,0,"2021-03-01 22:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,2,5.0,0,"2021-03-01 22:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,3,5.0,0,"2021-03-01 22:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,3,5.0,0,"2021-03-02 22:00");

# Sesiones de Espacio Coruña
INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,4,5.0,0,"2021-03-01 20:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,5,5.0,0,"2021-03-01 20:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,6,5.0,0,"2021-03-01 20:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,6,5.0,0,"2021-03-02 20:00");

# Sesiones de Muralla
INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,7,5.0,0,"2021-03-01 18:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,8,5.0,0,"2021-03-01 18:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,9,5.0,0,"2021-03-01 18:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,9,5.0,0,"2021-03-02 18:00");

# Sesiones de As Termas
INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,10,5.0,0,"2021-03-01 20:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,11,5.0,0,"2021-03-01 20:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,12,5.0,0,"2021-03-01 20:00");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,12,5.0,0,"2021-03-02 20:00");