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
}
