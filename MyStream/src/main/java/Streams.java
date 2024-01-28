import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams<T> {
    private final Collection<T> collection;

    private Streams(Collection<T> collection) {
        this.collection = collection;
    }

    public static <T> Streams<T> of(Collection<T> collection) {
        return new Streams<>(collection);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        return new Streams<>(collection.stream().filter(predicate).collect(Collectors.toList()));
    }

    public Streams<T> transform(Function<? super T, ? extends T> mapper) {
        return new Streams<>(collection.stream().map(mapper).collect(Collectors.toList()));
    }

    public <K> Map<K, T> toMap(Function<? super T, K> keyMapper) {
        return collection.stream().collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    public List<T> toList() {
        return new ArrayList<>(collection);
    }
}