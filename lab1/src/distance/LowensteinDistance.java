package distance;

public class LowensteinDistance extends DistanceBase {
    protected int[][] matrix;

    public LowensteinDistance(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    protected int calculateDistance() {
        matrix = new int[firstWord.length() + 1][secondWord.length() + 1];
        fillMatrix();
        findDistanceInMatrix();
        return getResultFromMatrix();
    }

    protected void getNextElementInMatrix(int i, int j) {
        int cost = calculateCost(i, j);

        matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + cost);
    }

    protected int calculateCost(int i, int j) {
        return (firstWord.charAt(i - 1) == (secondWord.charAt(j - 1))) ? 0 : 1;
    }

    private void findDistanceInMatrix() {
        for (int i = 1; i <= firstWord.length(); i++) {
            for (int j = 1; j <= secondWord.length(); j++) {
                getNextElementInMatrix(i, j);
            }
        }
    }

    private void fillMatrix() {
        fillMatrixFirstColumn();
        fillMatrixFirstRow();
    }

    private void fillMatrixFirstColumn() {
        for (int i = 0; i < firstWord.length() + 1; i++) {
            matrix[i][0] = i;
        }
    }

    private void fillMatrixFirstRow() {
        for (int i = 0; i < secondWord.length() + 1; i++) {
            matrix[0][i] = i;
        }
    }

    private int getResultFromMatrix() {
        return matrix[firstWord.length()][secondWord.length()];
    }

    @Override
    public String toString() {

        StringBuilder distanceMatrix = new StringBuilder("\n\n");
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
                distanceMatrix.append(matrix[i][j] + "\t");
            distanceMatrix.append("\n");
        }
        distanceMatrix.append("\n\n");

        return distanceMatrix.toString();
    }
}
