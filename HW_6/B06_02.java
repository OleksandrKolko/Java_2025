package HW_6;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B06_02 {
    public static void main(String[] args) {
        String text = "Мої телефони: +380501234567, 380501234567, (050)123-45-67, 050-123-45-67, " +
                "0501234567. Також є +38(067)777-88-99 і інший номер +380-63-555-44-33";

        String regex = "(\\+?\\d{2})?\\s*\\(?\\d{2,3}\\)?[-\\s]?\\d{2,3}[-\\s]?\\d{2}[-\\s]?\\d{2}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Знайдені телефони:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
