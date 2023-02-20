/*
Ömer Kibar
Sinan Göçmen
*/
public class KthSmallest{
    public static int byInsertionSort(int[] array, int k) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while ((j > -1) && (array[j] > key)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array[k - 1];
    }

    public static int byMergeSort(int[] arr, int k) {
        mergeSort(arr, arr.length);//
        return arr[k - 1];
    }

    private static void mergeSort(int[] array, int length) {
        if (length < 2) {
            return;
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
        mergeSort(left, mid);
        mergeSort(right, length - mid);
        merge(left, right, array, mid, length - mid);
    }

    private static void merge(int[] left, int[] right, int[] array, int leftSize, int rightSize) {
        int i = 0, l = 0, r = 0;
        while (l < leftSize && r < rightSize) {
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
    }

    public static int byQuickSort(int[] array, int k) {
        quickSort(array, 0, array.length - 1);
        return array[k - 1];
    }

    private static void quickSort(int[] array, int l, int r) {
        if (l < r) {
            int pivot = partition(array, l, r,"first");
            quickSort(array, l, pivot-1);
            quickSort(array, pivot + 1, r);
        }
    }

    private static int partition(int[] array, int l, int r,String pivotSelect)
    {
        if(pivotSelect.equals("median")){
            medianOfThree(array,l,r);
        }
        int pivot = array[l];
        int pivotIndex = r;
        for (int i = r; i > l; i--){
            if (array[i] > pivot){
                swap(array, i, pivotIndex);
                pivotIndex--;
            }
        }
        swap(array, l, pivotIndex);
        return pivotIndex;
    }

    public static int byPartialSelectionSort(int[] array, int k) {
        for (int i = 0; i < k; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            swap(array, minIndex, i);
        }
        return array[k - 1];
    }

    public static int byPartialHeapSort(int[] array,int k){
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
        for (int i = n - 1; i > k-1; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
        return array[0];
    }
    private static void heapify(int array[], int n, int i)
    {
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
            heapify(array, n, largest);
        }
    }

    public static int byQuickSelect(int arr[], int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] array, int left, int right, int k) {
        if (left == right)
            return array[left];

        int pivot = partition(array, left, right,"first");
        if (k == pivot)
            return array[k];
        if (k < pivot){
            return quickSelect(array, left, pivot - 1, k);
        }
        return quickSelect(array, pivot + 1, right, k);
    }

    public static int byQuickSelectMedian(int arr[], int k) {
        return quickSelectMedian(arr, 0, arr.length - 1, k - 1);
    }

    public static int quickSelectMedian(int[] array, int left, int right, int k) {
        if (left == right)
            return 0;
        int pivot = partition(array, left, right,"median");
        if (k == pivot)
            return array[k];
        if (k < pivot){
            return quickSelectMedian(array, left, pivot - 1, k);
        }
        return quickSelectMedian(array, pivot + 1, right, k);
    }
    
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

    private static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}