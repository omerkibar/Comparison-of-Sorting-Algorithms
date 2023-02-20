/*
Ömer Kibar
Sinan Göçmen
This class is used for testing the algorithms
For the purpose of this class every algorithm returns number of basic operations counted after finding a solution.
Some of original lines are commented.
*/
public class KthSmallestCount {
    //Sorts the array using insertion sort algorithm. While sorting it counts the number of basic operations.
    public static int byInsertionSort(int[] array, int k) {
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            //When this is the case even though comparison is done it wouldn't be counted in while loop. So we also count this case.
            if(array[j]<=key){
                count++;
            }
            //Basic operation is comparison in the while loop.
            while ((j > -1) && (array[j] > key)) {
                count++;
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        //return array[k - 1]; //Actual return value for the algorithm.
        return count;
    }

    public static int byMergeSort(int[] arr, int k) {
        //mergeSort(arr,arr.length);
        //return arr[k - 1]; //Actual return value for the algorithm.
        return mergeSort(arr, arr.length); //Returns the count value returned from mergeSort method.
    }
    //In mergeSort as a basic operation we choose comparison in the merge method.
    private static int mergeSort(int[] array, int length) {
        if (length < 2) {
            //return; //We normally just return when length<2.
            return 0; //We return 0 as the number of comparison in this case.
        }
        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];
        int k = 0;
        for (int i = 0; i < length; ++i) {
            if (i < mid) {
                left[i] = array[i];
            } else {
                right[k] = array[i];
                k = k + 1;
            }
        }
        //mergeSort(left, mid);
        //mergeSort(right, length - mid);
        //merge(left, right, array, mid, length - mid);
        //return;
        int count1 = mergeSort(left, mid); //The number of comparisons required to sort the left part of array is assigned to count1.
        int count2 = mergeSort(right, length - mid);//The number of comparisons required to sort the right part of array is assigned to count2.
        int count3 = merge(left, right, array, mid, length - mid); //The number of comparisons required to merge sorted left and right part.
        return count1 + count2 + count3;//Total number of comparisons is returned.
    }

    private static int merge(int[] left, int[] right, int[] array, int leftSize, int rightSize) {
        int count = 0;
        int i = 0, l = 0, r = 0;
        while (l < leftSize && r < rightSize) {
            count++;//Basic operation is the comparison in the following if block
            if (left[l] < right[r]) {
                array[i++] = left[l++];
            } else {
                array[i++] = right[r++];
            }
        }
        while (l < leftSize) {
            array[i++] = left[l++];
        }
        while (r < rightSize) {
            array[i++] = right[r++];
        }
        return count;
    }

    public static int byQuickSort(int[] array, int k) {
        //quickSort(array, 0, array.length - 1);
        //return array[k - 1];
        return quickSort(array, 0, array.length - 1); //Returns the count value returned from quickSort method.
    }
    //In quick sort the basic operation is comparison in the partition method.
    private static int quickSort(int[] array, int l, int r) {
        if (l < r) {
            int[] pivot_count = partition(array, l, r,"first");//Partition the array and get pivot index and the number of comparisons.
            int count1 = quickSort(array, l, pivot_count[0]-1);//Sort the left part and get number of comparisons.
            int count2 = quickSort(array, pivot_count[0] + 1, r);//Sort the right part and get number of comparisons.
            return count1+count2+pivot_count[1];//Return total number of comparisons.
        }
        return 0;//Return 0 as the number of comparisons when l>=r.
    }
    //Partition method returns both index of pivot and the number of comparisons. It returns an array of length 2. 0'th index is pivot index 1'st index is number of comparisons.
    //Since it is used for quickSelectMedian method it chooses pivot according to argument pivotSelect.
    private static int[] partition(int[] array, int l, int r,String pivotSelect)
    {
        int count = 0;
        if(pivotSelect.equals("median")){
            medianOfThree(array,l,r);
        }
        int pivot = array[l];
        int pivotIndex = r;
        for (int i = r; i > l; i--){
            count++;//Basic operation is the comparison in the following if block.
            if (array[i] > pivot){
                swap(array, i, pivotIndex);
                pivotIndex--;
            }
        }
        swap(array, l, pivotIndex);
        return new int[]{pivotIndex,count};
    }
    
