package HW_4;

import java.util.*;

public class B04_06 {
    static abstract class Equipment{
        protected String name;
        protected double weight;
        protected double protectionLevel;
        protected double basePrice;

        public Equipment(String name, double weight, double protectionLevel, double basePrise){
            this.name = name;
            this.weight = weight;
            this.protectionLevel = protectionLevel;
            this.basePrice = basePrise;
        }

        public abstract  double calculatePrice();

        public double getWeight(){
            return weight;
        }

        public double getProtectionLevel(){
            return protectionLevel;
        }

        @Override
        public String toString() {
            return String.format("%s (Вага: %.2f кг, Захист: %.2f, Ціна: %.2f грн)",
                    name, weight, protectionLevel, calculatePrice());
        }
    }

    static class Helmet extends Equipment{
        public Helmet(String name, double weight, double protectionLevel, double basePrice){
            super(name, weight, protectionLevel, basePrice);
        }

        @Override
        public double calculatePrice() {
            return basePrice + protectionLevel * 1000;
        }
    }

    static class Jacket extends Equipment{
        public Jacket(String name, double weight, double protectionLevel, double basePrice){
            super(name, weight, protectionLevel, basePrice);
        }

        @Override
        public double calculatePrice() {
            return basePrice + protectionLevel * 800;
        }
    }
    static class Gloves extends Equipment {
        public Gloves(String name, double weight, double protectionLevel, double basePrice) {
            super(name, weight, protectionLevel, basePrice);
        }

        @Override
        public double calculatePrice() {
            return basePrice + protectionLevel * 300;
        }
    }

    static class Boots extends Equipment {
        public Boots(String name, double weight, double protectionLevel, double basePrice) {
            super(name, weight, protectionLevel, basePrice);
        }

        @Override
        public double calculatePrice() {
            return basePrice + protectionLevel * 500;
        }
    }

    static class Motorcyclist {
        private List<Equipment> equipmentList = new ArrayList<>();

        public void addEquipment(Equipment e){
            equipmentList.add(e);
        }

        public double totalCost() {
            return equipmentList.stream()
                    .mapToDouble(Equipment::calculatePrice)
                    .sum();
        }

        public void sortByWeight() {
            equipmentList.sort(Comparator.comparingDouble(Equipment::getWeight));
        }

        public List<Equipment> filterByProtection(double min, double max) {
            List<Equipment> result = new ArrayList<>();
            for (Equipment e : equipmentList) {
                if (e.getProtectionLevel() >= min && e.getProtectionLevel() <= max) {
                    result.add(e);
                }
            }
            return result;
        }

        public void showEquipment() {
            equipmentList.forEach(System.out::println);
        }

    }

    public static void main(String[] args) {
        Motorcyclist biker = new Motorcyclist();

        biker.addEquipment(new Helmet("Шолом Shark", 1.2, 0.9, 2500));
        biker.addEquipment(new Jacket("Куртка Alpinestars", 3.5, 0.85, 4000));
        biker.addEquipment(new Gloves("Рукавиці Dainese", 0.6, 0.7, 1500));
        biker.addEquipment(new Boots("Черевики Forma", 2.0, 0.8, 3000));

        System.out.println("=== Екіпіровка мотоцикліста ===");
        biker.showEquipment();

        System.out.printf("\nЗагальна вартість екіпіровки: %.2f грн\n", biker.totalCost());

        biker.sortByWeight();
        System.out.println("\n=== Сортування за вагою ===");
        biker.showEquipment();

        System.out.println("\n=== Елементи з коефіцієнтом захисту 0.75–0.9 ===");
        for (Equipment e : biker.filterByProtection(0.75, 0.9)) {
            System.out.println(e);
        }
    }
}
