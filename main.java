import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(calc());
    }
    public static int calc() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение ");
        String input = sc.nextLine();
        input = input.replaceAll("\\s+", ""); // убираем пробелы

        String[] symbols = {"+", "-", "*", "/"};
        String delimiter = "";

        for (String smb :symbols){
            // находим разделитель
            if (input.contains(smb))
                delimiter = smb;
        }
        String[] numbers = input.split("\\"+delimiter); // создаем массив из строки

        int first = parseDecimal(numbers[0]); // происвоение значения переменной 1
        int second = parseDecimal(numbers[1]);

        int result;

        //System.out.println(input);

        switch (delimiter){
            case "+": {
                result = first + second;
                break;
            }
            case "-": {
                result = first - second;
                break;
            }case "*": {
                result = first * second;
                break;
            }case "/": {
                result = first / second;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected delimiter");
        }
        if (first > 0 && first < 11 && second > 0 && second < 11){
            return result;
        }else throw new IllegalStateException("Unexpected numbers");
    }

    private static Integer romanToDecimal(String romanNum){
        int decimal = 0;
        int lastNum =0;

        romanNum = romanNum.toUpperCase(); // верхний регистор
        for (int x = romanNum.length() - 1; x >= 0; x--){
            char symbol = romanNum.charAt(x);
            switch (symbol){
                // преобразование значений
                case  'X':
                    decimal = processDecimal(10, lastNum, decimal); 
                    lastNum = 10;
                    break;

                case  'V':
                    decimal = processDecimal(5, lastNum, decimal);
                    lastNum = 5;
                    break;

                case  'I':
                    decimal = processDecimal(1, lastNum, decimal);
                    lastNum = 1;
                    break;
            }
        }
        return decimal;
    }
    public static int parseDecimal(String str){
        // римские или арабские?
        char symbol = str.toCharArray()[0];

        if (symbol == 'X' || symbol == 'Y' || symbol == 'I' ){
           return romanToDecimal(str);
        } else {
            return Integer.parseInt(str);
        }
    }
    public static int processDecimal(int decimal, int lastNum, int lastDecimal){
        // фактическое преобразование
        if (lastNum>decimal){
            return lastDecimal-decimal;
        }else {
            return lastDecimal+decimal;
        }
    }
}
