import java.util.Scanner;

/**
 * Checks the given number is neon number or not
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class NeonNumber {

    public boolean isNeonNumber(int number) {
        int square = number * number;
        int sumOfSquareDigits = 0;

        while(square > 0) {
            sumOfSquareDigits = sumOfSquareDigits + square % 10;
            square = square / 10;    
        }
        
        if (sumOfSquareDigits == number) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int number;
        NeonNumber neonNumber = new NeonNumber();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter any number : ");
        number = scanner.nextInt();
        
        if (neonNumber.isNeonNumber(number)) {
            System.out.print(number + " Is a Neon Number ");
        } else {
            System.out.print(number + " Is not a Neon Number ");
        }
    }
}