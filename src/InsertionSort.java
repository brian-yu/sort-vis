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
		super.setTitle("Insertion Sort");
	}
	
	public InsertionSort(ArrayList<Integer> arr) {
		super(arr);
		super.setTitle("Insertion Sort");
	}

	@Override
	public void sort() throws InterruptedException {
		ArrayList<Integer> arr = super.getArray();
		for (int i=1; i < arr.size(); i++) {
			int j = i;
			while (j > 0 && arr.get(j-1) > arr.get(j)) {
				super.swap(arr, j, j-1);
				j--;
				super.display(j);
				Thread.sleep(super.getDelay());
			}		
		}
		super.display(-1);
	}
	
}
