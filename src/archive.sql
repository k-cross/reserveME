DROP DATABASE IF EXISTS archiveDB;
CREATE DATABASE archiveDB;
GRANT ALL ON archiveDB.* TO 'cs157abnb'@'localhost';
USE archiveDB;


CREATE TABLE reservations
    (
        reservationID INTEGER NOT NULL,
        userID INTEGER,
        tableID INTEGER,
        orderID INTEGER,
        people INTEGER,
        resDate DATETIME,
        updatedAt DATETIME
    ) ENGINE=ARCHIVE;


#Procedure 2: Archiving
DROP PROCEDURE IF EXISTS UpdateArchive;
DELIMITER //
CREATE PROCEDURE UpdateArchive()
BEGIN
    INSERT INTO archiveDB.reservations 
        SELECT * FROM reserveme.reservations AS checkTime
        WHERE (TO_DAYS(NOW()) - TO_DAYS(checkTime.updatedAt) > 30);
END; //
DELIMITER ;
