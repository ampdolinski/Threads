package _02_zadankoWatki;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch listaObecnosci = new CountDownLatch(2);

        Thread jas = new Thread(new Jas(listaObecnosci));
        Thread malgosia = new Thread(new Malgosia(listaObecnosci));

        jas.start();
        malgosia.start();

        listaObecnosci.await();

//        jeżeli nie chcemy korzystać z countDownLatch-a,
//        można użyć sprawdzenia skończenia obu wątków,
//        kiedy dołączą (join) spowrotem do wątku main
//        jas.join();
//        malgosia.join();

        System.out.println("Koniec dnia!");

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static class Jas implements Runnable {

        private CountDownLatch countDownLatch;

        public Jas(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {

            try {
                System.out.println("Jaś zaczyna śniadanie...");
                Thread.sleep(5000);
                System.out.println("Jaś skończył śniadanie!");

                System.out.println("Jaś zaczyna prysznic...");
                Thread.sleep(3000);
                System.out.println("Jaś skończył prysznic!");

                System.out.println("Jaś zaczyna ubierać się...");
                Thread.sleep(1000);
                System.out.println("Jaś skończył ubierać się!");

                System.out.println("Jaś zaczyna zakupy...");
                Thread.sleep(15000);
                System.out.println("Jaś skończył zakupy!");

                System.out.println("Jaś zaczyna grać na konsoli...");
                Thread.sleep(5000);
                System.out.println("Jaś skończył grać na konsoli!");

                countDownLatch.countDown();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static class Malgosia implements Runnable {

        private CountDownLatch countDownLatch;

        public Malgosia(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {

            try {
                System.out.println("Małgosia zaczyna biegać...");
                Thread.sleep(6000);
                System.out.println("Małgosia skończyła biegać!");

                System.out.println("Małgosia zaczyna prysznic...");
                Thread.sleep(2000);
                System.out.println("Małgosia skończył prysznic!");

                System.out.println("Małgosia zaczyna śniadanie...");
                Thread.sleep(1000);
                System.out.println("Małgosia skończyła śniadanie!");

                System.out.println("Małgosia zaczyna ubierać się...");
                Thread.sleep(1000);
                System.out.println("Małgosia skończyła ubierać się!");

                System.out.println("Małgosia zaczyna spotkanie z koleżanką...");
                Thread.sleep(25000);
                System.out.println("Małgosia skończyła spotkanie z koleżanką!");

                countDownLatch.countDown();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
