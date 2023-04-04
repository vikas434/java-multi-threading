public class Main {
    public static void main(String[] args) {
        var countPrimeNumberTill = 1000000;
        var counter = 0;
        var printPrimeNumber = new PrintPrimeNumber();
        var startTime = System.currentTimeMillis();
        for (var i = 2; i <= countPrimeNumberTill; i++) {
            if (printPrimeNumber.isPrime(i)) {
                counter++;
            }
        }
        System.out.println("Total prime numbers till " + countPrimeNumberTill + " are " + counter);
        System.out.println("Time taken to compute is " + (System.currentTimeMillis() - startTime) + " milliseconds");
        //29773
    }
}