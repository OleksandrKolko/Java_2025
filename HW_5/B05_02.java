package HW_5;
import java.util.Scanner;

public class B05_02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть рядок (тільки літери та цифри):");
        String s = sc.nextLine();

        boolean a = checkA(s);
        boolean b = checkB(s);
        boolean c = checkC(s);

        System.out.println("\nРезультати перевірки:");
        System.out.println("a) " + a);
        System.out.println("b) " + b);
        System.out.println("c) " + c);
    }

    public static boolean checkA(String s) {
        if (s.isEmpty() || !Character.isDigit(s.charAt(0))) return false;
        int firstDigit = s.charAt(0) - '0';
        if (firstDigit == 0) return false; // ненульова
        String rest = s.substring(1);
        if (!rest.matches("[A-Za-z]+")) return false;
        return rest.length() == firstDigit;
    }

    public static boolean checkB(String s) {
        int digitCount = 0;
        int digitValue = -1;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
                digitValue = c - '0';
            }
        }
        return digitCount == 1 && digitValue == s.length();
    }

    public static boolean checkC(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += c - '0';
            }
        }
        return sum == s.length();
    }
}