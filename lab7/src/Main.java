import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        analyseTime();
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(Character.toString(random.nextInt(9)));
        }
        return builder.toString();
    }

    public static void analyseTime() {
        int repeats = 20;
        try (BufferedWriter file = new BufferedWriter(new FileWriter(("Standart.txt")))) {
            for (int i = 4; i < 1000; i+=10) {
                long time = 0;
                for (int j = 0; j < repeats; j++) {
                    String s = generateRandomString(i);
                    String sub = generateRandomString(3);

                    long start = System.nanoTime();
                    StrMatching.standard(s, sub);
                    long end = System.nanoTime();

                    time += (end - start);
                }

                file.write(i + " " + time / repeats + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
