package HW_6;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class B06_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть арифметичний вираз:");
        String input = sc.nextLine();

        String expr = input.replaceAll("\\s+", "");

        String regex = "^[+-]?\\d+(?:[+\\-*/][+-]?\\d+)*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expr);

        if (matcher.matches()) {
            System.out.println("Вираз синтаксично правильний");
        } else {
            System.out.println("Вираз синтаксично НЕправильний");
        }
    }
}
