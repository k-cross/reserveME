import java.util.Scanner;
import javax.swing.*;

public class Main 
{
	
	public static void main(String[] args) 
	{
		String username = null;
		String password = null;
		boolean quit = false;
		Scanner input = new Scanner(System.in);
		
		Restaurant restaurant = new Restaurant();
		if(restaurant.connectDB())
		{
			while (!quit)
			{
				menu();
				switch(input.next())
				{
					case "1":
						username = null;
						password = null;
						
						System.out.print("\n[ Username : ] > ");
						username = (input.hasNext())? input.next():null;
						
						System.out.print("\n[ Password : ] > ");
						password = (input.hasNext())? input.next():null;
						
						if(restaurant.userLogin(username, password));
						else
						{
							if(restaurant.getUser())
								System.out.println("User not Found!");
							else if (restaurant.checkPW())
								System.out.println("Invaild Password!");
						}
						break;
					case "2":
						System.out.print("\n[ Enter your name : ] > ");
						String name = (input.hasNext())? input.next():null;
						
						System.out.print("\n[ Enter your Username : ] > ");
						String userName = (input.hasNext())? input.next():null;
						
						System.out.print("\n[ Enter your Password : ] > ");
						String passWord = (input.hasNext())? input.next():null;
						if(!restaurant.insertUser(name, userName, passWord, "customer"))
							System.out.println("Creation Failed!");
						break;
					case "3":
						quit = true; 
						break;
					default:
						break;
				}
			}
		}
	}
	
	public static void menu()
	{
		System.out.print("\n --- Menu ---" 
						+ "\n 1. Login"
						+ "\n 2. Sign-Up"
						+ "\n 3. Quit"
						+ "\n\n[ Enter the Number of Your Choice : ] > ");
	}
}
