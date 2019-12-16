import distance.LowensteinDamerauDistance;
import distance.LowensteinDistance;
import distance.LowensteinRecursiveDistance;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    private static final int REPEAT = 100;
    private static final  String firstWord = "qqzxczxcxzczxczxxb";
    private static final String secondWord = "qweczxxzzxcxzczxr";

    public static long getTimeForLowensteinAlgorithm() {
        LowensteinDistance distance = new LowensteinDistance(firstWord, secondWord);

        System.out.println(distance.calculate());

        Instant instant = Instant.now();
        for (int i = 0; i < REPEAT; i++) {
            distance.calculate();
        }

        return Duration.between(instant, Instant.now()).toMillis();
    }

    public static long getTimeForLowensteinDamerauAlgorithm() {
        LowensteinDamerauDistance distance = new LowensteinDamerauDistance(firstWord, secondWord);

        System.out.println(distance.calculate());

        Instant instant = Instant.now();
        for (int i = 0; i < REPEAT; i++)
            distance.calculate();

        return Duration.between(instant, Instant.now()).toMillis();
    }

    public static long getTimeForLowensteinRecursiveAlgorithm() {
        LowensteinRecursiveDistance distance = new LowensteinRecursiveDistance(firstWord, secondWord);

        System.out.println(distance.calculate());

        Instant instant = Instant.now();
        for (int i = 0; i < REPEAT; i++)
            distance.calculate();

        return Duration.between(instant, Instant.now()).toMillis();
    }


    public static void main(String[] args) throws IOException {
        /*System.out.println("Time for Lowenstein algorithm: " + getTimeForLowensteinAlgorithm());
        System.out.println("Time for LowensteinDamerau algorithm: " + getTimeForLowensteinDamerauAlgorithm());
        System.out.println("Time for LowensteinDamerau Recursive algorithm: " + getTimeForLowensteinRecursiveAlgorithm());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fWord = reader.readLine();
        String secWord = reader.readLine();
*/
        //System.out.println(start);
        LowensteinDistance l = new LowensteinDistance(firstWord, secondWord);
        System.out.println("s" + l.calculate());

        //System.out.println(end);






        LowensteinDamerauDistance ld = new LowensteinDamerauDistance(firstWord, secondWord);
        LowensteinRecursiveDistance lr = new LowensteinRecursiveDistance(firstWord, secondWord);
        //System.out.println(l);
       /* System.out.println("LowDam: " + ld.calculate());
        System.out.println("LowRec: " + lr.calculate());*/


        System.out.println();



    }
}

