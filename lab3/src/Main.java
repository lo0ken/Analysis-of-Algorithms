import java.time.Duration;
import java.time.Instant;

public class Main {

    public static long getTimeForBubbleSort(int[] arr) {
        Instant start = Instant.now();
        SortUtils.bubbleSort(arr);
        Instant end = Instant.now();

        return Duration.between(start, end).toMillis();
    }
    public static long getTimeForInsertionSort(int[] arr) {
        Instant start = Instant.now();
        SortUtils.insertionSort(arr);
        Instant end = Instant.now();

        return Duration.between(start, end).toMillis();
    }
    public static long getTimeForQuickSort(int[] arr) {
        Instant start = Instant.now();
        SortUtils.quickSort(arr, 0, arr.length - 1);
        Instant end = Instant.now();

        return Duration.between(start, end).toMillis();
    }

    public static void main(String[] args) {
	    int[] arr = RandomArrayFactory.getRandomArray(500);

        System.out.println(getTimeForBubbleSort(arr));
        System.out.println(getTimeForInsertionSort(arr));
        System.out.println(getTimeForQuickSort(arr));
    }
}
