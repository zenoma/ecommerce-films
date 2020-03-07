-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

INSERT INTO City(name)
	VALUES ("A Coruña");

INSERT INTO Cinema(name, cityID)
	VALUES ("Cinoma", 1);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 1", 69, 1);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 2", 666, 1);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala 3", 420, 1);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("Batman Begins", "This is a synopsis",180);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("Caballero Oscuro", "This is a longer synopsis",200);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("Caballero Oscuro Renace", "This is a more longer synopsis",220);

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,1,5.0,0,"2020-03-07 18:14");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,1,5.0,0,"2020-04-07 18:14");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (4,2,7.0,0,"2020-03-07 18:14");

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (4,2,7.0,0,"2020-04-07 18:14");

 INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (5,3,10.0,0,"2020-04-07 18:14");


