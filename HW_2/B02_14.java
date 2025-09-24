package HW_2;

import java.util.Scanner;

public class B02_14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        int v = num & 0xFFFF;

        int countA = 0;
        int mask = 1;
        for (int i = 0; i < 16; i++) {
            if ((v & mask) != 0){
                countA++;
            }
            mask <<= 1;
        }
        System.out.println("Count: " + countA);
        sc.close();
    }
}
