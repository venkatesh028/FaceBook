import java.util.Scanner;

public class Pattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target;
        System.out.print("Enter the number of rows : ");
        target = scanner.nextInt();
        
        for (int row = 0; row<= target-1; row++) {
            for (int col = 0; col <= row; col++) {
                System.out.print("*"+ " "); 
            }
             System.out.println("");
        }  
        
        for (int row = target-1; row>=0; row--) {
            for (int col = 0; col <= row - 1; col++) {
                System.out.print("*"+ " ");
            }
            System.out.println("");
        }
    }
}