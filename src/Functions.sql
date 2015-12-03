#1
#Add user
#Prepared Statement
INSERT INTO Users (userID, name, uname, pw, usertype) VALUES (?, ? , ?, ?, ?);

#2
#Delete user
#Prepared Statement
DELETE FROM Users
WHERE userID= ?;

#3
#Update user
#Prepared Statement
UPDATE Users
SET pw = 'qweasdzxc' #Can update name, uName, or userType
WHERE userID= ?;

#4
#Add reservation
#Prepared Statement
INSERT INTO Reservations (reservationID, userID, tableID, orderID, people, resDate) VALUES (?, ?, ?, ?, ?, ?);

#5
#Delete reservation
#Prepared Statement
DELETE FROM Reservations
WHERE userID= ?;

#6
#Update reservation
#Prepared Statement
UPDATE Reservations
SET resDate= '2015-11-11 18:30:00' 
WHERE userID= ?;

#7
#Add order
#Prepared Statement
INSERT INTO Orders (orderID, userID, foodID) VALUES (?, ?, ?);

#8
#Delete order
#Prepared Statement
DELETE FROM Orders
WHERE orderID= ?;

#9
#Update order
#Prepared Statement
UPDATE Orders
SET foodID= 'Beer With Broccoli' 
WHERE orderID= ?;

#10
# Sort the menu with price in decreasing or increasing order 
SELECT foodID, dishname, category, price
FROM Foods
ORDER BY Price; #DESC or ASC(default)

#11
#Sort the menu by category
SELECT foodID, dishname, category, price
FROM Foods
WHERE category = 'Appetizer';

#12
# Sort the menu by name in alphabetical order.
SELECT foodID, dishname, category, price
FROM Foods
ORDER BY dishName;

#13
#Sort menu by price < or > price specified by the user 
#Prepared Statement
SELECT foodID, dishName, category, price
FROM Foods
WHERE price > ?; #WHERE price < ?

#14
#Correlated Subquery
#Will give the chepeast items for all categories
SELECT foodID, dishName, category, price 
FROM foods AS lessThan
WHERE price <= (
	SELECT AVG(Price)
    FROM foods
    WHERE category = lessThan.category);

#15
#View open tables
SELECT tableID
FROM tables
WHERE people = 0; #0 people mean that the table is free

#16
#Top 3 most popular food, food is considered popular if greater than 20
SELECT foodID,dishName,category,price
FROM foods
GROUP BY ordered
HAVING ordered IS NOT NULL
ORDER BY ordered desc
LIMIT 3;

#Trigger 1
#Decrement ordered after deletion of an orderID
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

#Trigger 2
#Increment ordered after insertion of an order with a given foodID
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

#Procedure 1: Outerjoin, total price of the order
DROP PROCEDURE IF EXISTS GetOrderTotal;
DELIMITER //
CREATE PROCEDURE GetOrderTotal()
BEGIN
	SELECT Round(SUM(price),2)
	FROM orders
	LEFT JOIN foods ON orders.foodID = foods.foodID
	WHERE orderID = ?
	UNION
	SELECT Round(SUM(price),2)
	FROM orders
	RIGHT JOIN foods ON orders.foodID = foods.foodID
	WHERE orderID = ?;
END; //
DELIMITER ; 
