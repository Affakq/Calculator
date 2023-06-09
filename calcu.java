import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите выражение:");
        String input = sc.nextLine();

        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    //
    public static String calc(String input) throws Exception {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new Exception("Неверный формат выражения");
        }

        //
        String number1Str = tokens[0];
        String operatorStr = tokens[1];
        String number2Str = tokens[2];

        int num1, num2;

        // Проверка на римские числа
        if (isValidRomanNumeral(number1Str) && isValidRomanNumeral(number2Str)) {
            num1 = romanToArabic(number1Str);
            num2 = romanToArabic(number2Str);
        }
        // Проверка на арабские числа
        else if (isInteger(number1Str) && isInteger(number2Str)) {
            num1 = Integer.parseInt(number1Str);
            num2 = Integer.parseInt(number2Str);
        }
        // Если используются разные системы счисления выбрасывает исключение
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        int result;
        switch (operatorStr) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                // Проверка деления на ноль
                if (num2 == 0) {
                    throw new Exception("Деление на ноль недопустимо");
                }
                result = num1 / num2;
                break;
            default:
                throw new Exception("Недопустимая операция: " + operatorStr);
        }

        // вывод результат ав виде строки
        if (isValidRomanNumeral(number1Str) && isValidRomanNumeral(number2Str)) {
            if (result <= 0) {
                throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }
            return arabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }
    //проверка является ли число римским
    private static boolean isValidRomanNumeral(String numberStr) {
        return numberStr.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }


    private static boolean isInteger(String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            return number >= 1 && number <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static int romanToArabic(String romanNumeral) {
        switch (romanNumeral) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new IllegalArgumentException("Неверный формат " + romanNumeral);
        }
    }

    private static String arabicToRoman(int number)
    {
        if (number < 1 || number > 100)
        {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 100 включительно");
        }
        int mem = 0;
        String[] romanSymbols = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",  "XX", "XXI", "XXIV","XXV","XXVII", "XXVIII", "XXX", "XXXII", "XXXV", "XXXVI", "XL", "XLII", "XLV", "XLVIII", "XLIX", "L", "LIV", "LVI", "LX", "LXIII", "LXIV", "LXX", "LXXII", "LXXX", "LXXXI", "XC", "C"};
        int IntArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 24, 25, 27, 28, 30, 32, 35, 36, 40, 42, 45, 48, 49, 50, 54, 56, 60, 63, 64, 70, 72, 80, 81, 90, 100};
        for (int i = 0; i < IntArray.length; i++)
        for (int i = 0; i < IntArray.length; i++)
        {
            if (IntArray[i] == number)
            {
                mem = i;
                break;
            }
        }
        return romanSymbols[mem];
    }
}
