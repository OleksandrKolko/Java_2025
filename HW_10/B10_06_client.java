package HW_10;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class B10_06_client {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 5000;

        try (Socket socket = new Socket(HOST, PORT)) {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            System.out.print("Введіть число k: ");
            int k = sc.nextInt();
            sc.nextLine();
            out.println(k);

            while (true) {
                System.out.print("Введіть рядок чисел (або 'exit'): ");
                String line = sc.nextLine();

                out.println(line);

                if (line.equalsIgnoreCase("exit")) {
                    break;
                }

                String response = in.readLine();
                System.out.println("Кількість кратних чисел: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
