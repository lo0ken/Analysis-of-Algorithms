package distance.tests;

import distance.LowensteinDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowensteinDistanceTest {

    @Test
    void calculateDistanceWithoutDiffirence() {
        String firstWord = "qwerty";
        String secondWord = "qwerty";

        LowensteinDistance distance = new LowensteinDistance(firstWord, secondWord);

        assertEquals(0, distance.calculate());
    }

    @Test
    void calculateDifferenceWithSwapTwoLetters() {
        String firstWord = "qwrety";
        String secondWord = "qwerty";

        LowensteinDistance distance = new LowensteinDistance(firstWord, secondWord);

        assertEquals(2, distance.calculate());
    }

    @Test
    void calculateDistanceWithOneMoreLetterDifference() {
        String firstWord = "qwe";
        String secondWord = "qwer";

        LowensteinDistance distance = new LowensteinDistance(firstWord, secondWord);

        assertEquals(1, distance.calculate());
    }

    @Test
    void calculateDistanceWithManyLettersDifference() {
        String firstWord = "blackcoat";
        String secondWord = "flashfort";

        LowensteinDistance distance = new LowensteinDistance(firstWord, secondWord);

        assertEquals(5, distance.calculate());
    }

    @Test
    void calculateDistanceWithEmptyWords() {
        String firstWord = "";
        String secondWord = "";

        LowensteinDistance distance = new LowensteinDistance(firstWord, secondWord);

        assertEquals(0, distance.calculate());
    }
}