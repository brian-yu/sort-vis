/*
 * Brian Yu
 * HW 7: Sort Visualization
 * bry4xm
 * CS2110 Edwards
 * Sources used: Big Java, Toptal, Wikipedia
 */
import java.util.ArrayList;

public class SelectionSort extends Sort {
	
	private static final long serialVersionUID = 1L;

	public SelectionSort(int size) {
		super(size);
		super.text.setText("Selection Sort");
	}

	@Override
	public void sort() throws InterruptedException {
		ArrayList<Integer> arr = super.getArray();
		for (int i = 0; i < arr.size() - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.size(); j++) {
				if (arr.get(j) < arr.get(minIndex)) {
					minIndex = j;
				}
				super.display(j);
				Thread.sleep(super.getDelay());
			}
			if (minIndex != i) {
				super.swap(arr, i, minIndex);
			}
		}
		super.display(-1);
	}
	
}
