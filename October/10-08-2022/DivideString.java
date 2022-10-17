public class DivideString {
    public static void main(String[] args) {
        String string = "aaaabbbbcccc";
        int length = string.length();
        int numberOfPiece = 3;
        int temp = 0;
        int characters = length/numberOfPiece;
        
        if (length % numberOfPiece != 0) {
            System.out.println("Sorry this string cannot be divided into "
                                + numberOfPiece +" equals parts ");
        } else {
            for(int index = 0; index < length; index = index+characters) {   
                String part = str.substring(i, i+chars);  
                equalStr[temp] = part;  
                temp++; 
            }
            System.out.println(numberOfPiece + " equal parts of given string are ");          
        }
        for(int i = 0; i < equalStr.length; i++) {  
                System.out.println(equalStr[i]);  
        }  

    }
}