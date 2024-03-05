package task1;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result;
    private boolean calculated;

    public Task(Callable<? extends T> callable) {
        //...
        this.callable = callable;
    }

    public T get() {
        // todo implement me
        if (!calculated) {
            synchronized (this) {
                try {
                    result = callable.call();
                    calculated = true;
                } catch (Exception e) {
                    throw new UncallableException(e.getMessage());
                }
            }
        }
        return result;
    }
}

