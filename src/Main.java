import java.util.concurrent.atomic.AtomicInteger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        AtomicInteger atomicCounter = new AtomicInteger(0);

        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                atomicCounter.incrementAndGet();
                counter.incAndGet();
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value with atomic counter is: " + atomicCounter);
        System.out.println("Final counter value with non-atomic counter is: " + counter.get());
    }
}