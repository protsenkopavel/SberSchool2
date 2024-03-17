package net.protsenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker {
    private final FileDownloader downloader;

    public Worker(FileDownloader downloader) {
        this.downloader = downloader;
    }

    public void run(String sourcePath, String outputFolder) {
        List<String> urls;
        try {
            urls = readUrlsFromFile(sourcePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path downloadPath = Paths.get(outputFolder);
        try {
            Files.createDirectories(downloadPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExecutorService service = Executors.newFixedThreadPool(3);

        for (String url : urls) {
            service.submit(() -> {
                downloader.download(url, outputFolder);
            });
        }
        service.shutdown();
    }

    public static List<String> readUrlsFromFile(String filePath) throws IOException {
        List<String> urls = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                urls.add(line);
            }
        }
        return urls;
    }
}

