import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> testMap = new HashMap<>();

        for (int i = 0; i < 100_000; i++) {
            testMap.put(i, "value" + i);
        }
    }
}
