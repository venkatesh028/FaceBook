/**
 * Trow the custom exception 
 *
 * @version 1.0
 * @author Venkatesh TM
 */
public class DemoException {

    public static void main(String [] args) {
    method1();
    System.out.println("Method1 completed");
    }

    public static void method1() {
       
        method2();
        System.out.println("lsjfsjf");
    }

    public static void method2() {
        
        try {
             int number = 10 / 0;
        } catch (ArithmeticException e) {
             throw new MyException("/ By zero is not possible");
        }
        System.out.println("Method2 completed");
    }
}

/**
 * In this custom class gives the custom message
 *
 */
class MyException extends RuntimeException {
     public MyException(String message) {
           super(message);
     }
}