import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger number = new AtomicInteger(5);
        OddEven oddEven = new OddEven();
        OddEvenWithMainVariable oddEvenWithMainVariable = new OddEvenWithMainVariable();
//        Thread t1 = new Thread(oddEven::printEven);
//        Thread t2 = new Thread(oddEven::printOdd);
//        t1.start();
//        t2.start();
        System.out.println("Simple odd even done");
        Thread t3 = new Thread(() -> {
            try {
                oddEvenWithMainVariable.printEven(number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                oddEvenWithMainVariable.printOdd(number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t3.start();
        t4.start();
    }
}