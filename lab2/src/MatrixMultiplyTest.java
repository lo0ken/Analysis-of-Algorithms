
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixMultiplyTest {

    @Test
    void wrongSizeTest() {
        int sizeA = 2, sizeB = 3;

        int[][] a = new int[sizeA][sizeA];
        int[][] b = new int[sizeB][sizeB];

        var result = new MultiplyStandart().multiply(a, b);

        assertNull(result);
    }

    @Test
    void sizeEqualsOneTest() {
        int[][] a = new int[1][];
        a[0] = new int[] { 2 };

        int[][] b = new int[1][];
        b[0] = new int[] { 3 };

        var result = new MultiplyStandart().multiply(a, b);

        assertNotNull(result);
        assertEquals(6, result[0][0]);
    }

    @Test
    void sizeEqualsTwoTest() {
        int[][] a = new int[][] { new int[] { 1, 2 }, new int[] { 3, 4 } };
        int[][] b = new int[][] { new int[] { 5, 6 }, new int[] { 7, 8 } };

        var result = new MultiplyStandart().multiply(a, b);

        int[][] expected = new int[][] { new int[] { 19, 22 }, new int[] { 43, 50 } };

        assertNotNull(result);
        assertArrayEquals(expected, result);
    }

    @Test
    void evenStandartSameAsVinogradTest() {
        int size = 10;
        int[][] a = MatrixMultiplication.generateRandomMatrix(size);
        int[][] b = MatrixMultiplication.generateRandomMatrix(size);

        var resultStand = new MultiplyStandart().multiply(a, b);
        var resultVinograd = new MultiplyVinograd().multiply(a, b);

        assertNotNull(resultStand);
        assertNotNull(resultVinograd);

        assertArrayEquals(resultStand, resultVinograd);
    }

    @Test
    void evenStandartSameAsVinogradOptimazedTest() {
        int size = 10;
        int[][] a = MatrixMultiplication.generateRandomMatrix(size);
        int[][] b = MatrixMultiplication.generateRandomMatrix(size);

        var resultStand = new MultiplyStandart().multiply(a, b);
        var resultVinogradOptimazed = new MultiplyVinogradOptimazed().multiply(a, b);

        assertNotNull(resultStand);
        assertNotNull(resultVinogradOptimazed);

        assertArrayEquals(resultStand, resultVinogradOptimazed);
    }

    @Test
    void oddStandartSameAsVinogradTest() {
        int size = 11;
        int[][] a = MatrixMultiplication.generateRandomMatrix(size);
        int[][] b = MatrixMultiplication.generateRandomMatrix(size);

        var resultStand = new MultiplyStandart().multiply(a, b);
        var resultVinograd = new MultiplyVinograd().multiply(a, b);

        assertNotNull(resultStand);
        assertNotNull(resultVinograd);

        assertArrayEquals(resultStand, resultVinograd);
    }

    @Test
    void oddStandartSameAsVinogradOptimazedTest() {
        int size = 11;
        int[][] a = MatrixMultiplication.generateRandomMatrix(size);
        int[][] b = MatrixMultiplication.generateRandomMatrix(size);

        var resultStand = new MultiplyStandart().multiply(a, b);
        var resultVinogradOptimazed = new MultiplyVinogradOptimazed().multiply(a, b);

        assertNotNull(resultStand);
        assertNotNull(resultVinogradOptimazed);

        assertArrayEquals(resultStand, resultVinogradOptimazed);
    }

}
