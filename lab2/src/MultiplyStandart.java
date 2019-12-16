public class MultiplyStandart implements MatrixMultiplier {

    @Override
    public int[][] multiply(int[][] firstMatrix, int[][] secondMatrix) {
        int firstRowCount = firstMatrix.length;
        int secondRowCount = secondMatrix.length;

        if (firstRowCount == 0 || secondRowCount == 0)
            return null;

        int firstColumnCount = firstMatrix[0].length;
        int secondColumnCount = secondMatrix[0].length;

        if (firstColumnCount == 0 || secondColumnCount == 0)
            return null;

        if (firstColumnCount != secondRowCount)
            return null;

        int[][] result = new int[firstRowCount][secondColumnCount];

        for (int i = 0; i < firstRowCount; i++)
            for (int j = 0; j < secondColumnCount; j++)
                for (int k = 0; k < firstColumnCount; k++)
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];

        return result;
    }
}

