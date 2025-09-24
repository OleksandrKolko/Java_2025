package HW_2;

import java.util.Scanner;

public class B02_06 {
    public static void main(String[] args) {
        System.out.print("num = ");
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        int[] arr = new int[num];

        for (int i = 0; i < num; i++){
            arr[i] = sc.nextInt();
        }

        double sum = 0;
        for  (int i = 0; i < num; i++){
            sum += 1.0 / arr[i];
        }

        double result = num /  sum;
        System.out.println("harmonic = " + result);
        sc.close();
    }
}
