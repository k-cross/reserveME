#1
#Add user
#Prepared Statement
INSERT INTO Users (uID, name, uName, pw, userType) VALUES (?, ? , ?, ?, ?);

#2
#Delete user
#Prepared Statement
DELETE FROM Users
WHERE uID = ?;

#3
#Update user
#Prepared Statement
UPDATE Users
SET pw = 'qweasdzxc' #Can update name, uName, or userType
WHERE uID = ?;

#4
#Add reservation
#Prepared Statement
INSERT INTO Reservations (rID, uID, tID, oID, people, dateTime) VALUES (?, ?, ?, ?, ?, ?);

#5
#Delete reservation
#Prepared Statement
DELETE FROM Reservations
WHERE uID = ?;

#6
#Update reservation
#Prepared Statement
UPDATE Reservations
SET dateTime = '2015-11-11 18:30:00' 
WHERE uID = ?;

#7
#Add order
#Prepared Statement
INSERT INTO Orders (oID, uID, fID, dateTime) VALUES (?, ?, ?, ?);

#8
#Delete order
#Prepared Statement
DELETE FROM Orders
WHERE oID = ?;

#9
#Update order
#Prepared Statement
UPDATE Orders
SET fID = 'Beer With Broccoli' 
WHERE oID = ?;

#10
# Sort the menu with price in decreasing or increasing order 
SELECT foodID, dishName, category, price
FROM Foods
ORDER BY Price; #DESC or ASC(default)

#11
#Sort the menu by category
SELECT foodID, dishName, category, price
FROM Foods
WHERE category = 'Appetizer';

#12
# Sort the menu by name in alphabetical order.
SELECT foodID, dishName, category, price
FROM Foods
ORDER BY dishName;

#13
#Sort menu by price < or > price specified by the user 
#Prepared Statement
SELECT foodID, dishName, category, price
FROM Foods
WHERE price > ?; #WHERE price < ?

#14
#Sort with the most popular food
SELECT dishName
FROM Foods
GROUP BY ordered
ORDER BY SUM(ordered) DESC;

#15
#View open tables
SELECT tID
FROM tables
WHERE people = 0; #0 people mean that the table is free
