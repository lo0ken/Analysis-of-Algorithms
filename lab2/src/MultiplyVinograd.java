public class MultiplyVinograd implements MatrixMultiplier {

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

        for (int i = 0; i < firstRowCount; i++) {
            for (int j = 0; j < firstColumnCount / 2; j++) {
                rowFactors[i] += firstMatrix[i][j * 2] * firstMatrix[i][j * 2 + 1];
            }
        }

        for (int i = 0; i < secondColumnCount; i++) {
            for (int j = 0; j < secondRowCount / 2; j++) {
                columnFactors[i] += secondMatrix[j * 2][i] * secondMatrix[j * 2 + 1][i];
            }
        }

        for (int i = 0; i < firstRowCount; i++) {
            for (int j = 0; j < secondColumnCount; j++) {
                result[i][j] = -rowFactors[i] - columnFactors[j];
                for (int k = 0; k < firstColumnCount / 2; k++) {
                    result[i][j] += (firstMatrix[i][2 * k + 1] + secondMatrix[2 * k][j]) * (firstMatrix[i][2 * k] + secondMatrix[2 * k + 1][j]);
                }
            }
        }

        if (firstColumnCount % 2 == 1) {
            for (int i = 0; i < firstRowCount; i++) {
                for (int j = 0; j < secondColumnCount; j++) {
                    result[i][j] += firstMatrix[i][firstColumnCount - 1] * secondMatrix[firstColumnCount - 1][j];
                }
            }
        }

        return result;
    }
}
