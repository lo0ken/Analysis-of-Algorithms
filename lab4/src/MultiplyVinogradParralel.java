import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiplyVinogradParralel{
    public int[][] multiplyParralel(int[][] firstMatrix, int[][] secondMatrix, int threadsCount) throws InterruptedException {
        int firstRowCount = firstMatrix.length;
        int secondRowCount = secondMatrix.length;

        if (firstRowCount == 0 || secondRowCount == 0) {
            return null;
        }

        int firstColumnCount = firstMatrix[0].length;
        int secondColumnCount = secondMatrix[0].length;

        if (firstColumnCount != secondRowCount) {
            return null;
        }

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

        ExecutorService service = Executors.newFixedThreadPool(threadsCount);


        for (int i = 0; i < threadsCount; i++) {
            service.execute(new LoopExecutor(
                    firstMatrix,
                    secondMatrix,
                    result,
                    rowFactors,
                    columnFactors,
                    i,
                    threadsCount));
        }
        service.shutdown();

        while (!service.awaitTermination(24L, TimeUnit.HOURS)) {
            System.out.println("Not yet. Still waiting for termination");
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

    class LoopExecutor implements Runnable {
        private int[][] firstMatrix;
        private int[][] secondMatrix;
        private int[][] result;
        private int[] rowFactors;
        private int[] colFactors;
        private int startIndex;
        private int step;

        public LoopExecutor(int[][] firstMatrix, int[][] secondMatrix, int[][] result, int[] rowFactors, int[] colFactors, int startIndex, int step) {
            this.firstMatrix = firstMatrix;
            this.secondMatrix = secondMatrix;
            this.result = result;
            this.rowFactors = rowFactors;
            this.colFactors = colFactors;
            this.startIndex = startIndex;
            this.step = step;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < firstMatrix.length; i+=step) {
                for (int j = 0; j < secondMatrix[0].length; j++) {
                    result[i][j] = -rowFactors[i] - colFactors[j];
                    for (int k = 0; k < firstMatrix[0].length / 2; k++) {
                        result[i][j] += (firstMatrix[i][2 * k + 1] + secondMatrix[2 * k][j]) * (firstMatrix[i][2 * k] + secondMatrix[2 * k + 1][j]);
                    }
                }
            }
        }
    }
}
