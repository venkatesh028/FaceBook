import java.util.Scanner;

/**
 * Searching is done based on the linear search 
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class LinearSearch {
    public static int linearSearch(int[] arr, int key) {
        
        for (int index =0; index < arr.length; index++) {
            if (arr[index] == key) {
                return index;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) { 
        int key;
        int[] arr = {10,20,30,50,70,90,100};
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number which you need to check : ");
        key = scanner.nextInt();
        
        if (linearSearch(arr, key) >= 0) {
            System.out.println(key + " is Found at index : " + linearSearch(arr, key)); 
        } else {
            System.out.println("No this number is not available");
        }
    }
}
