import java.util.concurrent.atomic.AtomicInteger;

public class MainWithThreads {
    public static void main(String[] args) {
        var countPrimeNumberTill = 1000000;
        AtomicInteger counter = new AtomicInteger();
        var printPrimeNumber = new PrintPrimeNumber();
        Thread t1 = new Thread(() -> {
            var startTime = System.currentTimeMillis();
            for (var i = 2; i <= countPrimeNumberTill/2; i++) {
                if (printPrimeNumber.isPrime(i)) {
                    counter.getAndIncrement();
                }
            }
            System.out.println("Total time taken by thread t1 is " + (System.currentTimeMillis() - startTime) + " milliseconds");
        });
        Thread t2 = new Thread(() -> {
            var startTime = System.currentTimeMillis();
            for (var i = countPrimeNumberTill/2; i < countPrimeNumberTill; i++) {
                if (printPrimeNumber.isPrime(i)) {
                    counter.getAndIncrement();
                }
            }
            System.out.println("Total time taken by thread t2 is " + (System.currentTimeMillis() - startTime) + " milliseconds");
        });
        t1.start();
        t2.start();
        // total time = 29928 milliseconds
    }
}