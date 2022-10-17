import java.util.Scanner;

/**
 * Check the given String is palindrome or not
 * 
 * @version 1.0
 * @author Venkatesh TM
 */
public class Palindrome {

  public static void main(String[] args) {
    String string = "";
    String reverseString = "";    
    int strLength;
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter the String : ");
    string = scanner.nextLine();
    strLength = string.length();
    for (int index = (strLength - 1); index >=0; --index) {
      reverseString = reverseString + string.charAt(index);
    }

    if (string.toLowerCase().equals(reverseString.toLowerCase())) {
      System.out.println(string + " is a Palindrome String.");
    }
    else {
      System.out.println(string + " is not a Palindrome String.");
    }
  }
}