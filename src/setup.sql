USE reserveme;

DROP TABLE IF EXISTS users;
CREATE TABLE users 
    (
        userID INT,
        fname VARCHAR(30),
        lname VARCHAR(30),
        address VARCHAR(50)
    );

INSERT INTO users (userID, fname, lname, address) VALUES(101,'Bill','Clinton','101 A st.');
INSERT INTO users (userID, fname, lname, address) VALUES(102,'Bernie','Sanders','102 B st.');
INSERT INTO users (userID, fname, lname, address) VALUES(103,'George','Bush','103 C st.');
INSERT INTO users (userID, fname, lname, address) VALUES(104,'Barry','Obama','104 D st.');
INSERT INTO users (userID, fname, lname, address) VALUES(105,'Ronald','Regan','105 E st.');
INSERT INTO users (userID, fname, lname, address) VALUES(106,'Teddy','Roosevelt','106 F st.');
INSERT INTO users (userID, fname, lname, address) VALUES(107,'George','Washington','107 G st.');
INSERT INTO users (userID, fname, lname, address) VALUES(108,'Hillary','Clinton','101 A st.');
INSERT INTO users (userID, fname, lname, address) VALUES(109,'Some','One','102 B st.');
INSERT INTO users (userID, fname, lname, address) VALUES(110,'Jeb','Bush','103 C st.');


DROP TABLE IF EXISTS orders;
CREATE TABLE orders 
    (
        orderID INT, 
        userID INT, 
        foodID INT, 
        temporalInfo DATETIME, 
        processed BOOLEAN
    );

INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(1,101,101,'11-13-15 12:00:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(2,102,102,'11-15-15 13:00:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(3,103,103,'11-21-15 18:30:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(4,104,104,'11-16-15 10:00:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(5,105,105,'11-11-15 21:00:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(6,106,106,'12-01-15 14:45:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(7,107,107,'12-01-15 11:00:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(8,108,108,'11-16-15 12:30:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(9,109,109,'11-15-15 19:00:00', FALSE);
INSERT INTO orders (orderID, userID, foodID, temporalInfo, processed) 
    VALUES(10,110,110,'11-15-15 22:00:00', FALSE);

DROP TABLE IF EXISTS foods;
CREATE TABLE foods
    (
        foodID INT,
        dishname VARCHAR(50),
        category VARCHAR(50),
        ingredients VARCHAR(100),
        price FLOAT,
        stock INT,
        ordered INT,
        lunch BOOLEAN,
        dinner BOOLEAN
    );

INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(101, 'Sushi', 'Rice', 'Fish, Vinegar, Rice, Seaweed, Avacado, Cucumber', 
    10, 20, 3, FALSE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(102, 'Pad Thai', 'Noodles','stuff',6.5,20,4,TRUE,TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(103, 'Yakitori', 'Grilled', 'stuff', 8, 10, 5, FALSE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(104, 'Ramen', 'Noodles', 'stuff', 5, 30, 6, TRUE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(105, 'Curry', 'Curry', 'stuff', 5, 40, 7, TRUE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(106, 'Soon Dubu', 'Soup', 'stuff', 8.5, 20, 5, TRUE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(107, 'Sam Gae Tang', 'Soup', 'stuff', 14, 5, 3, TRUE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(108, 'Spicy Chicken', 'Meat', 'stuff', 8, 5, 2, FALSE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(109, 'Chow Mein', 'Noodles', 'stuff', 5.5, 30, 5, TRUE, TRUE);
INSERT INTO foods (foodID, dishname, category, ingredients, 
    price, stock, ordered, lunch, dinner) 
VALUES(110, 'Ice Cream', 'Dessert', 'stuff', 4, 20, 6, FALSE, TRUE);


DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations
    (
        reservationID INT,
        userID INT,
        tableID INT,
        orderID INT,
        people INT,
        resDate DATETIME
    );

INSERT INTO reservations (reservationID, userID, tableID, orderID, people, resDate) 
VALUES(1, 103, 3, 115, 4, '11-11-2015 18:30:00');


DROP TABLE IF EXISTS tables;
CREATE TABLE tables
    (
        tableID INT,
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

QUIT
