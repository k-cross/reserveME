USE reserveme;

DROP TABLE IF EXISTS users;
CREATE TABLE users 
    (
        userID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(30),
        uname VARCHAR(50) UNIQUE,
        pw VARCHAR(50),
        usertype INTEGER NOT NULL
    );

INSERT INTO users (userID, name, uname, pw, usertype) VALUES(101,'Chiu', 'chiu', 'qweasdzxc', 1);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(102,'Bernie Sanders', 'burn', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(103,'George Bush', 'JB', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(104,'Barry Obama', 'OG', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(105,'Ronald Regan', 'R&R', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(106,'Teddy Roosevelt', 'TeddyBear', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(107,'George Washington', 'jackLondon', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(108,'Hillary Clinton', 'hillaryDuff', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(109,'Some One', 'SomeTwo', 'qweasdzxc', 0);
INSERT INTO users (userID, name, uname, pw, usertype) VALUES(110,'Jeb Bush', 'notGonnaWin', 'qweasdzxc', 0);


DROP TABLE IF EXISTS tables;
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


DROP TABLE IF EXISTS foods;
CREATE TABLE foods
    (
        foodID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        dishname VARCHAR(50),
        category VARCHAR(50),
        price FLOAT,
        ordered INTEGER
    );

INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(101, 'Sushi', 'Rice', 10, 20);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(102, 'Pad Thai', 'Noodles',6.5,20);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(103, 'Yakitori', 'Grilled', 8, 10);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(104, 'Ramen', 'Noodles', 5, 30);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(105, 'Curry', 'Curry', 5, 40);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(106, 'Soon Dubu', 'Soup', 8.5, 20);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(107, 'Sam Gae Tang', 'Soup', 14, 5);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(108, 'Spicy Chicken', 'Meat', 8, 5);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(109, 'Chow Mein', 'Noodles', 5.5, 30);
INSERT INTO foods (foodID, dishname, category, price, ordered) VALUES(110, 'Ice Cream', 'Dessert', 4, 20);


DROP TABLE IF EXISTS orders;
CREATE TABLE orders 
    (
        orderID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
        userID INTEGER FOREIGN KEY REFERENCES users(userID),
        foodID INTEGER FOREIGN KEY REFERENCES foods(foodID)
    );

INSERT INTO orders (orderID, userID, foodID) VALUES(1,101,101);
INSERT INTO orders (orderID, userID, foodID) VALUES(2,102,102);
INSERT INTO orders (orderID, userID, foodID) VALUES(3,103,103);
INSERT INTO orders (orderID, userID, foodID) VALUES(4,104,104);
INSERT INTO orders (orderID, userID, foodID) VALUES(5,105,105);
INSERT INTO orders (orderID, userID, foodID) VALUES(6,106,106);
INSERT INTO orders (orderID, userID, foodID) VALUES(7,107,107);
INSERT INTO orders (orderID, userID, foodID) VALUES(8,108,108);
INSERT INTO orders (orderID, userID, foodID) VALUES(9,109,109);
INSERT INTO orders (orderID, userID, foodID) VALUES(10,110,110);


DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations
    (
        reservationID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
        userID INTEGER FOREIGN KEY REFERENCES users(userID),
        tableID INTEGER FOREIGN KEY REFERENCES tables(tableID),
        orderID INTEGER FOREIGN KEY REFERENCES orders(orderID),
        people INTEGER,
        resDate DATETIME
    );

INSERT INTO reservations (reservationID, userID, tableID, orderID, people, resDate) 
VALUES(1, 103, 3, 115, 4, '11-11-2015 18:30:00');

QUIT
