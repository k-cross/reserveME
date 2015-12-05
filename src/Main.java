import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args) 
	{
		Restaurant restaurant = new Restaurant();
		if(restaurant.connectDB())
		{
			new mainMenu(restaurant);
		}
		else
			System.out.print("There is no connection to Database!");
	}
}
