/**
 * Trying to get the elements at the odd position
 *
 * @author Venkatesh TM
 * @version 1.0 
 * @since 07-OCT-2022
 */
public class OddPosition {
    public static void main(String[] args) {
        int[] array = new int[] {1,2,3,4,5};
        System.out.println("Elements of given array present on odd position ");
        
        for (int index = 0; index < array.length; index = index +2) {
            System.out.println(array[index]);
        }
    
    }
}