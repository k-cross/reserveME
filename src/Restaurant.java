import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Restaurant 
{
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	private boolean userNotFound = true;
	private boolean invaildPW = true;
	
	private User user;
	
	Scanner input = new Scanner(System.in);
	
	public Restaurant()
	{
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
	}
	
	// connect to database
	public boolean connectDB()
	{
		try 
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reserveme","root", "");
	 
		}
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
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
			if(resultSet.getString("password").equals(password))
				invaildPW = false;
			else
				return false;
			user = new User(resultSet.getInt("userID"), resultSet.getString("name"), resultSet.getString("uname"), resultSet.getString("pw"), resultSet.getString("usertype"));
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		switch(user.getType())
		{
			case "manager":
				manager(user);
				break;
			case "staff":
				staff(user);
				break;
			case "customer":
				customer(user);
				break;
			default:
				break;
		}
		return true;
	}
	
	public boolean getUser()
	{
		return this.userNotFound;
	}
	
	public boolean checkPW()
	{
		return this.invaildPW;
	}
	
	// different user types
	private void manager(User user)
	{
		String name=null, userName=null, passWord=null;
		boolean quit = false;
		while (!quit)
		{
			System.out.print("\n --- Manager Menu ---" 
					+ "\n 1. List of Orders"
					+ "\n 2. List of Tables"
					+ "\n 3. List of Reservations"
					+ "\n 4. Add Staff"
					+ "\n 5. Detele Staff"
					+ "\n 6. Quit"
					+ "\n\n[ Enter the Number of Your Choice : ] > ");
			switch(input.next())
			{
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					System.out.print("\n[ Enter your name : ] > ");
					name = (input.hasNext())? input.next():null;
					System.out.print("\n[ Enter your Username : ] > ");
					userName = (input.hasNext())? input.next():null;
					System.out.print("\n[ Enter your Password : ] > ");
					passWord = (input.hasNext())? input.next():null;
					if(!this.insertUser(name, userName, passWord, "staff"))
						System.out.println("Creation Failed!");
					break;
				case "5":
					System.out.print("\n[ Enter your Username : ] > ");
					userName = (input.hasNext())? input.next():null;
					if(!this.deleteUser(userName))
						System.out.println("Deteletion Failed!");
					break;
				case "6":
					quit = true; 
					break;
				default:
					break;
			}
		}
	}
	
	private void staff(User user)
	{
		boolean quit = false;
		while (!quit)
		{
			System.out.print("\n --- Staff Menu ---" 
					+ "\n 1. List of Orders"
					+ "\n 2. List of Tables"
					+ "\n 3. List of Reservations"
					+ "\n 4. Account"
					+ "\n 5. Quit"
					+ "\n\n[ Enter the Number of Your Choice : ] > ");
			switch(input.next())
			{
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					quit = true; 
					break;
				default:
					break;
			}
		}
	}
	
	private void customer(User user)
	{
		boolean quit = false;
		while (!quit)
		{
			System.out.print("\n --- Customer Menu ---" 
					+ "\n 1. Make an Orders"
					+ "\n 2. Make an Reservations"
					+ "\n 3. Update Password"
					+ "\n 4. Quit"
					+ "\n\n[ Enter the Number of Your Choice : ] > ");
			switch(input.next())
			{
				case "1":
					break;
				case "2":
					break;
				case "3":
					System.out.print("\n[ Enter your new Password : ] > ");
					String passWord = (input.hasNext())? input.next():null;
					if(!this.updatePassword(user.getUserID(), passWord))
						System.out.println("The password has not been updated!");
					break;
				case "4":
					break;
				case "5":
					quit = true; 
					break;
				default:
					break;
			}
		}
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
				this.statement = this.connection.prepareStatement("INSERT INTO users (name, uname, pw, type) VALUES (?, ?, ?, ?)");
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
					System.out.println("Your account has been created!");
					return true;
				}
				else
					return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return false;
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
					System.out.println("Your account has been deleted!");
					return true;
				}
				else
					return false;
			}
			else
			{
				System.out.println("The username is not existed!");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return false;
	}
	
	private boolean updatePassword(int userID, String password)
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
					System.out.println("Your password has been updated!");
					return true;
				}
				else
					return false;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return false;
	}
}
