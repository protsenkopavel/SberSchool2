import java.util.concurrent.LinkedBlockingQueue;

public class ScalableThreadPool implements ThreadPool {
    private final int minThreads;
    private final int maxThreads;
    private int currentThread;
    private final LinkedBlockingQueue<Runnable> tasks;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        this.currentThread = minThreads;
        this.tasks = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < currentThread; i++) {
            createAndStartThread();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasks.put(runnable);
            if (currentThread < maxThreads) {
                createAndStartThread();
                currentThread++;
            } else if (tasks.isEmpty() && currentThread > minThreads) {
                currentThread--;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void createAndStartThread() {
        Thread thread = new Thread(() -> {
            try {
                Runnable task = tasks.take();
                task.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}
