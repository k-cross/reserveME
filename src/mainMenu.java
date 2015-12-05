import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class mainMenu extends JFrame
{
	public mainMenu(Restaurant restaurant)
	{
		super("Menu");
		this.setLayout(new FlowLayout());
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = JOptionPane.showInputDialog("Enter your username: ");
				String password = JOptionPane.showInputDialog("Enter your password: ");
				if(!user.isEmpty() || !user.equals("") || !password.isEmpty() || !password.equals(""))
				{
					if(restaurant.userLogin(user, password))
					{
						new Window(restaurant);
					}
					else
					{
						if(restaurant.findUser())
							JOptionPane.showMessageDialog(null, String.format("User not Found!"));
						else if (restaurant.checkPW())
							JOptionPane.showMessageDialog(null, String.format("Invaild Password!"));
					}
				}
				else
					JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
			}
		});
		JButton signUp = new JButton("Sign-Up");
		signUp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter your name: ");
				String user = JOptionPane.showInputDialog("Enter your username: ");
				String password = JOptionPane.showInputDialog("Enter your password: ");
				String rePassword = JOptionPane.showInputDialog("Re-Enter your password: ");
				if(password.equals(rePassword))
				{
					if(restaurant.insertUser(name, user, password, 0))
						JOptionPane.showMessageDialog(null, String.format("Your account has been created!\nName: %s\nUsername: %s\nPassword: %s", name, user, password));
					else
						JOptionPane.showMessageDialog(null, String.format("Fail to create the account"));
				}
				else
					JOptionPane.showMessageDialog(null, String.format("Passwords are not match~!"));
					
			}
		});
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		
		this.add(login);
		this.add(signUp);
		this.add(quit);
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocation(400, 200);
		this.setSize(100, 130);
		this.setVisible(true);
	}
	
	public void closeFrame()
	{
		super.dispose();
	}
}
