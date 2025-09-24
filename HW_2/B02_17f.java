package HW_2;

import java.util.Scanner;

public class B02_17f {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        double eps = scanner.nextDouble();

        double res = f(x, eps);
        System.out.println("Result = " + res);
        scanner.close();
    }

    public static double f(double x, double eps){
        if(Math.abs(x) < 1.0){
            return 0;
        }
        double sum = 0.0;
        double ak = 1.0; // a0 = 1
        int k = 0;

        final int MAX_ITER = 200_000; // захист від надто довгих розрахунків

        for (int iter = 0; iter < MAX_ITER; iter++) {
            double candidate = sum + ak;
            if (Math.abs(candidate) <= eps) {
                sum = candidate;
            } else {
                break;
            }

            k++;
            double multiplier = - (x * x) / ((2.0 * k - 1.0) * (2.0 * k));
            ak = ak * multiplier;

            if (Math.abs(ak) == 0.0) break;
        }
        return sum;
    }
}
