import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class _01_BlockingQueue {
    private static int MAX_NUMBER = 100000;

    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        for (int j = 2; j <= MAX_NUMBER; j += 1) {
            try {
                queue.put(j);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        var NUMBER_OF_THREAD = Runtime.getRuntime().availableProcessors();
        System.out.println("Total number of threads as per the processors are " + NUMBER_OF_THREAD);

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

        var startTime = System.currentTimeMillis();
        for (int i = 0; i < NUMBER_OF_THREAD; i++) {
            executorService.submit(new PrimeNumberFinderTask());
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total time taken " + getTime(startTime));
        System.out.println("Total count " + PrimeNumberFinderTask.getCount());
        //Total time taken 97
        //Total count 9592
        //TODO fix the issue of executor service shutting down without finishing task
    }

    private static long getTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static class PrimeNumberFinderTask implements Runnable {
        static AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                try {
                    var number = queue.take();
                    if (isPrime(number)) {
                        System.out.println("Found the number " + number + " from thread " + Thread.currentThread().getName()
                                + " with count " + count.incrementAndGet());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private boolean isPrime(Integer number) {
            if (number <= 1) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public static int getCount() {
            return count.get();
        }
    }
}
