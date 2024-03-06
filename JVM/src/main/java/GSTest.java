import java.util.ArrayList;
import java.util.List;

public class GSTest {
    public static void main(String[] args) throws InterruptedException {
        List<Object> objects = new ArrayList<>();

        for (int i = 0; i < 100_000; i++) {
            objects.add(new byte[1024]);
            Thread.sleep(1);
        }
    }
}
