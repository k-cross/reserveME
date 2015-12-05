import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Window extends JFrame
{
	private Controller controller;
	private Restaurant restaurant;
	
	public Window(Restaurant restaurant)
	{
		this.restaurant = restaurant;
		this.controller = new Controller(this.restaurant);
		this.setTitle(this.restaurant.getUser().getType() + " | Dashboard");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(300,600);
		this.setVisible(true);
		
		this.add(controller);
	}
}
