package HW_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B11_01 {
    public static void main(String[] args) {
        try {
            String url = "https://www.timeanddate.com/worldclock/ukraine/kyiv";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String html = response.toString();
            Pattern pattern = Pattern.compile("<span id=ct class=h1>([0-9:]+)</span>");
            Matcher matcher = pattern.matcher(html);

            if (!matcher.find()) {
                System.out.println("Не вдалося знайти час на сайті");
                return;
            }

            String webTimeStr = matcher.group(1);
            System.out.println("Час з веб-сайту: " + webTimeStr);

            LocalTime localTime = LocalTime.now();
            String localTimeStr = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Локальний час: " + localTimeStr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime webTime = LocalTime.parse(webTimeStr, formatter);

            long secondsDifference = Math.abs(ChronoUnit.SECONDS.between(webTime, localTime));

            System.out.println("\nРезультат перевірки:");
            System.out.println("Різниця в часі: " + secondsDifference + " секунд");

            if (secondsDifference <= 1) {
                System.out.println("✓ Час відповідає точному часу");
            } else {
                System.out.println("✗ Час не відповідає точному часу");
            }

        } catch (Exception e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }
}