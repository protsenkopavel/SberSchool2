import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        String fileName = "src/main/resources/numbers.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int availableProcessors = Runtime.getRuntime().availableProcessors();
                ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);

                while ((line = reader.readLine()) != null) {
                    long number = Integer.parseInt(line);
                    executorService.submit(new FactorialCalculator(number));
                }

                executorService.shutdown();
                executorService.awaitTermination(10, TimeUnit.SECONDS);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
