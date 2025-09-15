import java.util.Scanner;

public class B01_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int res = 0;
        for (int i = 0; i < num; i++) {
            if (scanner.hasNextInt()){
                res += scanner.nextInt();
            }
            else {
                System.out.print("Введене число не є цілим");
            }
        }
        System.out.println(res);
    }
}
