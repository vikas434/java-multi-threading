import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenWithMainVariable {
    private static final int COUNT_MAX = 10;
    private AtomicInteger counter = new AtomicInteger(0);

    public synchronized void printEven(AtomicInteger mainVariable) throws InterruptedException {
        while (counter.get() < COUNT_MAX) {
            if (counter.get() % 2 == 0) {
                mainVariable.incrementAndGet();
                Thread.sleep(2000);
                System.out.println("Even with main variable val : " + mainVariable + " and counter" + counter.get() + " printed from thread " + Thread.currentThread().getName());
                counter.incrementAndGet();
                System.out.println("current time stamp odd " + System.currentTimeMillis());
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

    public synchronized void printOdd(AtomicInteger mainVariable) throws InterruptedException {
        while (counter.get() < COUNT_MAX) {
            if (counter.get() % 2 != 0) {
                mainVariable.incrementAndGet();
                Thread.sleep(1000);
                System.out.println("Odd: with main variable val : " + mainVariable + " and counter" + counter.get() + " printed from thread " + Thread.currentThread().getName());
                counter.incrementAndGet();
                System.out.println("current time stamp odd " + System.currentTimeMillis());
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
