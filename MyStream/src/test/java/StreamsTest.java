import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StreamsTest {

    @Test
    void filterTest() {
        List<String> list = Arrays.asList("cat", "dog", "frog", "fox", "elephant");
        Streams<String> stream = Streams.of(list);

        Streams<String> filteredStream = stream.filter(s -> s.length() > 5);

        List<String> filteredList = filteredStream.toList();

        assertEquals(Arrays.asList("elephant"), filteredList);
    }

    @Test
    void transformTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Streams<Integer> stream = Streams.of(list);

        Streams<Integer> transformedStream = stream.transform(i -> i * 2);

        List<Integer> transformedList = transformedStream.toList();

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), transformedList);
    }

    @Test
    void toMapTest() {
        List<Person> personList = Arrays.asList(
                new Person("Anna", 25),
                new Person("Alex", 30),
                new Person("Pablo", 18)
        );
        Streams<Person> stream = Streams.of(personList);

        Map<String, Person> personMap = stream.toMap(Person::getName);

        assertEquals("Anna", personMap.get("Anna").getName());
        assertEquals(30, personMap.get("Alex").getAge());
        assertEquals("Pablo", personMap.get("Pablo").getName());
    }

    @Test
    void toListTest() {
        List<String> originalList = Arrays.asList("one", "two", "three", "four");
        Streams<String> stream = Streams.of(originalList);

        List<String> resultList = stream.toList();

        assertEquals(originalList, resultList);
    }

    private static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}
