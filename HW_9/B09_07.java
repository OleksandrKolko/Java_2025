package HW_9;

import java.util.*;
import java.util.concurrent.*;

public class B09_07 {

    static class Doctor {
        final int id;
        final long serviceTime;
        final Semaphore semaphore = new Semaphore(1);

        Doctor(int id, long serviceTime) {
            this.id = id;
            this.serviceTime = serviceTime;
        }

        void servePatient(int patientId) throws InterruptedException {
            System.out.println("Лікар " + id +
                    " почав обслуговувати пацієнта " + patientId +
                    " (час прийому: " + serviceTime + " мс)");
            Thread.sleep(serviceTime);
            System.out.println("Лікар " + id +
                    " закінчив обслуговувати пацієнта " + patientId);
        }

        boolean tryAcquire() {
            return semaphore.tryAcquire();
        }

        void release() {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws Exception {
        int N = 3;
        int patientCount = 10;
        long T1 = 300;
        long T2 = 700;
        long T3 = 800;
        long T4 = 1500;

        Random rnd = new Random();
        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long service = T3 + rnd.nextInt((int) (T4 - T3));
            doctors.add(new Doctor(i, service));
        }

        ExecutorService patients = Executors.newFixedThreadPool(patientCount);

        for (int i = 0; i < patientCount; i++) {
            int id = i;
            patients.submit(() -> {
                try {
                    long arrival = T1 + rnd.nextInt((int) (T2 - T1));
                    Thread.sleep(arrival);

                    System.out.println("Пацієнт " + id +
                            " прийшов у лікарню (затримка: " + arrival + " мс)");

                    while (true) {
                        for (Doctor d : doctors) {
                            if (d.tryAcquire()) {
                                System.out.println("Пацієнт " + id +
                                        " зайшов до лікаря " + d.id);
                                d.servePatient(id);
                                d.release();
                                System.out.println("Пацієнт " + id +
                                        " пішов від лікаря " + d.id);
                                return;
                            }
                        }
                        System.out.println("Пацієнт " + id +
                                " чекає, всі лікарі зайняті...");
                        Thread.sleep(100);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        patients.shutdown();
        patients.awaitTermination(1, TimeUnit.HOURS);
    }
}
