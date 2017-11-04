/*
 * Brian Yu
 * HW 7: Sort Visualization
 * bry4xm
 * CS2110 Edwards
 * Sources used: Big Java, Toptal, Wikipedia
 */
import java.util.ArrayList;

public class InsertionSort extends Sort {

	private static final long serialVersionUID = 1L;

	public InsertionSort(int size) {
		super(size);
		super.text.setText("Insertion Sort");
	}

	@Override
	public void sort() throws InterruptedException {
		ArrayList<Integer> arr = super.getArray();
		for (int i=1; i < arr.size(); i++) {
			int current = arr.get(i);
			for (int j=i; j >= 0; j--) {
				if (current < arr.get(j)) {
					super.swap(arr, j, j+1);
				}
				super.display(j);
				Thread.sleep(super.getDelay());
			}			
		}
		super.display(-1);
	}
	
}
