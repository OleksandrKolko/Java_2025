package HW_9;

import java.io.*;
import java.util.concurrent.*;

public class B09_01 {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        long T1 = 500;
        long T2 = 800;
        long T3 = 600;

        File input = new File("input.txt");
        File out1 = new File("output_thread2.txt");
        File out2 = new File("output_thread3.txt");

        Thread reader = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new FileReader(input))) {
                String line;
                while ((line = br.readLine()) != null) {
                    queue.put(line);
                    Thread.sleep(T1);
                }
                queue.put("EOF");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread worker1 = new Thread(() -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(out1))) {
                while (true) {
                    String line = queue.take();
                    if (line.equals("EOF")) {
                        queue.put("EOF");
                        break;
                    }
                    Thread.sleep(T2);
                    bw.write(line);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread worker2 = new Thread(() -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(out2))) {
                while (true) {
                    String line = queue.take();
                    if (line.equals("EOF")) {
                        queue.put("EOF");
                        break;
                    }
                    Thread.sleep(T3);
                    bw.write(line);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        reader.start();
        worker1.start();
        worker2.start();

        reader.join();
        worker1.join();
        worker2.join();
    }
}
