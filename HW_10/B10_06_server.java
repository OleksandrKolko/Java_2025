package HW_10;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class B10_06_server {

    public static void main(String[] args) {
        final int PORT = 5000;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущено. Очікування клієнта...");

            Socket socket = serverSocket.accept();
            System.out.println("Клієнт підключено.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int k = Integer.parseInt(in.readLine());
            System.out.println("Отримано k = " + k);

            String line;

            while ((line = in.readLine()) != null) {

                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Клієнт завершив роботу.");
                    break;
                }

                String[] numbers = line.trim().split("\\s+");
                int count = 0;

                for (String n : numbers) {
                    if (!n.isEmpty()) {
                        int num = Integer.parseInt(n);
                        if (num % k == 0) count++;
                    }
                }

                out.println(count);
                System.out.println("Рядок: \"" + line + "\" → кратних = " + count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
