import java.util.Scanner;
import java.util.Arrays;

/**
 * Checks the given Strings are anagrams or not
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class Anagrams {
    public static void main(String[] args) {
        String word1;
        String word2;
        boolean status = true;
        char[] array1;
        char[] array2;
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Enter the String1 : ");
        word1 = scanner.next();
        System.out.print("Enter the String2 : ");
        word2 = scanner.next();
        
        if (word1.length() != word2.length()) {
            status = false;
            System.out.println(word1 + " and " + word2 + " are not anagrams");
        } else {
            array1 = word1.toLowerCase().toCharArray();
            array2 = word2.toLowerCase().toCharArray();
            Arrays.sort(array1);
            Arrays.sort(array2);
            status = Arrays.equals(array1, array2);

            if (status) {
                System.out.println(word1 + " and " + word2 + " are anagrams ");
            } else {
                System.out.println(word1 + " and " + word2 + " are not anagrams");
            }
        } 
    }
}