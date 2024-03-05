package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ExecutorService executorService;

    public ExecutionManagerImpl(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ContextImpl context = new ContextImpl(tasks.length);

        for (Runnable task : tasks) {
            executorService.submit(() -> {
                try {
                    task.run();
                    context.incrementCompletedTaskCount();
                } catch (Exception e) {
                    context.incrementFailedTaskCount();
                } finally {
                    context.checkIfFinished(callback);
                }
            });
        }

        return context;
    }
}
