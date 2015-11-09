# Sort the menu with price in decreasing or increasing order 
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
ORDER BY Price; #DESC or ASC(default)

# Sort the menu with the pre-fixed menu number of the foods
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
ORDER BY foodID;  #DESC or ASC(default)

# Sort the menu by name in alphabetical order.
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
ORDER BY dishName;

#Sort the menu by Appetizers
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
WHERE category = 'Appetizer';

#Sort the menu by Alcoholic Drinks and Non-Alcoholic Drinks
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
WHERE category = 'Alcoholic Drink'; #WHERE categories = 'Non-Alcoholic Drinks' 

#Sort the menu by Meats
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
WHERE category = 'Beef'; #Pork, Seafood, Soup

#Sort the menu if the items are available for lunch 
SELECT foodID, dishName, category, ingredients, price
FROM Foods
WHERE Lunch = 'True'; 

#Sort the menu if the items are available for dinner 
SELECT foodID, dishName, category, ingredients, price
FROM Foods
WHERE Dinner = 'True';

#Sort menu by price < or > price specified by the user
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
WHERE price > ?; #WHERE price < ?

#The system shows the product’s name that contains the text customer input
SELECT foodID, dishName, category, ingredients, price, lunch, dinner
FROM Foods
WHERE dishName = ?; 

#The system shows the ingredients used to cook a specific dish
SELECT dishName, ingredients
FROM Foods
WHERE dishName = ?;
