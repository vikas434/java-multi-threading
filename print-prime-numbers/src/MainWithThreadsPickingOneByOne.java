import java.util.concurrent.atomic.AtomicInteger;

public class MainWithThreadsPickingOneByOne {
    public static void main(String[] args) {
        var countPrimeNumberTill = 1000000;
        AtomicInteger counter = new AtomicInteger();
        var printPrimeNumber = new PrintPrimeNumber();
        //TODO explore fork & join framework
    }
}