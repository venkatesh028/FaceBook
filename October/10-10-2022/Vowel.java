import java.util.Scanner;

/**
 * Finds the vowel in the given String
 * 
 * @version 1.0 10-10-2022
 * @author Venkatesh TM
 */
public class Vowel {  
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    String string = new String();

    System.out.println("Enter the String : ");    
    string = scanner.nextLine();
    for(int index=0; index < string.length(); index++) {
      if(string.charAt(index) == 'a'|| string.charAt(index) == 'e'|| 
          string.charAt(index) == 'i' || string.charAt(index) == 'o' || 
          string.charAt(index) == 'u')
        
        System.out.println("Given string contains " + 
            string.charAt(index)+" at the index " + index);
    }
  }
}
