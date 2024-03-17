package net.protsenko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.protsenko")
public class AppConfig {
    @Bean
    public FileDownloader fileDownloader() {
        return new FileDownloader();
    }

    @Bean
    public Worker worker(FileDownloader fileDownloader) {
        return new Worker(fileDownloader);
    }
 }
