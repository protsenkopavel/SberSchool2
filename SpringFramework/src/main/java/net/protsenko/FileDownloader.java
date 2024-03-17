package net.protsenko;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileDownloader {
    private int fileCounter = 1;

    public void download(String link, String destinationFolder) {
        try {
            URL url = new URL(link);
            String fileName = generateFileName();
            Path filePath = Path.of(destinationFolder, fileName);

            byte[] buffer = new byte[1024];

            try (InputStream inputStream = url.openStream();
                 OutputStream outputStream = Files.newOutputStream(filePath)) {
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateFileName() {
        return "file" + fileCounter++;
    }
}


