/**
 * Creates the number pattern
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class NumberPattern {
  public static void main(String[] args) {
    int rows = 5;
    int number = 0;
    int count = 0;
    int count1 = 0;

    for (int i = 1; i <= rows; ++i) {
      for (int space = 1; space <= rows - i; ++space) {
        System.out.print("  ");
        ++count;
      }

      while (number != 2 * i - 1) {
        if (count <= rows - 1) {
          System.out.print((i + number) + " ");
          ++count;
        } else {
          ++count1;
          System.out.print((i + number - 2 * count1) + " ");
        }

        ++number;
      }
      count1 = count = number = 0;

      System.out.println();
    }
  }
}