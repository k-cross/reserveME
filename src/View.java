import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class View extends JPanel
{
	private JScrollPane view = new JScrollPane();
	public View()
	{
		this.setBackground(Color.GRAY);
		this.add(view);
	}
	
	public void setView(JScrollPane newView)
	{
		this.view = newView;
	}
}
