/*
 * Brian Yu
 * HW 7: Sort Visualization
 * bry4xm
 * CS2110 Edwards
 * Sources used: Big Java, Toptal, Wikipedia
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class RectangleComponent extends JComponent {
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	public RectangleComponent(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  
		g.setColor(color);
		g.fillRect(x,y,width,height);  
	}
	
	public Dimension getPreferredSize() {
	    return new Dimension(width + 2 * x, height + 2 * y); // appropriate constants
	  }
	
}
