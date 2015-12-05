DROP DATABASE IF EXISTS archiveDB;
CREATE DATABASE archiveDB;
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
