package HW_8;
import java.util.Stack;

public class B08_02 {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false; // Немає пари
                }

                char top = stack.pop();

                if (!matches(top, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        String test = "{[()]}";

        if (isValid(test)) {
            System.out.println("Дужки розставлені правильно.");
        } else {
            System.out.println("Помилка в розстановці дужок.");
        }
    }

}
