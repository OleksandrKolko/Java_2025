package HW_3;

import java.util.*;

public class B03_14 {

    static class Subscriber {
        String fullName;
        double credit;
        int localTime;
        int longDistanceTime;

        public Subscriber(String fullName, double credit, int localTime, int longDistanceTime) {
            this.fullName = fullName;
            this.credit = credit;
            this.localTime = localTime;
            this.longDistanceTime = longDistanceTime;
        }

        @Override
        public String toString() {
            return fullName + " | Кредит: " + credit +
                    " | Локальні: " + localTime + " хв" +
                    " | Міжміські: " + longDistanceTime + " хв";
        }
    }

    static class SubscriberUtils {

        public static Subscriber[] filterByLocalTime(Subscriber[] subscribers, int limit) {
            List<Subscriber> result = new ArrayList<>();
            for (Subscriber s : subscribers) {
                if (s.localTime > limit) {
                    result.add(s);
                }
            }

            result.sort(Comparator.comparingDouble(s -> s.credit));

            return result.toArray(new Subscriber[0]);
        }

        public static Subscriber[] filterByLongDistance(Subscriber[] subscribers) {
            List<Subscriber> result = new ArrayList<>();
            for (Subscriber s : subscribers) {
                if (s.longDistanceTime > 0) {
                    result.add(s);
                }
            }
            return result.toArray(new Subscriber[0]);
        }
    }

    public static void main(String[] args) {
        Subscriber[] subscribers = {
                new Subscriber("Іваненко Іван", 150.5, 120, 0),
                new Subscriber("Петренко Петро", 200.0, 90, 30),
                new Subscriber("Сидоренко Олена", 75.0, 130, 15),
                new Subscriber("Коваль Андрій", 300.0, 50, 0),
                new Subscriber("Мельник Марія", 180.0, 200, 45)
        };

        int limit = 100;

        System.out.println("Абоненти з локальними розмовами > " + limit + " хв (сортовані за кредитом):");
        Subscriber[] locals = SubscriberUtils.filterByLocalTime(subscribers, limit);
        for (Subscriber s : locals) {
            System.out.println(s);
        }

        System.out.println("\nАбоненти, які користувалися міжміським зв'язком:");
        Subscriber[] longDistance = SubscriberUtils.filterByLongDistance(subscribers);
        for (Subscriber s : longDistance) {
            System.out.println(s);
        }
    }
}
