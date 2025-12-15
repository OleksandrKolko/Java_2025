package HW_12;

import java.io.*;

// Базовий інтерфейс для студентів
interface Student {
    void accept(Visitor visitor);
    String getSpecialty();
    int getCredits();
    double getMoney();
    boolean isExpelled();
    void setExpelled(boolean expelled);
    void addCredits(int credits);
    void addMoney(double amount);
    boolean pay(double amount);
    int getRequiredCredits();
    void setRequiredCredits(int credits);
}

// Інтерфейс відвідувача
interface Visitor {
    void visit(HumanitarianStudent student);
    void visit(NaturalStudent student);
    void visit(NaturalHumanitarianStudent student);
}

// Конкретні класи студентів
class HumanitarianStudent implements Student {
    private String specialty = "humanitarian";
    private int credits = 0;
    private double money = 0;
    private boolean expelled = false;
    private int requiredCredits = 0;

    public HumanitarianStudent(double initialMoney, int requiredCredits) {
        this.money = initialMoney;
        this.requiredCredits = requiredCredits;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSpecialty() { return specialty; }
    @Override
    public int getCredits() { return credits; }
    @Override
    public double getMoney() { return money; }
    @Override
    public boolean isExpelled() { return expelled; }
    @Override
    public void setExpelled(boolean expelled) { this.expelled = expelled; }
    @Override
    public void addCredits(int credits) { this.credits += credits; }
    @Override
    public void addMoney(double amount) { this.money += amount; }
    @Override
    public boolean pay(double amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
    @Override
    public int getRequiredCredits() { return requiredCredits; }
    @Override
    public void setRequiredCredits(int credits) { this.requiredCredits = credits; }
}

class NaturalStudent implements Student {
    private String specialty = "natural";
    private int credits = 0;
    private double money = 0;
    private boolean expelled = false;
    private int requiredCredits = 0;

    public NaturalStudent(double initialMoney, int requiredCredits) {
        this.money = initialMoney;
        this.requiredCredits = requiredCredits;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSpecialty() { return specialty; }
    @Override
    public int getCredits() { return credits; }
    @Override
    public double getMoney() { return money; }
    @Override
    public boolean isExpelled() { return expelled; }
    @Override
    public void setExpelled(boolean expelled) { this.expelled = expelled; }
    @Override
    public void addCredits(int credits) { this.credits += credits; }
    @Override
    public void addMoney(double amount) { this.money += amount; }
    @Override
    public boolean pay(double amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
    @Override
    public int getRequiredCredits() { return requiredCredits; }
    @Override
    public void setRequiredCredits(int credits) { this.requiredCredits = credits; }
}

class NaturalHumanitarianStudent implements Student {
    private String specialty = "natural-humanitarian";
    private int credits = 0;
    private double money = 0;
    private boolean expelled = false;
    private int requiredCredits = 0;

    public NaturalHumanitarianStudent(double initialMoney, int requiredCredits) {
        this.money = initialMoney;
        this.requiredCredits = requiredCredits;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSpecialty() { return specialty; }
    @Override
    public int getCredits() { return credits; }
    @Override
    public double getMoney() { return money; }
    @Override
    public boolean isExpelled() { return expelled; }
    @Override
    public void setExpelled(boolean expelled) { this.expelled = expelled; }
    @Override
    public void addCredits(int credits) { this.credits += credits; }
    @Override
    public void addMoney(double amount) { this.money += amount; }
    @Override
    public boolean pay(double amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
    @Override
    public int getRequiredCredits() { return requiredCredits; }
    @Override
    public void setRequiredCredits(int credits) { this.requiredCredits = credits; }
}

// Конкретні відвідувачі
class Teacher implements Visitor {
    private String profile;
    private int credits;

    public Teacher(String profile, int credits) {
        this.profile = profile;
        this.credits = credits;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        if (!student.isExpelled() && profile.equals("humanitarian")) {
            student.addCredits(credits);
        }
    }

    @Override
    public void visit(NaturalStudent student) {
        if (!student.isExpelled() && profile.equals("natural")) {
            student.addCredits(credits);
        }
    }

    @Override
    public void visit(NaturalHumanitarianStudent student) {
        if (!student.isExpelled()) {
            student.addCredits(credits);
        }
    }
}

class ScholarshipVisitor implements Visitor {
    private double amount;

    public ScholarshipVisitor(double amount) {
        this.amount = amount;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        if (!student.isExpelled()) {
            student.addMoney(amount);
        }
    }

    @Override
    public void visit(NaturalStudent student) {
        if (!student.isExpelled()) {
            student.addMoney(amount);
        }
    }

    @Override
    public void visit(NaturalHumanitarianStudent student) {
        if (!student.isExpelled()) {
            student.addMoney(amount);
        }
    }
}

class ParentSupportVisitor implements Visitor {
    private double amount;

    public ParentSupportVisitor(double amount) {
        this.amount = amount;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        if (!student.isExpelled()) {
            student.addMoney(amount);
        }
    }

    @Override
    public void visit(NaturalStudent student) {
        if (!student.isExpelled()) {
            student.addMoney(amount);
        }
    }

    @Override
    public void visit(NaturalHumanitarianStudent student) {
        if (!student.isExpelled()) {
            student.addMoney(amount);
        }
    }
}

class HostelPaymentVisitor implements Visitor {
    private double amount;

    public HostelPaymentVisitor(double amount) {
        this.amount = amount;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        if (!student.isExpelled()) {
            boolean success = student.pay(amount);
            if (!success) {
                student.setExpelled(true);
            }
        }
    }

    @Override
    public void visit(NaturalStudent student) {
        if (!student.isExpelled()) {
            boolean success = student.pay(amount);
            if (!success) {
                student.setExpelled(true);
            }
        }
    }

    @Override
    public void visit(NaturalHumanitarianStudent student) {
        if (!student.isExpelled()) {
            boolean success = student.pay(amount);
            if (!success) {
                student.setExpelled(true);
            }
        }
    }
}

class CanteenPaymentVisitor implements Visitor {
    private double amount;

    public CanteenPaymentVisitor(double amount) {
        this.amount = amount;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        if (!student.isExpelled()) {
            boolean success = student.pay(amount);
            if (!success) {
                student.setExpelled(true);
            }
        }
    }

    @Override
    public void visit(NaturalStudent student) {
        if (!student.isExpelled()) {
            boolean success = student.pay(amount);
            if (!success) {
                student.setExpelled(true);
            }
        }
    }

    @Override
    public void visit(NaturalHumanitarianStudent student) {
        if (!student.isExpelled()) {
            boolean success = student.pay(amount);
            if (!success) {
                student.setExpelled(true);
            }
        }
    }
}

// Клас для зчитування та обробки файлів
class StudentSimulation {
    public static void processStudentFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String specialty = reader.readLine().trim();
        int requiredCredits = Integer.parseInt(reader.readLine().trim());
        double initialMoney = Double.parseDouble(reader.readLine().trim());

        Student student = createStudent(specialty, initialMoney, requiredCredits);

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String action = parts[0];

            switch (action) {
                case "teach":
                    handleTeach(parts, student);
                    break;
                case "obtain":
                    handleObtain(parts, student);
                    break;
                case "pay":
                    handlePay(parts, student);
                    break;
                default:
                    System.err.println("Невідома дія: " + action);
            }
        }

        reader.close();

        // Перевірка чи отримає диплом
        boolean getsDiploma = !student.isExpelled() && student.getCredits() >= student.getRequiredCredits();

        System.out.println("=== Результати для " + fileName + " ===");
        System.out.println("Спеціальність: " + student.getSpecialty());
        System.out.println("Накопичено кредитів: " + student.getCredits() + "/" + student.getRequiredCredits());
        System.out.println("Залишок коштів: " + student.getMoney());
        System.out.println("Статус: " + (student.isExpelled() ? "Відрахований" : "Навчається"));
        System.out.println("Отримає диплом: " + (getsDiploma ? "ТАК" : "НІ"));
        System.out.println();
    }

    private static Student createStudent(String specialty, double initialMoney, int requiredCredits) {
        switch (specialty) {
            case "humanitarian":
                return new HumanitarianStudent(initialMoney, requiredCredits);
            case "natural":
                return new NaturalStudent(initialMoney, requiredCredits);
            case "natural-humanitarian":
                return new NaturalHumanitarianStudent(initialMoney, requiredCredits);
            default:
                throw new IllegalArgumentException("Невідома спеціальність: " + specialty);
        }
    }

    private static void handleTeach(String[] parts, Student student) {
        String profile = parts[1];
        int credits = parts.length > 2 ? Integer.parseInt(parts[2]) : 3; // За замовчуванням 3 кредити

        Teacher teacher = new Teacher(profile, credits);
        student.accept(teacher);
    }

    private static void handleObtain(String[] parts, Student student) {
        String type = parts[1];
        double amount = Double.parseDouble(parts[2]);

        if (type.equals("scholarship")) {
            ScholarshipVisitor visitor = new ScholarshipVisitor(amount);
            student.accept(visitor);
        } else if (type.equals("parents")) {
            ParentSupportVisitor visitor = new ParentSupportVisitor(amount);
            student.accept(visitor);
        }
    }

    private static void handlePay(String[] parts, Student student) {
        String type = parts[1];
        double amount = Double.parseDouble(parts[2]);

        if (type.equals("hostel")) {
            HostelPaymentVisitor visitor = new HostelPaymentVisitor(amount);
            student.accept(visitor);
        } else if (type.equals("canteen")) {
            CanteenPaymentVisitor visitor = new CanteenPaymentVisitor(amount);
            student.accept(visitor);
        }
    }
}

// Головний клас
public class B12_01 {
    public static void main(String[] args) {
        String[] inputFiles = {
                "input01.txt", "input02.txt", "input03.txt", "input04.txt",
                "input05.txt", "input06.txt", "input07.txt", "input08.txt"
        };

        for (String fileName : inputFiles) {
            try {
                StudentSimulation.processStudentFile(fileName);
            } catch (IOException e) {
                System.err.println("Помилка при обробці файлу " + fileName + ": " + e.getMessage());
            }
        }
    }
}
