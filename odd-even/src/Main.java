public class Main {
    public static void main(String[] args) {
        OddEven oddEven = new OddEven();
        Thread t1 = new Thread(oddEven::printEven);
        Thread t2 = new Thread(oddEven::printOdd);
        t1.start();
        t2.start();
    }
}