package distance.tests;

import distance.LowensteinRecursiveDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowensteinRecursiveDistanceTest {
    @Test
    void calculateDistanceWithoutDiffirence() {
        String firstWord = "qwerty";
        String secondWord = "qwerty";

        LowensteinRecursiveDistance distance = new LowensteinRecursiveDistance(firstWord, secondWord);

        assertEquals(0, distance.calculate());
    }

    @Test
    void calculateDifferenceWithSwapTwoLetters() {
        String firstWord = "qwrety";
        String secondWord = "qwerty";

        LowensteinRecursiveDistance distance = new LowensteinRecursiveDistance(firstWord, secondWord);

        assertEquals(2, distance.calculate());
    }

    @Test
    void calculateDistanceWithOneMoreLetterDifference() {
        String firstWord = "qwe";
        String secondWord = "qwer";

        LowensteinRecursiveDistance distance = new LowensteinRecursiveDistance(firstWord, secondWord);

        assertEquals(1, distance.calculate());
    }

    @Test
    void calculateDistanceWithManyLettersDifference() {
        String firstWord = "blackcoat";
        String secondWord = "flashfort";

        LowensteinRecursiveDistance distance = new LowensteinRecursiveDistance(firstWord, secondWord);

        assertEquals(5, distance.calculate());
    }

    @Test
    void calculateDistanceWithEmptyWords() {
        String firstWord = "";
        String secondWord = "";

        LowensteinRecursiveDistance distance = new LowensteinRecursiveDistance(firstWord, secondWord);

        assertEquals(0, distance.calculate());
    }

}