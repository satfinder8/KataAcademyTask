import java.util.HashMap;
import java.util.Map;

public class RomanArabicConverter {

    static int numbers[]  = {1, 4, 5, 9, 10, 40, 50, 90, 100};
    static String letters[]  = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};

    public static int convertToArabic(String expressionElement) {

        Map<Character,Integer> numbersMap = new HashMap<>();
        numbersMap.put('I',1);
        numbersMap.put('V',5);
        numbersMap.put('X',10);
        numbersMap.put('L',50);
        numbersMap.put('C',100);

        final int[] result = {0};

        for (int i = 0; i < expressionElement.length(); i++) {
            char c = expressionElement.charAt(i);
            char next = i < expressionElement.length() - 1
                    ? expressionElement.charAt(i + 1) : ' ';
            if (numbersMap.get(c) < numbersMap.getOrDefault(next, 0)) {
                result[0] -= numbersMap.get(c);
            }
            else {
                result[0] += numbersMap.get(c);
            }
        }

        return result[0];
    }

    public static String convertToRoman(int arabicNumber) throws BadExpressionException {

        String romanValue = "";
        if (arabicNumber <= 0) {
            throw new BadExpressionException("Ответ равен или меньше 0. Не может быть отображен в римской нотации");
        }
        while ( arabicNumber > 0 ){
            for (int i = 0; i < numbers.length; i++){
                if ( arabicNumber < numbers[i] ){
                    arabicNumber -= numbers[i-1];
                    romanValue += letters[i-1];
                    break;
                }
            }
        }
        return romanValue;
    }

}