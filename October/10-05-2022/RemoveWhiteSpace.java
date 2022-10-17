import java.util.Scanner;

/**
 * Remove the white spaces in the given string
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class RemoveWhiteSpace {

    public static String removeWhiteSpaces(String input){
        StringBuilder output = new StringBuilder();
	
	char[] charArray = input.toCharArray();
	
	for(char character : charArray) {
		if (!Character.isWhitespace(character))
			output.append(character);
	}
	
	return output.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence;

        System.out.print("Enter the String : ");
        sentence = scanner.nextLine();
        System.out.println(removeWhiteSpaces(sentence));                  
    }
}