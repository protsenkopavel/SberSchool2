import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheProxyTest {
    @Test
    void testInMemoryCache() {
        CacheSettings cacheSettings = new CacheSettings("");
        CacheProxy cacheProxy = new CacheProxy(new ServiceImpl(), cacheSettings);
        Service service = cacheProxy.cache(new ServiceImpl());

        List<String> result1 = service.work("item1");
        List<String> result2 = service.work("item1");

        assertEquals(result1, result2);
    }

    @Test
    void testFileCache() {
        CacheSettings cacheSettings = new CacheSettings("src/main/resources");
        CacheProxy cacheProxy = new CacheProxy(new ServiceImpl(), cacheSettings);
        Service service = cacheProxy.cache(new ServiceImpl());

        List<String> result1 = service.run("item1", 10.0, new Date());
        List<String> result2 = service.run("item1", 10.0, new Date());

        assertEquals(result1, result2);
    }

    @Test
    void testIgnoreArguments() {
        CacheSettings cacheSettings = new CacheSettings("src/main/resources");
        CacheProxy cacheProxy = new CacheProxy(new ServiceImpl(), cacheSettings);
        Service service = cacheProxy.cache(new ServiceImpl());

        List<String> result1 = service.run("item1", 10.0, new Date());
        List<String> result2 = service.run("item1", 5.0, new Date());

        assertEquals(result1, result2);
    }
}
