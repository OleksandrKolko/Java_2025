package HW_5;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class B05_08 {
    public static void main(String[] args) {
        System.out.println("Поточна директорія: " + Paths.get("").toAbsolutePath());

        String inputFile = "cubes.txt";

        try {
            List<Cube> cubes = readCubesFromFile(inputFile);

            int targetSize = 5;
            List<Cube> filtered = findCubesBySize(cubes, targetSize);
            writeCubesToFile(filtered, "bySize.txt");

            Map<String, Integer> colorCount = countCubesByColor(cubes);
            writeColorCountToFile(colorCount, "colorCount.txt");

            System.out.println("Результати записано у файли:");
            System.out.println(" - bySize.txt");
            System.out.println(" - colorCount.txt");

        } catch (IOException e) {
            System.err.println("Помилка читання або запису файлу: " + e.getMessage());
        }
    }

    static class Cube {
        int size;
        String color;
        String material;

        Cube(int size, String color, String material) {
            this.size = size;
            this.color = color;
            this.material = material;
        }

        @Override
        public String toString() {
            return size + " " + color + " " + material;
        }
    }


    public static List<Cube> readCubesFromFile(String fileName) throws IOException {
        List<Cube> cubes = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 3) {
                int size = Integer.parseInt(parts[0]);
                String color = parts[1];
                String material = parts[2];
                cubes.add(new Cube(size, color, material));
            }
        }
        return cubes;
    }


    public static List<Cube> findCubesBySize(List<Cube> cubes, int targetSize) {
        List<Cube> result = new ArrayList<>();
        for (Cube c : cubes) {
            if (c.size == targetSize) {
                result.add(c);
            }
        }
        return result;
    }

    public static Map<String, Integer> countCubesByColor(List<Cube> cubes) {
        Map<String, Integer> colorCount = new LinkedHashMap<>();
        for (Cube c : cubes) {
            colorCount.put(c.color, colorCount.getOrDefault(c.color, 0) + 1);
        }
        return colorCount;
    }

    public static void writeCubesToFile(List<Cube> cubes, String fileName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (Cube c : cubes) {
                writer.write(c.toString());
                writer.newLine();
            }
        }
    }

    public static void writeColorCountToFile(Map<String, Integer> colorCount, String fileName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (Map.Entry<String, Integer> entry : colorCount.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }
}