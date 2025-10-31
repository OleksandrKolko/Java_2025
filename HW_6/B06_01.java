package HW_6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class B06_01 {
    public static void main(String[] args) {
        String text = "Сьогодні дата 02.10.2024, а тут підкреслення __.__.____ для дати.";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String currentDate = LocalDate.now().format(formatter);

        String regex = "(\\d{2}\\.\\d{2}\\.\\d{4}|__\\.__.____)";

        String result = text.replaceAll(regex, currentDate);

        System.out.println(result);
    }
}
