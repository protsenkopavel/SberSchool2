import org.junit.jupiter.api.Test;

public class ThreadPoolTest {
    @Test
    public void testFixedThreadPool() {
        // Given
        ThreadPool threadPool = new FixedThreadPool(3);

        // When
        threadPool.start();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                System.out.println("Задача выполнена в потоке: " + Thread.currentThread().getName());
            });
        }
    }

    @Test
    public void testScalableThreadPool() {
        // Given
        ThreadPool threadPool = new ScalableThreadPool(2, 5);

        //When
        threadPool.start();
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("Задача выполнена в потоке: " + Thread.currentThread().getName());
            });
        }
    }
}
