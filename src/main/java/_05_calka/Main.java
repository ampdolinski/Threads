package _05_calka;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        final AtomicDouble poleCalki = new AtomicDouble(0);

        ExecutorService threadPool = Executors.newFixedThreadPool(1);

//        pomysł - podzielić to na 3 części 0-5-10-15, tam każda szwaczka oś porobi
//        i to się zsumuje

        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                double poleCalki = getPoleCalki(0,15,0.001);
            }
        });

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("Całka podanej funkcji to: " + poleCalki);

    }

    private static double getPoleCalki(double zakresPoczatek, double zakresKoniec, double krokDzieleniaNaProstokaty) {
        double poleCalki = 0.0;

        for (double i = zakresPoczatek; i <= zakresKoniec; i = i + krokDzieleniaNaProstokaty) {
            double poczatekProstokata = function(i);
            double koniecProstokata = function(i + krokDzieleniaNaProstokaty);
            poleCalki = poleCalki + krokDzieleniaNaProstokaty * ((poczatekProstokata + koniecProstokata) / 2);
        }
        return poleCalki;
    }

    private static double function(double x) {
        return (3*Math.sin(x)) - (0.2*Math.pow(x, 3)) + (3*Math.pow(x, 2));
    }

}
