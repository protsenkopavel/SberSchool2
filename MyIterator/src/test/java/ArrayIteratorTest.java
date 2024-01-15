import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIteratorTest {

    @Test
    void testHasNext() {
        Integer[] integers = {1, 2, 3};
        ArrayIterator<Integer> iterator = new ArrayIterator<>(integers);

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testNext() {
        String[] strings = {"a", "b", "c"};
        ArrayIterator<String> iterator = new ArrayIterator<>(strings);

        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testRemove() {
        Character[] chars = {'a', 'b', 'c'};
        ArrayIterator<Character> iterator = new ArrayIterator<>(chars);

        iterator.next();
        iterator.remove();
        assertNull(chars[0]);
        assertThrows(IllegalStateException.class, iterator::remove);

        iterator.next();
        iterator.next();
        iterator.remove();
        assertNull(chars[2]);
        assertThrows(IllegalStateException.class, iterator::remove);
    }
}
