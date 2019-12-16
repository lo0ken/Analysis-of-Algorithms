package distance;

import java.util.Arrays;
import java.util.Collections;

public class LowensteinRecursiveDistance extends DistanceBase {
    public LowensteinRecursiveDistance(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    protected int calculateDistance() {
        return calculateRecursive(firstWord.length(), secondWord.length());
    }

    private int calculateRecursive(int firstWordLength, int secondWordLength) {
        int cost;

        if (firstWordLength == 0)
            return secondWordLength;
        if (secondWordLength == 0)
            return firstWordLength;

        if (firstWord.charAt(firstWordLength - 1) == secondWord.charAt(secondWordLength - 1))
            cost = 0;
        else
            cost = 1;


        return Collections.min(Arrays.asList(
                calculateRecursive(firstWordLength - 1, secondWordLength) + 1,
                calculateRecursive(firstWordLength, secondWordLength - 1) + 1,
                calculateRecursive(firstWordLength - 1, secondWordLength - 1) + cost
        ));
    }
}
