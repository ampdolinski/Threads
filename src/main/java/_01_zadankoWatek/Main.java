package _01_zadankoWatek;

public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
        System.out.println("Hello world!");
    }

    private static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Hello I'm thread!");
        }
    }

}
