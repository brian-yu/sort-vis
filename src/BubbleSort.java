/*
 * Brian Yu
 * HW 7: Sort Visualization
 * bry4xm
 * CS2110 Edwards
 * Sources used: Big Java, Toptal, Wikipedia
 */
import java.util.ArrayList;

public class BubbleSort extends Sort {

	private static final long serialVersionUID = 1L;

	public BubbleSort(int size) {
		super(size);
		super.setTitle("Bubble Sort");
	}
	
	public BubbleSort(ArrayList<Integer> arr) {
		super(arr);
		super.setTitle("Bubble Sort");
	}

	@Override
	public void sort() throws InterruptedException {
		ArrayList<Integer> arr = super.getArray();
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 1; i < arr.size(); i++) {
				if (arr.get(i-1) > arr.get(i)) {
					super.swap(arr, i-1, i);
					swapped = true;
				}
				super.display(i);
				Thread.sleep(super.getDelay());
			}
		}
		super.display(-1);
	}
	
}
