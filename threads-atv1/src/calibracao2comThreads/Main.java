package calibracao2comThreads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static calibracao2comThreads.Calibracao.valorCalibracao;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Calibração do arquivo sem o uso de Threads");
        long tiSemThreads = System.currentTimeMillis();

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\calibracao2comThreads\\new_calibration_text.txt");
        List<String> calibrations = Files.readAllLines(path);

        int soma = 0;
        int count = 0;

        for (String line: calibrations) {
            soma += valorCalibracao(line);
            count++;
        }

        System.out.println("A soma dos valores é: " + soma);
        System.out.println("Total de linhas: " + count);

        long tfSemThreads = System.currentTimeMillis();

        System.out.printf("%.3f ms%n", (tfSemThreads - tiSemThreads) / 1000d);

        System.out.println("\nCalibração do arquivo com o uso de 2 Threads");
        long tiComThreads = System.currentTimeMillis();

        String[] linhasArray = calibrations.toArray(new String[0]);

        ThreadCalibradora thread1 = new ThreadCalibradora(linhasArray, 1, 40000);
        ThreadCalibradora thread2 = new ThreadCalibradora(linhasArray, 40001, 80000);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int somaTotal = thread1.getSomaDaThread() + thread2.getSomaDaThread();
        System.out.println("A soma dos valores é: " + somaTotal);

        long tfComThreads = System.currentTimeMillis();
        System.out.printf("%.3f ms%n", (tfComThreads - tiComThreads) / 1000d);


    }

}
