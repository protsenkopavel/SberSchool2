package net.protsenko;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Worker worker = context.getBean(Worker.class);

        String sourcePath = "/home/unknown/study-projects/SberJava2/SpringFramework/src/main/resources/urls.txt";
        String outputFolder = "/home/unknown/study-projects/SberJava2/SpringFramework/src/main/resources";

        try {
            worker.run(sourcePath, outputFolder);
        } catch (RuntimeException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
