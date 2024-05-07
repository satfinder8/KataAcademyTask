
public class ExpressionValidator {

    private static String[] expressionElements;
    private static final int countOfOperands = 2;
    private static final String regexRoman = "X|IX|IV|V?I{0,3}";
    private static final String regexArabic = "[1-9]{1}|10{1}";
    private static final String regexOperation = "[\\*,\\/,\\-,\\+]{1}";
    private static String typeOfOperand = null;

    public static String[] validate(String expression) throws BadExpressionException {
        expressionElements = expression.split(" ");
        if(expressionElements.length != countOfOperands * 2 - 1) {
            throw new BadExpressionException("Ошибка! Возможно указать только "
                    + countOfOperands + " операнда и знак операции между ними, разделенные одним пробелом");
        }
        if(!validateOperands(expressionElements)) {
            throw new BadExpressionException("Ошибка! Оба операнда должны быть целыми числами от 1 до 10," +
                    "записанными одновременно либо арабскими либо римскими цифрами (заглавными I,V,X)");
        }
        if(!validateOperations(expressionElements)) {
            throw new BadExpressionException("Ошибка! Поддерживаются только 4 арифметические операции (+, -, *, /)");
        }
        return getExpressionElements();
    }

    private static boolean validateOperands(String[] expressionElements) {
        checkTypeOfOperands(expressionElements);
        for (int i = 2; i < countOfOperands * 2; i = i + 2) {
            if((("roman").equals(typeOfOperand)
                    && expressionElements[i].matches(regexRoman))
                || (("arabic").equals(typeOfOperand)
                    && expressionElements[i].matches(regexArabic))) {
                return true;
            } else {
                typeOfOperand = null;
            }
        }
        return false;
    }

    private static void checkTypeOfOperands(String[] expressionElements) {
        if (expressionElements[0].matches(regexRoman)) {
            typeOfOperand = "roman";
        } else if (expressionElements[0].matches(regexArabic)) {
            typeOfOperand = "arabic";
        }
    }

    private static boolean validateOperations(String[] expressionElements) {
        for (int i = 1; i < countOfOperands; i = i + 2) {
            if(!(expressionElements[i].matches(regexOperation))) {
                return false;
            }
        }
        return true;
    }

    private static String[] getExpressionElements() {
        return expressionElements;
    }

    public static String getTypeOfOperand() {
        return typeOfOperand;
    }
}
