package distance.tests;

import distance.LowensteinDamerauDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowensteinDamerauDistanceTest {
    @Test
    void calculateDistanceWithoutDiffirence() {
        String firstWord = "qwerty";
        String secondWord = "qwerty";

        LowensteinDamerauDistance distance = new LowensteinDamerauDistance(firstWord, secondWord);

        assertEquals(0, distance.calculate());
    }

    @Test
    void calculateDifferenceWithSwapTwoLetters() {
        String firstWord = "qwrety";
        String secondWord = "qwerty";

        LowensteinDamerauDistance distance = new LowensteinDamerauDistance(firstWord, secondWord);

        assertEquals(1, distance.calculate());
    }

    @Test
    void calculateDistanceWithOneMoreLetterDifference() {
        String firstWord = "qwe";
        String secondWord = "qwer";

        LowensteinDamerauDistance distance = new LowensteinDamerauDistance(firstWord, secondWord);

        assertEquals(1, distance.calculate());
    }

    @Test
    void calculateDistanceWithManyLettersDifference() {
        String firstWord = "blackcoat";
        String secondWord = "flashfort";

        LowensteinDamerauDistance distance = new LowensteinDamerauDistance(firstWord, secondWord);

        assertEquals(5, distance.calculate());
    }

    @Test
    void calculateDistanceWithEmptyWords() {
        String firstWord = "";
        String secondWord = "";

        LowensteinDamerauDistance distance = new LowensteinDamerauDistance(firstWord, secondWord);

        assertEquals(0, distance.calculate());
    }

}