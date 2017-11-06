/*
 * Brian Yu
 * HW 7: Sort Visualization
 * bry4xm
 * CS2110 Edwards
 * Sources used: Big Java, Toptal, Wikipedia, https://krazydad.com/tutorials/makecolors.php
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class Sort extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> array;
	private Color[] colors;
	private Map<Integer, Color> colorMap;
	private int size;
	private int delay;
	
	private JPanel title;
	private JLabel text;
	private JPanel visual;
	private Font font;
	
	public Sort(int size) {
		
		title = new JPanel();
		text = new JLabel("Sort");
		font = new Font("Futura", Font.BOLD, 15);
		visual = new JPanel();
		
		visual.setSize(500, 480);
		setSize(500, 500);
		setLayout(new BorderLayout());
		title.setSize(50, 20);
		text.setFont(font);
		title.add(text);
		visual.setLayout(new GridLayout(0,1));
		add(title, BorderLayout.NORTH);
		add(visual, BorderLayout.CENTER);
		setBorder(new EmptyBorder(15,15,15,15));
		setBackground(Color.white);
		title.setBackground(Color.white);
		visual.setBackground(Color.white);
				
		this.size = size;
		array = new ArrayList<>();
		colorMap = new HashMap<>();
		colors = gradient(.3,.3,.3,0,2,4, 176, 79, size);
		// Map elements to gradient colors
		for (int i=1; i <= size; i++) {
			array.add(i);
			colorMap.put(i, colors[i-1]);
		}
		// Shuffle array
		Collections.shuffle(array);
		display(-1);
		delay = 50;
		
		// Re-render display after resize
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				display(-1);
			}
		});
	}
	
	public Sort(ArrayList<Integer> arr) {
		this(arr.size());
		array = new ArrayList<>(arr);
		display(-1);
	}
	
	public abstract void sort() throws InterruptedException;
	
	// The display function
	public void display(int current) {		
		visual.removeAll();
		
		for (int i = 0; i < array.size(); i++) {
			int value = array.get(i);
			// color is red if index is current iteration index
			Color color = current == i ? Color.red : Color.white;
			JPanel container = new JPanel();
			container.setBackground(Color.white);
			container.setSize(visual.getWidth(), visual.getHeight()/size);
			container.setLayout(new BorderLayout());
			RectangleComponent mark = new RectangleComponent(0, 0, 10, visual.getHeight()/size, color); // Element marker
			RectangleComponent rect = new RectangleComponent(0, 0, (visual.getWidth()-10)/size*value, visual.getHeight()/size, colorMap.get(value));
			
			container.add(mark, BorderLayout.WEST);
			container.add(rect, BorderLayout.CENTER);
			visual.add(container);
		}
		
		visual.validate();
	}
	
	public void run() {
		try {
			sort();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getArray() {
		return array;
	}
	
	// A function that swaps values in the ArrayList
	public void swap(ArrayList<Integer> arr, int i, int j) {
		int temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
	}
	
	// The delay in milliseconds between iterations
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	// A function that generates an array containing a color gradient. Found at https://krazydad.com/tutorials/makecolors.php
	private Color[] gradient(double f1, double f2, double f3, int p1, int p2, int p3, int center, int width, int len) {
		Color[] colors = new Color[len];
		for (int i = 0; i < len; i++) {
			int red = (int) (Math.sin(f1*i + p1) * width + center);
			int green = (int) (Math.sin(f2*i + p2) * width + center);
			int blue = (int) (Math.sin(f3*i + p3) * width + center);
			colors[i] = new Color(red, green, blue);
		}
		return colors;
	}
	
	public void setTitle(String title) {
		text.setText(title);
	}

}

