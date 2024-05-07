import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KataCalculator {

    public static void main(String[] args) throws Exception {

//          boolean isCorrectExpression = false;

//        do {
            try {
                System.out.println("Введите выражение:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String expression = br.readLine();
                System.out.println(calc(expression));
//                isCorrectExpression = true;
            } catch (BadExpressionException badExpressionException) {
                System.out.println(badExpressionException.getMessage());
            }
//        } while (!isCorrectExpression);

    }

    public static String calc(String expression) throws BadExpressionException {
        boolean isRomanResult = false;
        int result = 0;
        int a, b;
        String[] expressionElements = ExpressionValidator.validate(expression);
        String typeOfOperands = ExpressionValidator.getTypeOfOperand();
        if (typeOfOperands.equals("roman")) {
            a = RomanArabicConverter.convertToArabic(expressionElements[0]);
            b = RomanArabicConverter.convertToArabic(expressionElements[2]);
            isRomanResult = true;
        } else {
            a = Integer.parseInt(expressionElements[0]);
            b = Integer.parseInt(expressionElements[2]);
        }

        String operation = expressionElements[1];

        switch (operation) {
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
        }
        if (isRomanResult == true) {
            return RomanArabicConverter.convertToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

}
