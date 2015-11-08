--mysql -h localhost -p -u root

/* Uncomment these if DB is not setup

DROP DATABASE IF EXISTS reserveme; --Don't know if this works
CREATE DATABASE reserveme;
GRANT ALL ON reserveme.* TO 'cs157abnb'@'localhost' IDENTIFIED BY 'qweasdzxc';
*/

DROP TABLE IF EXISTS users;
CREATE TABLE users (uID INT,fname VARCHAR(30),lname VARCHAR(30),address VARCHAR(50));
INSERT INTO users (uID, fname, lname, address) VALUES(101,'Bill','Clinton','101 A st.');
INSERT INTO users (uID, fname, lname, address) VALUES(102,'Bernie','Sanders','102 B st.');
INSERT INTO users (uID, fname, lname, address) VALUES(103,'George','Bush','103 C st.');
INSERT INTO users (uID, fname, lname, address) VALUES(104,'Barry','Obama','104 D st.');
INSERT INTO users (uID, fname, lname, address) VALUES(105,'Ronald','Regan','105 E st.');
INSERT INTO users (uID, fname, lname, address) VALUES(106,'Teddy','Roosevelt','106 F st.');
INSERT INTO users (uID, fname, lname, address) VALUES(107,'George','Washington','107 G st.');
INSERT INTO users (uID, fname, lname, address) VALUES(108,'Hillary','Clinton','101 A st.');
INSERT INTO users (uID, fname, lname, address) VALUES(109,'Some','One','102 B st.');
INSERT INTO users (uID, fname, lname, address) VALUES(110,'Jeb','Bush','103 C st.');

--DROP TABLE IF EXISTS orders;
--CREATE TABLE orders (oID INT, uID INT, fID INT, day, time, processed BOOLEAN);
--INSERT INTO orders (1,101,101,11/13/15,12:00:00 PM, FALSE)
--INSERT INTO orders (2,102,102,11/15/15,01:00:00 PM, FALSE)
--INSERT INTO orders (3,103,103,11/21/15,06:30:00 PM, FALSE)
--INSERT INTO orders (4,104,104,11/16/15,10:00:00 AM, FALSE)
--INSERT INTO orders (5,105,105,11/11/15,09:00:00 PM, FALSE)
--INSERT INTO orders (6,106,106,12/01/15,02:45:00 PM, FALSE)
--INSERT INTO orders (7,107,107,12/01/15,11:00:00 AM, FALSE)
--INSERT INTO orders (8,108,108,11/16/15,12:30:00 PM, FALSE)
--INSERT INTO orders (9,109,109,11/15/15,07:00:00 PM, FALSE)
--INSERT INTO orders (10,110,110,11/15/15,10:00:00 PM, FALSE)
