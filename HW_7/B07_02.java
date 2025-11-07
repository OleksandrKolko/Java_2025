package HW_7;

import java.io.*;
import java.util.*;

public class B07_02 {


/* Serializable потрібен для бінарного збереження об'єктів.*/
    public static class Toy implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private double price;
        private int minAge;
        private int maxAge;

        public Toy(String name, double price, int minAge, int maxAge) {
            this.name = name;
            this.price = price;
            this.minAge = minAge;
            this.maxAge = maxAge;
        }

        public boolean isSuitableForAge(int age) {
            return age >= minAge && age <= maxAge;
        }

        @Override
        public String toString() {
            return String.format("%s (%.2f грн, %d-%d років)", name, price, minAge, maxAge);
        }
    }

    public static void createToysFile(String filename, List<Toy> toys) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(toys);
            System.out.println("Файл \"" + filename + "\" створено успішно (" + toys.size() + " елементів).");
        } catch (IOException e) {
            System.err.println("Помилка запису у файл \"" + filename + "\": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Toy> readToysFile(String filename) {
        List<Toy> toys = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("Файл \"" + filename + "\" не знайдено.");
            return toys;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                toys = (List<Toy>) obj;
            } else {
                System.err.println("Невірний формат файлу: очікувався List<Toy>.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Помилка читання файлу \"" + filename + "\": " + e.getMessage());
        }
        return toys;
    }

    public static List<Toy> filterToysByAge(List<Toy> toys, int age) {
        List<Toy> result = new ArrayList<>();
        for (Toy t : toys) {
            if (t.isSuitableForAge(age)) result.add(t);
        }
        return result;
    }

    public static void printToys(List<Toy> toys, String header) {
        System.out.println(header);
        if (toys.isEmpty()) {
            System.out.println("  (порожньо)");
            return;
        }
        for (Toy t : toys) {
            System.out.println("  " + t);
        }
    }

    public static void main(String[] args) {
        String fileAll = "toys.dat";
        String fileFiltered = "toys_filtered.dat";

        List<Toy> toys = Arrays.asList(
                new Toy("М'яч", 150.0, 3, 10),
                new Toy("Лялька", 320.5, 5, 12),
                new Toy("Конструктор LEGO", 890.0, 7, 14),
                new Toy("Машинка", 200.0, 2, 8),
                new Toy("Настільна гра", 450.0, 8, 15),
                new Toy("Пазл 1000 ел.", 250.0, 10, 99)
        );

        createToysFile(fileAll, toys);

        List<Toy> readToys = readToysFile(fileAll);
        printToys(readToys, "Всі іграшки з файлу \"" + fileAll + "\":");

        Scanner scanner = new Scanner(System.in);
        int age;
        while (true) {
            System.out.print("Введіть вік дитини (ціле число >= 0): ");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age < 0) {
                    System.out.println("Вік не може бути від'ємним. Спробуйте ще раз.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Невірний ввід. Введіть, будь ласка, ціле число.");
            }
        }

        List<Toy> suitable = filterToysByAge(readToys, age);
        printToys(suitable, "Іграшки, що підходять для віку " + age + ":");

        createToysFile(fileFiltered, suitable);
        System.out.println("Файл \"" + fileFiltered + "\" створено з відібраними іграшками.");

        scanner.close();
    }
}
