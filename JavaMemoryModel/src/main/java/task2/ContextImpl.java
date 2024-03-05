package task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ContextImpl implements Context {
    private final int totalTaskCount;
    private final CountDownLatch latch;
    private volatile int completedTaskCount = 0;
    private volatile int failedTaskCount = 0;
    private volatile int interruptedTaskCount = 0;
    private volatile boolean finished = false;

    public ContextImpl(int totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
        this.latch = new CountDownLatch(totalTaskCount);
    }

    public synchronized void incrementCompletedTaskCount() {
        completedTaskCount++;
        latch.countDown();
    }

    public synchronized void incrementFailedTaskCount() {
        failedTaskCount++;
        latch.countDown();
    }

    public synchronized void checkIfFinished(Runnable callback) {
        if (completedTaskCount + failedTaskCount + interruptedTaskCount >= totalTaskCount && !finished) {
            finished = true;
            callback.run();
        }
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }

    @Override
    public void interrupt() {
        interruptedTaskCount = totalTaskCount - completedTaskCount - failedTaskCount;
        latch.countDown();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
