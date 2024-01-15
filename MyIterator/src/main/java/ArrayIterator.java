import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<E> {
    private final E[] array;
    private int index;
    private boolean lastRemoved = false;

    public ArrayIterator(E[] array) {
        this.array = array;
    }

    public boolean hasNext(){
        return index < array.length;
    }

    public E next() {
        if (index >= array.length) {
            throw new NoSuchElementException();
        }
        E e = array[index++];
        lastRemoved = false;
        return e;
    }

    public void remove() {
        if (index == 0 || lastRemoved) {
            throw new IllegalStateException();
        }
        array[index - 1] = null;
        lastRemoved = true;
    }
}