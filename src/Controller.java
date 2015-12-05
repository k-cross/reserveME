import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Controller extends JPanel
{
	private Restaurant restaurant;
	// update the user type using int
	public Controller(Restaurant restaurant)
	{
		this.restaurant = restaurant;
		// setting up the control menu
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);

		switch(restaurant.getUser().getType())
		{
			case "1":
				JButton order = new JButton("List of Orders");
				order.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.listOrders()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						frame.setVisible(true);
					}
				});
				JButton addOrder = new JButton("Add Order");
				addOrder.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter the userID: "));
						int foodID = Integer.parseInt(JOptionPane.showInputDialog("Enter the foodID: "));
						if(userID >= 0 && foodID >= 0)
						{
							if(restaurant.addOrder(userID, foodID))
								JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
							else
								JOptionPane.showMessageDialog(null, "Error!");
						}
						else
							JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
					}
				});
				JButton deleteOrder = new JButton("Delete Order");
				deleteOrder.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int input = Integer.parseInt(JOptionPane.showInputDialog("Enter the orderID: "));
						if(input >= 0)
						{
							if(restaurant.deleteOrder(input))
								JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
							else
								JOptionPane.showMessageDialog(null, "Error!");
						}
						else
							JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
					}
				});
				JButton updateOrder = new JButton("Update Order");
				updateOrder.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int orderID = Integer.parseInt(JOptionPane.showInputDialog("Enter the orderID: "));
						int foodID = Integer.parseInt(JOptionPane.showInputDialog("Enter the foodID: "));
						if(orderID >= 0 && foodID >= 0)
						{
							if(restaurant.updateOrder(orderID, foodID))
								JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
							else
								JOptionPane.showMessageDialog(null, "Error!");
						}
						else
							JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
					}
				});
				JButton table = new JButton("List of Tables");
				table.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.listTables()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});
				
				JButton reservations = new JButton("List of Reservations");
				reservations.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.listReservations()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});
				JButton addReservations = new JButton("Add Reservations");
				addReservations.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter the userID: "));
						int tableID = Integer.parseInt(JOptionPane.showInputDialog("Enter the tableID: "));
						int people = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of people: "));
						String resDate = JOptionPane.showInputDialog("Enter the resDate YYYY-MM-DD hh:mm ");
						if(userID >= 0 && tableID >= 0 && people >= 0 && !resDate.isEmpty())
						{
							if(restaurant.addReservations(userID, tableID, people, resDate))
								JOptionPane.showMessageDialog(null, String.format("The Reservation has been created!"));
							else
								JOptionPane.showMessageDialog(null, String.format("Fail to create the Reservation"));
						}
						else
							JOptionPane.showMessageDialog(null, String.format("Passwords are not match~!"));
							
					}
				});
				JButton deleteReservations = new JButton("Delete Reservations");
				deleteReservations.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int input = Integer.parseInt(JOptionPane.showInputDialog("Enter the userID: "));
						if(input >= 0)
						{
							if(restaurant.deleteReservation(input))
								JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
							else
								JOptionPane.showMessageDialog(null, "Error!");
						}
						else
							JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
					}
				});
				
				JButton updateReservations = new JButton("Update Reservations");
				updateReservations.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int userID = Integer.parseInt(JOptionPane.showInputDialog("Enter the userID: "));
						String resDate = JOptionPane.showInputDialog("Enter the resDate YYYY-MM-DD hh:mm: ");
						if(userID >= 0 && !resDate.isEmpty())
						{
							if(restaurant.updateReservations(userID, resDate))
								JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
							else
								JOptionPane.showMessageDialog(null, "Error!");
						}
						else
							JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
					}
				});
				
				JButton listUser = new JButton("List of User");
				listUser.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.listUsers()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						frame.setVisible(true);
							
					}
				});
				
				JButton add = new JButton("Add User");
				add.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Enter the name: ");
						String user = JOptionPane.showInputDialog("Enter the username: ");
						String password = JOptionPane.showInputDialog("Enter the password: ");
						String rePassword = JOptionPane.showInputDialog("Re-Enter the password: ");
						if(password.equals(rePassword))
						{
							if(restaurant.insertUser(name, user, password, 0))
								JOptionPane.showMessageDialog(null, String.format("The account has been created!\nName: %s\nUsername: %s\nPassword: %s", name, user, password));
							else
								JOptionPane.showMessageDialog(null, String.format("Fail to create the account"));
						}
						else
							JOptionPane.showMessageDialog(null, String.format("Passwords are not match~!"));
							
					}
				});
				JButton delete = new JButton("Detele User");
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
				JButton Otable = new JButton("List of Open Table");
				Otable.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.listOpenTables()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});

				this.add(order);
				this.add(addOrder);
				this.add(deleteOrder);
				this.add(updateOrder);
				this.add(table);
				this.add(Otable);
				this.add(reservations);
				this.add(addReservations);
				this.add(deleteReservations);
				this.add(updateReservations);
				this.add(listUser);
				this.add(add);
				this.add(delete);
				break;
			case "0":
				// sort the menu by name in alphabet order
				JButton sByName = new JButton("Sort By Name");
				sByName.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.sortByName()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});
				// sort by price in decrease
				JButton sByPriceDec = new JButton("Sort By Prices (DEC)");
				sByPriceDec.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.sortByPrice(1)));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});
				// sort by price in increase 
				JButton sByPriceInc = new JButton("Sort By Prices (INC)");
				sByPriceInc.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						frame.add(new JScrollPane(restaurant.sortByPrice(0)));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});
				// sort by categories
				JButton sByCategories = new JButton("Sort By Categories");
				sByCategories.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frame = new JFrame(); 
						// frame.add(new JScrollPane(restaurant.sortByCategories()));
						frame.setSize(500, 300); 
						frame.setLocationRelativeTo(null); 
						
						frame.setVisible(true);
					}
				});
				// < ? price
				JButton lessPrice = new JButton("< ? Price");
				lessPrice.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						double input = Double.parseDouble(JOptionPane.showInputDialog("Price less than: "));
						if(input >= 0)
						{
							JFrame frame = new JFrame(); 
							frame.add(new JScrollPane(restaurant.priceLessThan(input)));
							frame.setSize(500, 300); 
							frame.setLocationRelativeTo(null); 
							
							frame.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, String.format("The input is empty!"));
					}
				});
				// > ? price
				JButton greaterPrice = new JButton("> ? Price");
				greaterPrice.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						double input = Double.parseDouble(JOptionPane.showInputDialog("Price greater than: "));
						if(input >= 0)
						{
							JFrame frame = new JFrame(); 
							frame.add(new JScrollPane(restaurant.priceGreaterThan(input)));
							frame.setSize(500, 300); 
							frame.setLocationRelativeTo(null); 
							
							frame.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, String.format("The input is empty!"));
					}
				});
				// cheapest items
				JButton cheapestItems = new JButton("Cheapest Items");
				cheapestItems.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
							JFrame frame = new JFrame(); 
							frame.add(new JScrollPane(restaurant.cheapestItems()));
							frame.setSize(500, 300); 
							frame.setLocationRelativeTo(null); 
							
							frame.setVisible(true);
					}
				});
				
				// most ppl foods
				JButton mostPopular = new JButton("Most Popular");
				mostPopular.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
							JFrame frame = new JFrame(); 
							frame.add(new JScrollPane(restaurant.mostPopular()));
							frame.setSize(500, 300); 
							frame.setLocationRelativeTo(null); 
							
							frame.setVisible(true);
					}
				});
				
				addOrder = new JButton("Make an Orders");
				addOrder.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int foodID = Integer.parseInt(JOptionPane.showInputDialog("Enter the foodID: "));
						if(foodID >= 0)
						{
							if(restaurant.addOrder(restaurant.getUser().getUserID(), foodID))
								JOptionPane.showMessageDialog(null, String.format(restaurant.getMessage()));
							else
								JOptionPane.showMessageDialog(null, "Error!");
						}
						else
							JOptionPane.showMessageDialog(null, String.format("All inputs could not be empty!"));
					}
				});
				
				addReservations = new JButton("Make an Reservations");
				addReservations.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int people = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of people: "));
						String resDate = JOptionPane.showInputDialog("Enter the resDate YYYY-MM-DD hh:mm: ");
						if(people >= 0 && !resDate.isEmpty())
						{
							if(restaurant.addReservations(restaurant.getUser().getUserID(), 0, people, resDate))
								JOptionPane.showMessageDialog(null, String.format("The Reservation has been created!"));
							else
								JOptionPane.showMessageDialog(null, String.format("Fail to create the Reservation"));
						}
						else
							JOptionPane.showMessageDialog(null, String.format("Passwords are not match~!"));
							
					}
				});
				
				this.add(sByName);
				this.add(sByPriceDec);
				this.add(sByPriceInc);
				this.add(sByCategories);
				this.add(lessPrice);
				this.add(greaterPrice);
				this.add(cheapestItems);
				this.add(mostPopular);
				this.add(addOrder);
				this.add(addReservations);
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
		this.add(password);
		this.setBackground(Color.DARK_GRAY);
	}
}
