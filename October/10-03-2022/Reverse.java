import java.util.Scanner;
/**
 * Reverse the given word
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class Reverse {
    public static void main(String[] args) {
        String word;
        String reverseWord = "";
        char character;
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the String : ");
        word = scanner.nextLine();
        
        for (int index = 0; index < word.length(); index++) {
            character = word.charAt(index);
            reverseWord = character+reverseWord;
        }
        System.out.println("Reversed word : " + reverseWord);
    }
}