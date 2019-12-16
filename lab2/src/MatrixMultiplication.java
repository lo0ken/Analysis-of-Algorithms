import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class MatrixMultiplication {
    public static void printMatrix(int[][] matrix) {
        System.out.println("----------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(String.format("%6d", matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    public static void multiplyMatrix(MatrixMultiplier multiplier, int[][] firstMatrix, int[][] secondMatrix) {
        printMatrix(multiplier.multiply(firstMatrix, secondMatrix));
    }

    public static int[][] generateRandomMatrix(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10000 - 100 + 1);
            }
        }

        return matrix;
    }

    public static long getTimeForMultiply(MatrixMultiplier multiplier, int[][] firstMatrix, int[][] secondMatrix) {
        int repeats = 10;
        Instant start = Instant.now();
        for (int i = 0; i < repeats; i++) {
            multiplier.multiply(firstMatrix, secondMatrix);
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis() / repeats;
    }

    public static void main(String[] args) {
        int size = 1000;
        int[][] firstMatrix = generateRandomMatrix(size);
        int[][] secondMatrix = generateRandomMatrix(size);

        System.out.println(getTimeForMultiply(new MultiplyStandart(), firstMatrix, secondMatrix));
        System.out.println(getTimeForMultiply(new MultiplyVinograd(), firstMatrix, secondMatrix));
        System.out.println(getTimeForMultiply(new MultiplyVinogradOptimazed(), firstMatrix, secondMatrix));
    }
}