package net.protsenko;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String sourcePath = "/home/unknown/study-projects/SberJava2/SpringFramework/src/main/resources/urls.txt";
        String outputFolder = "/home/unknown/study-projects/SberJava2/SpringFramework/src/main/resources";

        FileDownloader fileDownloader = new FileDownloader();
        Worker worker = new Worker(fileDownloader);

        try {
            worker.run(sourcePath, outputFolder);
        } catch (RuntimeException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
