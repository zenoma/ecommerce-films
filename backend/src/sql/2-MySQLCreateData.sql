-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

INSERT INTO City(name)
	VALUES ("A Coruña");

INSERT INTO City(name)
	VALUES ("Lugo");

INSERT INTO City(name)
	VALUES ("Pontevedra");

INSERT INTO Cinema(name, cityID)
	VALUES ("Marineda", 1);

INSERT INTO Cinema(name, cityID)
	VALUES ("Espacio Coruña", 1);

INSERT INTO Cinema(name, cityID)
	VALUES ("Muralla", 2);

INSERT INTO Cinema(name, cityID)
	VALUES ("As Termas", 2);

INSERT INTO Cinema(name, cityID)
	VALUES ("Ravachol", 3);

INSERT INTO Cinema(name, cityID)
	VALUES ("Lérez", 3);

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

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala A", 50, 5);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala B", 10, 5);

INSERT INTO Room(name,capacity,cinemaId)
    VALUES ("Sala C", 5, 5);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("Batman Begins", "This is a synopsis",180);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("The Dark Knight", "This is a longer synopsis",200);

INSERT INTO Movie(title,synopsis,duration)
	VALUES ("The Dark Knight Rises", "This is a more longer synopsis",220);


#Cine Marineda
INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,1,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,2,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,3,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,1,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '1 00:05' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,2,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '1 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,3,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,1,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '2 00:05' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,2,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '2 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,3,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '2 23:55' DAY_MINUTE));

#Cine Ravachol
INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,13,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,13,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,13,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,13,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '0 20:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,14,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '1 00:05' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,14,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '1 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,14,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '2 23:55' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,14,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '3 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (1,15,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '4 20:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (2,15,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '5 17:00' DAY_MINUTE));

INSERT INTO MovieSession(movieId,roomId,price,seats,date)
    VALUES (3,15,5.0,0, DATE_ADD(DATE(NOW()), INTERVAL '6 20:00' DAY_MINUTE));



INSERT INTO User(userName, password, firstName, lastName, email, role)
  VALUES("ticketseller", "$2a$10$z0Ug3gPwsAf1ttTn8LQOEuJRXJBR95rpxynLLP8xLSoMp634HJi36", "firstName", "lastName", "email@email", 1);

INSERT INTO User(userName, password, firstName, lastName, email, role)
  VALUES("viwer", "$2a$10$P3ugvsOwhc70AD0uZ9pu0uK8kuqwS1nD6PRfI0BSJY6UKSXSSBiwm", "firstName", "lastName", "email@email", 0);

