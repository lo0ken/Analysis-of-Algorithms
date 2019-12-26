import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

class StrMatchingTest {
    private static Random random = new Random();
    private static int N = 500;

    @Test
    public void testStandardRandom() {
        for (int i = 0; i < N; i++) {
            String s = Character.toString(random.nextInt(999999) + 1000);
            String sub = Character.toString(random.nextInt(999) + 100);

            int correctIndex = s.indexOf(sub);
            int resultIndex = StrMatching.standard(s, sub);
            Assert.assertEquals( "str: " + s + " sub: " + sub, correctIndex, resultIndex);
        }
    }

    @Test
    public void TestKMPRandom() {
        for (int i = 0; i < N; i++) {
            String s = Character.toString(random.nextInt(999999) + 1000);
            String sub = Character.toString(random.nextInt(999) + 100);

            int correctIndex = s.indexOf(sub);
            int resultIndex = StrMatching.KMP(s, sub);
            Assert.assertEquals( "str: " + s + " sub: " + sub, correctIndex, resultIndex);
        }
    }

    @Test
    public void TestBMRandom() {
        for (int i = 0; i < N; i++) {
            String s = Character.toString(random.nextInt(999999) + 1000);
            String sub = Character.toString(random.nextInt(999) + 100);

            int correctIndex = s.indexOf(sub);
            int resultIndex = StrMatching.BM(s, sub);
            Assert.assertEquals( "str: " + s + " sub: " + sub, correctIndex, resultIndex);
        }
    }

    @Test
    public void TestStandardSameLength() {
        for (int i = 0; i < N; i++) {
            String s = Character.toString(random.nextInt(999) + 100);
            String sub = Character.toString(random.nextInt(999) + 100);

            int correctIndex = s.indexOf(sub);
            int resultIndex = StrMatching.standard(s, sub);
            Assert.assertEquals( "str: " + s + " sub: " + sub, correctIndex, resultIndex);
        }
    }

    @Test
    public void TestKMPSameLength() {
        for (int i = 0; i < N; i++) {
            String s = Character.toString(random.nextInt(999) + 100);
            String sub = Character.toString(random.nextInt(999) + 100);

            int correctIndex = s.indexOf(sub);
            int resultIndex = StrMatching.KMP(s, sub);
            Assert.assertEquals( "str: " + s + " sub: " + sub, correctIndex, resultIndex);
        }
    }

    @Test
    public void TestBMSameLength() {
        for (int i = 0; i < N; i++) {
            String s = Character.toString(random.nextInt(999) + 100);
            String sub = Character.toString(random.nextInt(999) + 100);

            int correctIndex = s.indexOf(sub);
            int resultIndex = StrMatching.BM(s, sub);
            Assert.assertEquals( "str: " + s + " sub: " + sub, correctIndex, resultIndex);
        }
    }
}