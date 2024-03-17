import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public Calculator(Source sourceCache) {
        this.sourceCache = sourceCache;
    }

    private static Source sourceCache;

    @Cacheable(DBConnection.class)
    public List<Integer> fibonachi(int i) {
        if (sourceCache.getData(Integer.toString(i)).isEmpty()) {
            List<Integer> result = calculateFibonachi(i);
            sourceCache.saveData(Integer.toString(i), result);
            return result;
        } else {
            return sourceCache.getData(Integer.toString(i));
        }
    }

    public List<Integer> calculateFibonachi(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be positive");
        }
        if (n == 0) {
            return List.of();
        }
        if (n == 1) {
            return List.of(0);
        }
        if (n == 2) {
            return List.of(0, 1);
        }
        List<Integer> result = new ArrayList<>(calculateFibonachi(n - 1));
        result.add(result.get(result.size() - 1) + result.get(result.size() - 2));
        return result;
    }
}