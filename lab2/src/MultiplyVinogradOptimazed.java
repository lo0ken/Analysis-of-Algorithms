public class MultiplyVinogradOptimazed implements MatrixMultiplier {

    @Override
    public int[][] multiply(int[][] firstMatrix, int[][] secondMatrix) {
        int firstRowCount = firstMatrix.length;
        int secondRowCount = secondMatrix.length;

        if (firstRowCount == 0 || secondRowCount == 0)
            return null;

        int firstColumnCount = firstMatrix[0].length;
        int secondColumnCount = secondMatrix[0].length;

        if (firstColumnCount != secondRowCount)
            return null;

        int[] rowFactors = new int[firstRowCount];
        int[] columnFactors = new int[secondColumnCount];

        int[][] result = new int[firstRowCount][secondColumnCount];

        int fColumnMod2 = firstColumnCount % 2;
        int sRowMod2 = secondRowCount % 2;

        for (int i = 0; i < firstRowCount; i++) {
            for (int j = 0; j < (firstColumnCount - fColumnMod2); j += 2) {
                rowFactors[i] += firstMatrix[i][j] * firstMatrix[i][j + 1];
            }
        }

        for (int i = 0; i < secondColumnCount; i++) {
            for (int j = 0; j < (secondRowCount - sRowMod2); j += 2) {
                columnFactors[i] += secondMatrix[j][i] * secondMatrix[j + 1][i];
            }
        }

        for (int i = 0; i < firstRowCount; i++) {
            for (int j = 0; j < secondColumnCount; j++) {
                int buff = -(rowFactors[i] + columnFactors[j]);
                for (int k = 0; k < (firstColumnCount - fColumnMod2); k += 2) {
                    buff += (firstMatrix[i][k + 1] + secondMatrix[k][j]) * (firstMatrix[i][k] + secondMatrix[k + 1][j]);
                }
                result[i][j] = buff;
            }
        }

        if (fColumnMod2 == 1) {
            for (int i = 0; i < firstRowCount; i++) {
                for (int j = 0; j < secondColumnCount; j++) {
                    result[i][j] += firstMatrix[i][firstColumnCount - 1] * secondMatrix[firstColumnCount - 1][j];
                }
            }
        }
        return result;
    }
}