    public static int byPartialSelectionSort(int[] array, int k) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                count++;//Basic operation is the comparison in the following if block.
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            swap(array, minIndex, i);
        }
        //return array[k - 1]; //Actual return value
        return count;
    }
    //In partial heap sort basic operation is the comparison in the heapify method.
    public static int byPartialHeapSort(int[] array,int k){
        int n = array.length;
        int count = 0;
        for (int i = n / 2 - 1; i >= 0; i--)
            count += heapify(array, n, i);//Number of comparisons while building a heap added to count value.
        for (int i = n - 1; i > k-1; i--) {
            swap(array, 0, i);
            count += heapify(array, i, 0);//Number of comparisons while removing root added to count value.
        }
        //return array[0]; //Actual return value
        return count;
    }
    /*In heapify method. There are two comparisons in if blocks as basic operations. 
    The number of comparisons executed is equal to the number of comparisons in recursive calls plus 2 from the if blocks.
    */ 
    private static int heapify(int array[], int n, int i)
    {
        int count = 0;
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
 
        if (l < n && array[l] > array[largest]){
            largest = l;
        }
        if (r < n && array[r] > array[largest]){
            largest = r;
        }
        if (largest != i) {
            swap(array, i, largest);
            count = heapify(array, n, largest); 
        }
        return count+2;
    }
    //In quick select algorithm the comparisons inside the partition is the basic operation. 
    public static int byQuickSelect(int arr[], int k) {
        //Normally quickSelect method returns the k'th element but we modified it to return the number of comparisons.
        return quickSelect(arr, 0, arr.length - 1, k - 1); //Returns the count value returned from quickSelect method.
    }
    
    private static int quickSelect(int[] array, int left, int right, int k) {
        if (left == right)
            //return array[left];
            return 0;//return 0 as the number of basic operation.

        int[] pivot_count = partition(array, left, right,"first");//Partition the array using first element as the pivot. Get the number of comparisons and the pivot index.
        int count = pivot_count[1];//Number of comparisons that are executed to partition the first index.

        if (k == pivot_count[0])
            //return array[k];//K'th element found.
            return count;
        if (k < pivot_count[0]){
            //return quickSelect(array, left, pivot_count[0] - 1, k); //K'th element in the left part.
            count += quickSelect(array, left, pivot_count[0] - 1, k); //Add the number of comparisons to the count while searching in the left part of array.
            return count;
        }
        //return quickSelect(array, pivot_count[0] + 1, right, k); //K'th element in the right part.
        count += quickSelect(array, pivot_count[0] + 1, right, k);//Add the number of comparisons to the count while searching in the right part of array.
        return count;//Return total number of comparisons
    }
    //In quick select median algorithm the comparisons inside the partition is the basic operation. 
    public static int byQuickSelectMedian(int arr[], int k) {
        //Normally quickSelectMedian method returns the k'th element but we modified it to return the number of comparisons.
        return quickSelectMedian(arr, 0, arr.length - 1, k - 1); //Returns the count value returned from quickSelectMedian method.
    }

    public static int quickSelectMedian(int[] array, int left, int right, int k) {
        if (left == right)
            //return array[left];
            return 0;//return 0 as the number of basic operation.
        int[] pivot_count = partition(array, left, right,"median");//Partition the array using median element as the pivot. Get the number of comparisons and the pivot index.
        int count = pivot_count[1];//Number of comparisons that are executed to partition the first index.
        if (k == pivot_count[0])
            //return array[k];//K'th element found.
            return count;
        if (k < pivot_count[0]){
            //return quickSelect(array, left, pivot_count[0] - 1, k); //K'th element in the left part.
            count += quickSelectMedian(array, left, pivot_count[0] - 1, k);//Add the number of comparisons to the count while searching in the left part of array.
            return count;
        }
        //return quickSelect(array, pivot_count[0] + 1, right, k); //K'th element in the right part.
        count += quickSelectMedian(array, pivot_count[0] + 1, right, k);//Add the number of comparisons to the count while searching in the right part of array.
        return count;//Return total number of comparisons.
    }
    //In the partition method first index is selected as pivot so we find the median of first, middle and last element and put it into first index. 
    private static void medianOfThree(int[] array,int l ,int r){
        if(array.length<3){
            return;
        }
        int a = array[l];
        int b = array[r];
        int c = array[(l+r)/2];
    
        if(a<b && b<c || c<b && b<a)
            swap(array, l, r);
        else if(a<c && c<b || b<c && c<a)
    	    swap(array,l,(l+r)/2);      
	}
    //Helper method to swap array elements
    private static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}