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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		Sort bubble = new BubbleSort(50);
		Sort selection = new SelectionSort(50);
		Sort insertion = new InsertionSort(50);
		
		sortVisuals.add(bubble);
		sortVisuals.add(selection);
		sortVisuals.add(insertion);
		
		Thread bubbleThread = new Thread(bubble);
		Thread selectionThread = new Thread(selection);
		Thread insertionThread = new Thread(insertion);
		
		JButton toggle = new JButton("Start");
		
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
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.setSize(50, 20);
		buttonPanel.add(toggle);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.setSize(1500, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
