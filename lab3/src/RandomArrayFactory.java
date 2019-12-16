import java.util.Random;

public class RandomArrayFactory {
    static public int[] getRandomArray(int size)
    {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size*10);
        }
        return arr;
    }

    static public int[] getIncreaseArray(int size)
    {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    static public int[] getDecreaseArray(int size)
    {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }
}