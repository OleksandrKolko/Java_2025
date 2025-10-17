package HW_5;
import java.util.Scanner;

public class B05_01 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введіть рядок:");
            String input = sc.nextLine();

            if (!isParenthesesValid(input)) {
                System.out.println("Помилка: дужки розставлено неправильно або вкладені дужки виявлено.");
            } else {
                String result = removeTextInParentheses(input);
                System.out.println("Результат: " + result);
            }
        }

        public static boolean isParenthesesValid(String s) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    if (count > 0) return false;
                    count++;
                } else if (c == ')') {
                    count--;
                    if (count < 0) return false;
                }
            }
            return count == 0;
        }


        public static String removeTextInParentheses(String s) {
            StringBuilder result = new StringBuilder();
            boolean insideParentheses = false;

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    insideParentheses = true;
                } else if (c == ')') {
                    insideParentheses = false;
                } else if (!insideParentheses) {
                    result.append(c);
                }
            }
            return result.toString();
        }
    }