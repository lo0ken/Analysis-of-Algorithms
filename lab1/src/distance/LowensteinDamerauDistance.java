package distance;

public class LowensteinDamerauDistance extends LowensteinDistance {
    public LowensteinDamerauDistance(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    protected void getNextElementInMatrix(int i, int j) {
        super.getNextElementInMatrix(i, j);

        if (adjacentLetterEqual(i, j))
            matrix[i][j] = Math.min(matrix[i][j], matrix[i - 2][j - 2] + calculateCost(i, j));
    }

    private boolean adjacentLetterEqual(int i, int j) {
        if (i > 1 && j > 1 &&
                firstWord.charAt(i - 1) == secondWord.charAt(j - 2) &&
                firstWord.charAt(i - 2) == secondWord.charAt(j - 1)) {
            return true;
        }
        return false;
    }
}
