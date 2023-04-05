import java.util.concurrent.atomic.AtomicInteger;

public class MainWithThreads {
    public static void main(String[] args) {
        var countPrimeNumberTill = 1000000;
        AtomicInteger counter = new AtomicInteger();
        var printPrimeNumber = new PrintPrimeNumber();
        var startTimeOverall = System.currentTimeMillis();
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

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // total time = 29928 milliseconds without join
        System.out.println("Total time overall "+ getTime(startTimeOverall) + " total count "+ counter.get());
//        Total time taken by thread t1 is 7881 milliseconds
//        Total time taken by thread t2 is 21958 milliseconds
//        Total time overall 21961 total count 78498


    }

    private static long getTime(long startTimeOverall) {
        return System.currentTimeMillis() - startTimeOverall;
    }
}