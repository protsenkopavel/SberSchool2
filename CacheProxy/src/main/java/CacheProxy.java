import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final CacheSettings cacheSettings;
    private final Map<String, Object> cache = new HashMap<>();

    public CacheProxy(Object target, CacheSettings cacheSettings) {
        this.target = target;
        this.cacheSettings = cacheSettings;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        }

        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        String key = (String) args[0];

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Object result = method.invoke(target, args);
        cache.put(key, result);

        if (cacheAnnotation.cacheType() == CacheType.FILE) {
            saveToFile(key, result, cacheAnnotation);
        }

        return result;
    }

    private void saveToFile(String key, Object result, Cache cacheAnnotation) {
        try {
            String fileName = cacheSettings.getRootFolder() + File.separator + key;
            if (cacheAnnotation.zip()) {
                fileName += ".zip";
                try (ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(fileName)))) {
                    oos.writeObject(result);
                }
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(result);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public <T> T cache(T service) {
        return (T) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                this
        );
    }
}