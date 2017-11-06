/*
 * Brian Yu
 * HW 7: Sort Visualization
 * bry4xm
 * CS2110 Edwards
 * Sources used: Big Java, Toptal, Wikipedia
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SortVisualDriver {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Sort Visualizations");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel sortVisuals = new JPanel();
		sortVisuals.setLayout(new GridLayout(1,3));
		
		sortVisuals.setSize(1500, 500);
		
		frame.add(sortVisuals, BorderLayout.CENTER);
		
		int numItems = 25;
		
		ArrayList<Integer> rand = new ArrayList<>();
		for (int i = 1; i <= numItems; i++) {
			rand.add(i);
		}
		Collections.shuffle(rand);
		
		Sort bubble = new BubbleSort(rand);
		Sort selection = new SelectionSort(rand);
		Sort insertion = new InsertionSort(rand);
		
		sortVisuals.add(bubble);
		sortVisuals.add(selection);
		sortVisuals.add(insertion);
		
		Thread bubbleThread = new Thread(bubble);
		Thread selectionThread = new Thread(selection);
		Thread insertionThread = new Thread(insertion);
		
		JButton toggle = new JButton("Start");
		
		// Toggles between start and exit button upon being run
		toggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (b.getText().equals("Start")) {
					b.setText("Exit");
					bubbleThread.start();
					selectionThread.start();
					insertionThread.start();
				} else if (b.getText().equals("Exit")) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		
		int defaultDelay = 50;
		
		JSlider delay = new JSlider(JSlider.HORIZONTAL, 10, 300, defaultDelay);
		JLabel delayLabel = new JLabel("Delay between iterations: " + defaultDelay + " ms");
		
		delay.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				bubble.setDelay((int)source.getValue());
				selection.setDelay((int)source.getValue());
				insertion.setDelay((int)source.getValue());
				delayLabel.setText("Delay between iterations: " + (int)source.getValue() + " ms");
			}
			
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.setSize(50, 20);
		buttonPanel.add(toggle);
		buttonPanel.add(delay);
		buttonPanel.add(delayLabel);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.setSize(1500, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
