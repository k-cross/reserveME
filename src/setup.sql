DROP DATABASE IF EXISTS reserveme;
CREATE DATABASE reserveme;
USE reserveme;
GRANT ALL ON reserveme.* TO 'cs157abnb'@'localhost';

DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS foods;
DROP TABLE IF EXISTS tables;
DROP TABLE IF EXISTS users;


CREATE TABLE users
    (
        userID INTEGER NOT NULL AUTO_INCREMENT,
        name VARCHAR(30),
        uname VARCHAR(50) UNIQUE,
        pw VARCHAR(50),
        usertype INTEGER NOT NULL,
        PRIMARY KEY (userID) 
    );

INSERT INTO users (userID, name, uname, pw, usertype) VALUES(101,'Chiu', 'chiu', 'qweasdzxc', 1);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(111,'David', 'kgb', 'qweasdzxc', 1);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(102,'Bernie Sanders', 'burn', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(103,'George Bush', 'JB', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(104,'Barry Obama', 'OG', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(105,'Ronald Regan', 'R&R', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(106,'Teddy Roosevelt', 'TeddyBear', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(107,'George Washington', 'jackLondon', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(108,'Hillary Clinton', 'hillaryDuff', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(109,'Some One', 'SomeTwo', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(110,'Jeb Bush', 'notGonnaWin', 'qweasdzxc', 0);


CREATE TABLE tables
    (
        tableID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        people INT
    );

INSERT INTO tables (tableID, people) VALUES(1,0);
INSERT INTO tables (tableID, people) VALUES(2,1);
INSERT INTO tables (tableID, people) VALUES(3,2);
INSERT INTO tables (tableID, people) VALUES(4,3);
INSERT INTO tables (tableID, people) VALUES(5,4);
INSERT INTO tables (tableID, people) VALUES(6,5);
INSERT INTO tables (tableID, people) VALUES(7,6);
INSERT INTO tables (tableID, people) VALUES(8,7);
INSERT INTO tables (tableID, people) VALUES(9,8);
INSERT INTO tables (tableID, people) VALUES(10,9);
INSERT INTO tables (tableID, people) VALUES(11,10);
INSERT INTO tables (tableID, people) VALUES(12,11);
INSERT INTO tables (tableID, people) VALUES(13,12);
INSERT INTO tables (tableID, people) VALUES(14,13);
INSERT INTO tables (tableID, people) VALUES(15,14);
INSERT INTO tables (tableID, people) VALUES(16,15);


CREATE TABLE foods
    (
        foodID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        dishname VARCHAR(50),
        category VARCHAR(50),
        price FLOAT,
        ordered INTEGER
    );

INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (101,'Pot Sticker(6)','Appetizer',5.95,20);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (102,'Spring Rolls(3)','Appetizer',3.95,22);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (103,'Hot and Sour Soup','Soup',6.95,10);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (104,'Mongolian Beef','Beef',9.95,30);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (105,'Beer With Broccoli','Beef',8.95,40);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (106,'Soon Dubu','Soup',8.5,20);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (107,'Sweet And Sour Pork','Pork',7.95,5);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (108,'Mo Shu Pork','Pork',7.95,5);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (109,'Kong Pao Prawns','Seafood',10.95,30);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (110,'Orange Juice','Non-Alcoholic Drink',2,20);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (111,'Pepsi','Non-Alcoholic Drink',2,20);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (112,'Bud Light','Alcoholic Drink',4.5,20);
INSERT INTO foods(foodID,dishName,category,price,ordered) VALUES (113,'Heineken','Alcoholic Drink',4.5,20);

CREATE TABLE orders
    (
        orderID INTEGER,
        userID INTEGER,
        foodID INTEGER,
        processed BOOLEAN,
        FOREIGN KEY (userID) REFERENCES users(userID) 
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        FOREIGN KEY (foodID) REFERENCES foods(foodID) 
        ON DELETE CASCADE
        ON UPDATE CASCADE
    );

INSERT INTO orders (orderID, userID, foodID, processed) VALUES(1,108,102, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(1,108,101, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(1,108,103, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(1,102,104, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(2,102,102, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(3,103,103, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(4,104,104, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(5,105,105, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(6,106,106, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(7,107,107, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(8,108,108, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(9,109,109, true);
INSERT INTO orders (orderID, userID, foodID, processed) VALUES(10,110,110, true);


CREATE TABLE reservations
    (
        reservationID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        userID INTEGER,
        tableID INTEGER,
        orderID INTEGER,
        people INTEGER,
        resDate DATETIME,
        updatedAt DATETIME,
        FOREIGN KEY (userID) REFERENCES users(userID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        FOREIGN KEY (tableID) REFERENCES tables(tableID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
   );


INSERT INTO reservations (userID, tableID, orderID, people, resDate, updatedAt)
VALUES(103, 3, 10, 4, '2015-11-11 18:30:00', '2015-11-11 18:30:00');
INSERT INTO reservations (userID, tableID, orderID, people, resDate, updatedAt)
VALUES(102, 3, 10, 4, '2015-10-10 18:30:00', '2015-10-10 18:30:00');


DROP TRIGGER IF EXISTS DecrementOrderTrigger;
delimiter //
CREATE TRIGGER DecrementOrderTrigger
AFTER DELETE ON orders FOR EACH ROW
BEGIN
    UPDATE Foods
    SET ordered = ordered - 1
    WHERE Old.foodID = foods.foodID;
END; //
delimiter ;


DROP TRIGGER IF EXISTS IncrementOrderTrigger;
delimiter //
CREATE TRIGGER IncrementOrderTrigger
AFTER INSERT ON orders FOR EACH ROW
BEGIN
    UPDATE Foods
    SET ordered = ordered + 1
    WHERE New.foodID = foods.foodID;
END; //
delimiter ;

DROP TRIGGER IF EXISTS UpdateOrderTrigger;
delimiter //
CREATE TRIGGER UpdateOrderTrigger
AFTER Update ON orders FOR EACH ROW
BEGIN
  UPDATE Foods
  SET ordered = ordered - 1
  WHERE Old.foodID = foods.foodID;
	UPDATE Foods
	SET ordered = ordered + 1
	WHERE New.foodID = foods.foodID;
END; //
delimiter ;

DROP PROCEDURE IF EXISTS GetOrderTotal;
DELIMITER //
CREATE PROCEDURE GetOrderTotal(IN uID INT)
BEGIN
	SELECT Round(SUM(price),2)
	FROM orders
	LEFT JOIN foods ON orders.foodID = foods.foodID
	WHERE userID = uID
	UNION
	SELECT Round(SUM(price),2)
	FROM orders
	RIGHT JOIN foods ON orders.foodID = foods.foodID
	WHERE userID = uID;
END; //
DELIMITER ;

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
        SELECT * FROM reserveme.reservations
        WHERE (TO_DAYS(NOW()) - TO_DAYS(reserveme.reservations.updatedAt) > 30);
    DELETE FROM reserveme.reservations
        WHERE (TO_DAYS(NOW()) - TO_DAYS(reserveme.reservations.updatedAt) > 30);
END; //
DELIMITER ;
