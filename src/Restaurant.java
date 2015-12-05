import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Restaurant 
{
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String message;
	
	private boolean userNotFound = true;
	private boolean invaildPW = true;
	
	private User user;
	
	Scanner input = new Scanner(System.in);
	
	public Restaurant()
	{
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
		this.message = "";
	}
	
	// connect to database
	public boolean connectDB()
	{
		try 
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reserveme","cs157abnb", "qweasdzxc");
		}
		catch (SQLException e) 
		{
			return false;
		}
		if (this.connection != null)
			return true;
		return false;
	}
	
	// user login
	public boolean userLogin(String username, String password)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM users WHERE uname=?");
			this.statement.setString(1, username);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
				this.userNotFound = false;
			else
				return false;
			if(resultSet.getString("pw").equals(password))
				invaildPW = false;
			else
				return false;
			user = new User(resultSet.getInt("userID"), resultSet.getString("name"), resultSet.getString("uname"), resultSet.getString("pw"), resultSet.getString("usertype"));
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return true;
	}
	
	public boolean findUser()
	{
		return this.userNotFound;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public boolean checkPW()
	{
		return this.invaildPW;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	

	// functions
	public boolean insertUser(String name, String userName, String password, String userType)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT uname FROM users WHERE uname=?");
			this.statement.setString(1, userName);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				System.out.println("The username has already existed!");
				return false;
			}
			else
			{
				this.statement = this.connection.prepareStatement("INSERT INTO users (name, uname, pw, usertype) VALUES (?, ?, ?, ?)");
				this.statement.setString(1, name);
				this.statement.setString(2, userName);
				this.statement.setString(3, password);
				this.statement.setString(4, userType);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT uname FROM users WHERE uname=?");
				this.statement.setString(1, userName);
				this.resultSet = this.statement.executeQuery();
				if(resultSet.next())
				{
					return true;
				}
				else
					return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean deleteUser(String userName)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT uname FROM users WHERE uname=?");
			this.statement.setString(1, userName);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				this.statement = this.connection.prepareStatement("DELETE FROM users WHERE uname=?");
				this.statement.setString(1, userName);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT uname FROM users WHERE uname=?");
				this.statement.setString(1, userName);
				this.resultSet = this.statement.executeQuery();
				if(!resultSet.next())
				{
					message = "Your account has been deleted!";
					return true;
				}
				else
					return false;
			}
			else
			{
				message = "The username is not existed!";
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean updatePassword(int userID, String password)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM users WHERE userID=?");
			this.statement.setInt(1, userID);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				this.statement = this.connection.prepareStatement("UPDATE users SET pw=? WHERE userID=?");
				this.statement.setString(1, password);
				this.statement.setInt(2, userID);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT uname FROM users WHERE userID=? AND pw=?");
				this.statement.setInt(1, userID);
				this.statement.setString(2, password);
				this.resultSet = this.statement.executeQuery();
				if(resultSet.next())
				{
					message = "Your password has been updated!";
					return true;
				}
				else
				{
					message = "Fail to update the password!";
					return false;
				}
			}
			else
			{
				message = "Fail to update the password!";
				return false;
			}
		}
		catch(Exception e)
		{
			message = "Fail to update the password!";
			return false;
		}
	}
	
	// list orders;
	public JTable listOrders()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM orders");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
	// add order
	public boolean addOrder(int userID, int foodID)
	{
		try
		{
			this.statement = this.connection.prepareStatement("INSERT INTO orders (userID, foodID) VALUES (?, ?)");
			this.statement.setInt(1, userID);
			this.statement.setInt(2, foodID);
			this.statement.executeUpdate();
			message = "The order has been added!";
			return true;
		}
		catch(Exception e)
		{
			message = "Fail to add the order!";
			return false;
		}
	}
	// delete order
	public boolean deleteOrder(int orderID)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT orderID FROM orders WHERE orderID=?");
			this.statement.setInt(1, orderID);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				this.statement = this.connection.prepareStatement("DELETE FROM orders WHERE orderID=?");
				this.statement.setInt(1, orderID);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT orderID FROM orders WHERE orderID=?");
				this.statement.setInt(1, orderID);
				this.resultSet = this.statement.executeQuery();
				if(!resultSet.next())
				{
					message = "The order has been deleted!";
					return true;
				}
				else
					return false;
			}
			else
			{
				message = "The order is not existed!";
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	// update order
	public boolean updateOrder(int orderID, int foodID)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM orders WHERE orderID=?");
			this.statement.setInt(1, orderID);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				this.statement = this.connection.prepareStatement("UPDATE orders SET foodID=? WHERE orderID=?");
				this.statement.setInt(1, foodID);
				this.statement.setInt(2, orderID);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT * FROM orders WHERE orderID=? AND foodID=?");
				this.statement.setInt(1, orderID);
				this.statement.setInt(2, foodID);
				this.resultSet = this.statement.executeQuery();
				if(resultSet.next())
				{
					message = "The order has been updated!";
					return true;
				}
				else
				{
					message = "Fail to update the order!";
					return false;
				}
			}
			else
			{
				message = "Fail to update the order!";
				return false;
			}
		}
		catch(Exception e)
		{
			message = "Fail to update the order!";
			return false;
		}
	}
	
	// list of tables;
	public JTable listTables()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM tables");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
	//list of Open Table
	public JTable listOpenTables()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM tables where people <= 0");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
	
	
	// list of Reservations
	public JTable listReservations()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM reservations");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector<String> row = new Vector<String>();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					System.out.println(resultSet.getString(i));
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
	// add Reservation
	public boolean addReservations(int userID, int tableID, int people, String resDate)
	{
		try
		{
			this.statement = this.connection.prepareStatement("INSERT INTO reservations (userID, tableID, people, resDate) VALUES (?, ?, ?, ?)");
			this.statement.setInt(1, userID);
			this.statement.setInt(2, tableID);
			this.statement.setInt(3, people);
			this.statement.setString(4, resDate);
			this.statement.executeUpdate();
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	// delete Reservation
	public boolean deleteReservation(int userID)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT userID FROM reservations WHERE userID=?");
			this.statement.setInt(1, userID);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				this.statement = this.connection.prepareStatement("DELETE FROM reservations WHERE userID=?");
				this.statement.setInt(1, userID);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT userID FROM reservations WHERE userID=?");
				this.statement.setInt(1, userID);
				this.resultSet = this.statement.executeQuery();
				if(!resultSet.next())
				{
					message = "The reservation has been deleted!";
					return true;
				}
				else
					return false;
			}
			else
			{
				message = "The reservation is not existed!";
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean updateReservations(int userID, String resDate)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM reservations WHERE userID=?");
			this.statement.setInt(1, userID);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
			{
				this.statement = this.connection.prepareStatement("UPDATE reservations SET resDate=? WHERE userID=?");
				this.statement.setString(1, resDate);
				this.statement.setInt(2, userID);
				this.statement.executeUpdate();
				this.statement = this.connection.prepareStatement("SELECT * FROM reservations WHERE userID=? AND resDate=?");
				this.statement.setInt(1, userID);
				this.statement.setString(2, resDate);
				this.resultSet = this.statement.executeQuery();
				if(resultSet.next())
				{
					message = "The reservation has been updated!";
					return true;
				}
				else
				{
					message = "Fail to update the reservation!";
					return false;
				}
			}
			else
			{
				message = "Fail to update the reservation!";
				return false;
			}
		}
		catch(Exception e)
		{
			message = "Fail to update the reservation!";
			return false;
		}
	}
	
	public JTable sortByName()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT foodID, dishname, category, price FROM Foods ORDER BY dishName");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
	
	public JTable sortByPrice(int choice)
	{
		JTable list = new JTable();
		try
		{
			if(choice == 1)
				this.statement = this.connection.prepareStatement("SELECT foodID, dishname, category, price from Foods ORDER BY Price DESC");
			else
				this.statement = this.connection.prepareStatement("SELECT foodID, dishname, category, price from Foods ORDER BY Price ASC");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
/*
	public JTable sortByCategories()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
*/

	public JTable priceLessThan(double price)
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT foodID, dishName, category, price FROM Foods WHERE price < ?");
			this.statement.setDouble(1, price);
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	}
	public JTable priceGreaterThan(double price)
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT foodID, dishName, category, price FROM Foods WHERE price > ?");
			this.statement.setDouble(1, price);
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	} 
	
	public JTable cheapestItems()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT foodID, dishName, category, price FROM foods AS lessThan WHERE price <= (SELECT AVG(Price) FROM foods WHERE category = lessThan.category)");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	} 
	public JTable mostPopular()
	{
		JTable list = new JTable();
		try
		{
			this.statement = this.connection.prepareStatement("SELECT foodID,dishName,category,price FROM foods GROUP BY ordered HAVING ordered IS NOT NULL ORDER BY ordered desc LIMIT 3");
			this.resultSet = this.statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int c = rsmd.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmd.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(resultSet.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++){
					row.add(resultSet.getString(i));
				}
				data.add(row);
			}
			list = new JTable(data,column);
			return list;
		}
		catch(Exception e)
		{
			return list;
		}
	} 
}
