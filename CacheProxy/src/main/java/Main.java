import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CacheSettings cacheSettings = new CacheSettings("src/main/resources");
        CacheProxy cacheProxy = new CacheProxy(new ServiceImpl(), cacheSettings);
        Service service = cacheProxy.cache(new ServiceImpl());

        List<String> result1 = service.work("item1");
        List<String> result2 = service.work("item1");
        List<String> result3 = service.work("item1");
        List<String> result4 = service.work("item1");

    }
}
