package HW_1;

import java.util.Scanner;

public class В01_05 {
    public static void main(String[] args) {
        int N, M;

        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
        } else {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            M = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int randomNum = (int) (Math.random() * M);
            System.out.println(randomNum);
        }
    }
}
