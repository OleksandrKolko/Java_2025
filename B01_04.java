import java.util.Scanner;

public class B01_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введи перше число: ");
        int a = sc.nextInt();

        System.out.print("Введи друге число: ");
        int b = sc.nextInt();

        System.out.print("Введи третє число: ");
        int c = sc.nextInt();

        double product = (double) a * b * c;
        double geometricMean = Math.cbrt(product);

        System.out.printf("Середнє геометричне = %.4f%n", geometricMean);
    }
}
