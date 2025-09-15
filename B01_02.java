import java.util.Scanner;
public class B01_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = num - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
    }
}
