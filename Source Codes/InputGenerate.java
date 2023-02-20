import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
/*
Ömer Kibar
Sinan Göçmen
This class is for generating input that has different characteristics.
It creates arrays in given size and writes them into a text file.
*/
public class InputGenerate {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = Integer.parseInt(input.nextLine());
        System.out.print("Enter number of arrays to generate: ");
        int numberOfArrays = Integer.parseInt(input.nextLine());
        System.out.print("Enter output file name: ");
        String outputFileName = input.nextLine();
        
        //generateRandomArrays(size, numberOfArrays, size*10, outputFileName);
        generateAlmostSortedArrays(size,numberOfArrays,size*10,outputFileName,"descending");
        //generateAlmostSortedArrays(size,numberOfArrays,size*10,outputFileName,"ascending");

        input.close();
    }
    //Using this method we generate random arrays.
    static void generateRandomArrays(int size,int numberOfArrays,int range,String outputFileName) throws Exception{
        FileWriter output = new FileWriter(new File(outputFileName));
        for(int i = 0;i<numberOfArrays;i++){
            int[] randomArray = generateRandomArray(size, range);
            String s = "";
            for (int j : randomArray) {
                s += j+",";
            }
            s.substring(0,s.length()-1);
            output.write(s+"\n");
        }
        output.close();
    }
    //Using this method we generated our sorted, reversely sorted,almost sorted,almost reversely sorted arrays.
    static void generateAlmostSortedArrays(int size,int numberOfArrays,int range,String outputFileName,String order) throws Exception{
        FileWriter output = new FileWriter(new File(outputFileName));
        for (int i = 0; i < numberOfArrays; i++) {
            int[] array = generateRandomArray(size,range);
            mergeSort(array, array.length);
            if(order.equals("descending")){
                reverse(array);
            }
            //randomize10Percent(array,range); //Activate when creating almost sorted arrays
            String s = "";
            for (int j : array) {
                s += j+",";
            }
            s = s.substring(0,s.length()-1);
            output.write(s+"\n");
        }
        output.close();
    }
    //Method for generating random array
    static int[] generateRandomArray(int size,int range){
        int[] array = new int[size];
        for(int i = 0;i<size;i++){
            array[i] = (int)(Math.random()*range);
        }
        return array;
    }
    //To generate almost sorted or almost reversely sorted we put sorted array to this method and it randomizes it by choosing some of indexes and assigning random values to them. 
    static void randomize10Percent(int[] array,int range){
        for (int i = 0; i < (array.length*0.1); i++) {
            int k = (int)(Math.random()*array.length);
            array[k] = (int)(Math.random()*range);
        }
    }
    //Reverses the array used for generating reversely sorted arrays.
    static void reverse(int[] array){
        int[] temp = new int[array.length];
        int j = array.length;
        for (int i = 0; i < array.length; i++) {
            temp[j - 1] = array[i];
            j = j - 1;
        } 
        for (int i = 0; i < array.length; i++) {
            array[i] = temp[i];
        } 
    }

    //We use merge sort algorithm to sort random array to generate sorted arrays.
    static void mergeSort(int[] array, int length) {
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

    static void merge(int[] left, int[] right, int[] array, int leftSize, int rightSize) {
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
}