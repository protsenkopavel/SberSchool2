import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsNumber;
    private final Thread[] threads;
    private final LinkedBlockingQueue<Runnable> tasks;

    public FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.threads = new Thread[threadsNumber];
        this.tasks = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
