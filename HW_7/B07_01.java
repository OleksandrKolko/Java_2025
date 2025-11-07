package HW_7;

import java.io.*;
import java.util.*;

public class B07_01 {
    public static void createBinaryFile(String filename, double[] numbers) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
            for (double num : numbers) {
                out.writeDouble(num);
            }
            System.out.println("Файл " + filename + " успішно створено.");
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public static double[] readBinaryFile(String filename) {
        List<Double> list = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {
            while (in.available() > 0) {
                list.add(in.readDouble());
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
        double[] result = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void createFilteredFile(String sourceFile, String targetFile, double a) {
        double[] numbers = readBinaryFile(sourceFile);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(targetFile))) {
            for (double num : numbers) {
                if (num > a) {
                    out.writeDouble(num);
                }
            }
            System.out.println("Файл " + targetFile + " створено (фільтр: > " + a + ").");
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public static void printBinaryFile(String filename) {
        double[] numbers = readBinaryFile(filename);
        System.out.print("Вміст файлу " + filename + ": ");
        for (double num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String fileF = "F.bin";
        String fileG = "G.bin";

        double[] numbers = {1.5, -2.3, 4.7, 0.0, 8.2, 3.14};
        double a = 2.0;

        createBinaryFile(fileF, numbers);

        printBinaryFile(fileF);

        createFilteredFile(fileF, fileG, a);

        printBinaryFile(fileG);
    }
}
