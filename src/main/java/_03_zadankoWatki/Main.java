package _03_zadankoWatki;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        File folder = new File("src/main/resources/numbers.txt");

        ExecutorService threadPool = Executors.newFixedThreadPool(16);

        Scanner scanner = new Scanner(folder);

        final AtomicInteger licznikPierwszych = new AtomicInteger(0);

        long poczatek = System.nanoTime();

        while (scanner.hasNextLong()) {
            final long liczbaTestowa = scanner.nextLong();

            threadPool.submit(new Runnable() {
                @Override
                public void run() {

                    boolean rezultat = czyLiczbaJestPierwsza(liczbaTestowa);
                    if (rezultat) {
                        licznikPierwszych.incrementAndGet();
                        System.out.println("Znalazłem " + licznikPierwszych.get() + " liczbę pierwszą!");
                    }
                }
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("Tyle było liczb pierwszych: " + licznikPierwszych);

        System.out.println("Zajęło to " + (System.nanoTime() - poczatek)/1_000_000.0 + " ms.");


    }

    private static boolean czyLiczbaJestPierwsza(long number) {

        if (number <= 1) {
            return false;
        }

        if (number == 2) {
            return true;
        }

        for (long i = 2; i < Math.sqrt(number)+1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}