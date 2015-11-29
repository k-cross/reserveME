import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller extends JFrame
{
	
	public Controller(Restaurant restaurant)
	{
		// setting up the window
		String title = restaurant.getUser().getType() + " | Dashboard";
		this.setTitle(title);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(800,600);
		this.setVisible(true);
		
		// setting up the control menu
		JPanel controller = new JPanel();
		controller.setLayout(new BoxLayout(controller, BoxLayout.Y_AXIS));
		controller.setVisible(true);

		switch(restaurant.getUser().getType())
		{
			case "manager":
				JButton order = new JButton("List of Orders");
				JButton table = new JButton("List of Tables");
				JButton reservations = new JButton("List of Reservations");
				JButton add = new JButton("Add Staff");
				add.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Enter the name: ");
						String user = JOptionPane.showInputDialog("Enter the username: ");
						String password = JOptionPane.showInputDialog("Enter the password: ");
						String rePassword = JOptionPane.showInputDialog("Re-Enter the password: ");
						if(password.equals(rePassword))
						{
							if(restaurant.insertUser(name, user, password, "staff"))
								JOptionPane.showMessageDialog(null, String.format("The account has been created!\nName: %s\nUsername: %s\nPassword: %s", name, user, password));
							else
								JOptionPane.showMessageDialog(null, String.format("Fail to create the account"));
						}
						else
							JOptionPane.showMessageDialog(null, String.format("Passwords are not match~!"));
							
					}
				});
				JButton delete = new JButton("Detele Staff");
				delete.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						String user = JOptionPane.showInputDialog("Enter the name: ");
						if(!user.equals("") && user != null)
						{
							restaurant.deleteUser(user);
							JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
						}
						else
							JOptionPane.showMessageDialog(null, String.format("The input is empty!"));
							
					}
				});
				// controller.add();
				controller.add(order);
				controller.add(table);
				controller.add(reservations);
				controller.add(add);
				controller.add(delete);
				break;
			case "staff":
				order = new JButton("List of Orders");
				table = new JButton("List of Tables");
				reservations = new JButton("List of Reservations");
				controller.add(order);
				controller.add(table);
				controller.add(reservations);
				break;
			case "customer":
				order = new JButton("Make an Orders");
				reservations = new JButton("Make an Reservations");
				controller.add(order);
				controller.add(reservations);
				break;
			default:
				break;
		}
		
		JButton password = new JButton("Change Password");
		password.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = JOptionPane.showInputDialog("Enter the password: ");
				String rePassword = JOptionPane.showInputDialog("Re-Enter the password: ");
				if(password.equals(rePassword))
				{
					restaurant.updatePassword(restaurant.getUser().getUserID(), password);
					JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
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
		
		controller.add(password);
		controller.add(quit);
		controller.setBackground(Color.DARK_GRAY);
		
		this.add(controller, BorderLayout.LINE_START);
		// Update view
		this.add(new View(restaurant, 0));
	}
	

	
	public void closeFrame()
	{
		super.dispose();
	}
}
