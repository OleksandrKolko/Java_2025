package HW_11;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

public class B11_10{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву міста англійською (наприклад, kyiv): ");
        String city = scanner.nextLine().trim().toLowerCase();

        if (city.isEmpty()) {
            city = "kyiv";
        }

        try {
            String url = "https://www.timeanddate.com/weather/ukraine/" + city;
            System.out.println("\nОтримання даних з: " + url);

            Document doc = Jsoup.connect(url)
                    .timeout(10000)
                    .get();

            System.out.println("\nЗаголовок сторінки: " + doc.title());
            System.out.println("==============================================");

            System.out.println("\nСпроба знайти прогноз погоди...");

            Elements tables = doc.select("table");
            System.out.println("Знайдено таблиць: " + tables.size());

            boolean foundForecast = false;

            for (int i = 0; i < tables.size(); i++) {
                Element table = tables.get(i);
                String tableHtml = table.outerHtml();

                if (tableHtml.contains("°C") || tableHtml.contains("°F") ||
                        tableHtml.toLowerCase().contains("temp") ||
                        tableHtml.toLowerCase().contains("humidity")) {

                    System.out.println("\n=== ТАБЛИЦЯ " + (i+1) + " (потенційний прогноз) ===");

                    Elements rows = table.select("tr");
                    int count = 0;

                    for (Element row : rows) {
                        if (row.text().trim().isEmpty()) continue;

                        System.out.println("Рядок " + (count+1) + ": " + row.text().trim());
                        count++;

                        if (count >= 16) {
                            break;
                        }
                    }

                    foundForecast = true;
                    break;
                }
            }

            if (!foundForecast) {
                System.out.println("\nНе вдалося знайти таблицю з прогнозом.");
                System.out.println("\n=== ВСІ ТАБЛИЦІ НА СТОРІНЦІ ===");

                Elements allTables = doc.select("table");
                for (int i = 0; i < Math.min(allTables.size(), 5); i++) {
                    System.out.println("\nТаблиця " + (i+1) + ":");
                    System.out.println(allTables.get(i).text().substring(0, Math.min(200, allTables.get(i).text().length())) + "...");
                }

                System.out.println("\n=== ПОШУК ТЕМПЕРАТУРИ В ІНШИХ ЕЛЕМЕНТАХ ===");
                Elements tempElements = doc.select(":contains(°C), :contains(°F), :contains(temp), :contains(Temp)");
                for (int i = 0; i < Math.min(tempElements.size(), 10); i++) {
                    String text = tempElements.get(i).text().trim();
                    if (text.length() > 5 && text.length() < 100) {
                        System.out.println("Знайдено: " + text);
                    }
                }
            }

            System.out.println("\n==============================================");
            System.out.println("Примітка: Якщо прогноз не відображається правильно,");
            System.out.println("сайт, можливо, змінив свою структуру.");
            System.out.println("Спробуйте використати:");
            System.out.println("1. Інше місто (kyiv, lviv, odesa)");
            System.out.println("2. Альтернативний сайт для парсингу");

        } catch (Exception e) {
            System.out.println("\nПомилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}