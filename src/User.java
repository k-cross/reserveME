
public class User 
{
	private int userID;
	private String name, address, userName, password, type;
	
	public User(int userID, String name, String userName, String password, String type)
	{
		this.userID = userID;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.type = type;
	}
	
	// setters
	
	// getters
	public int getUserID()
	{
		return this.userID;
	}
	public String getPassword()
	{
		return password;
	}
	public String getType()
	{
		return this.type;
	}
	
	// toString
	public String toString()
	{
		return this.userID + " " + this.name + " " + this.userName + " " +  this.password + " " + this.type;
	}
}
