import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
/*
Ömer Kibar 150119037
Sinan Göçmen 150120519
Using this class we make our experiments.
It tests for all inputs that is in inputFiles array.
It prints number of comparisons to the output file for each input file and for each array inside input files using KthSmallestCount class.
Before starting the experiment the algorithm that will be tested should be choosen
*/
public class Test {
    public static void main(String[] args) throws Exception {
        String[] inputFiles = new String[] {
                "random1000.txt", "random2000.txt", "random3000.txt", "random4000.txt",
                "almostsorted1000.txt", "almostsorted2000.txt", "almostsorted3000.txt", "almostsorted4000.txt",
                "almostsortedr1000.txt", "almostsortedr2000.txt", "almostsortedr3000.txt", "almostsortedr4000.txt",
                "sorted1000.txt", "sorted2000.txt", "sorted3000.txt", "sorted4000.txt",
                "rsorted1000.txt", "rsorted2000.txt", "rsorted3000.txt", "rsorted4000.txt"
        };
        for (int i = 0; i < inputFiles.length; i++) {
            //Makes test for k=1, k=n/2 and k=n
            for (int j = 0; j < 3; j++) {
                Scanner fileInput = new Scanner(new File(inputFiles[i]));
                FileWriter output = new FileWriter(new File("o"+j+"_"+inputFiles[i]));
                while (fileInput.hasNext()) {
                    String[] inputArray = fileInput.nextLine().split(",");
                    int[] array = new int[inputArray.length];
                    for (int a = 0; a < array.length; a++) {
                        array[a] = Integer.parseInt(inputArray[a]);
                    }
                    int k = 0;
                    switch (j) {
                        case 0:
                            k = 1;
                            break;
                        case 1:
                            k = array.length/2;
                            break;
                        default:
                            k = array.length;
                    }
                    //Choose one of algorithms to test 
                    int count = KthSmallestCount.byInsertionSort(array, k);
                    output.write(count + "\n");
                }
                fileInput.close();
                output.close();
            }
        }
    }
}