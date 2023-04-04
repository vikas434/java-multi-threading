import java.util.concurrent.atomic.AtomicInteger;

public class OddEven {

    private static final int COUNT_MAX = 10;
    private AtomicInteger counter = new AtomicInteger(0);

    public synchronized void printEven() {
        while(counter.get() < COUNT_MAX) {
            if(counter.get() % 2 == 0) {
                System.out.println("Even: " + counter.get() + " printed from thread "+Thread.currentThread().getName());
                counter.incrementAndGet();
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void printOdd() {
        while(counter.get() < COUNT_MAX) {
            if(counter.get() % 2 != 0) {
                System.out.println("Odd: " + counter.get() + " printed from thread "+Thread.currentThread().getName());
                counter.incrementAndGet();
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
