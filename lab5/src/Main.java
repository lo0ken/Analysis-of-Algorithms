import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static final int COUNT_CONVEYORS = 3;
    public static final int COUNT_TASKS = 5;

    public static void main(String[] args) throws InterruptedException {
        parralel(1000, 1100);
        linear(1000, 1100);
        //analyseParralel(100, 1100, 100);
        //analyseLinear(100, 1100, 100);
    }

    public static void analyseParralel(int minDelay, int maxDelay, int step) throws InterruptedException {
        System.out.println();
        int nSteps = (maxDelay - minDelay) / step;

        for (int i = 1; i <= nSteps; i++) {
            parralel(minDelay, minDelay + (step * i));
        }
    }

    public static void analyseLinear(int minDelay, int maxDelay, int step) throws InterruptedException {
        System.out.println();
        int nSteps = (maxDelay - minDelay) / step;

        for (int i = 1; i <= nSteps; i++) {
            linear(minDelay, minDelay + (step * i));
        }
    }

    public static void parralel(int minDelay, int maxDelay) throws InterruptedException {
        System.out.print("Parralel Difference: " + (maxDelay - minDelay));
        List<Task> allTasks = generateTasks();

        Thread[] threads = new Thread[COUNT_CONVEYORS];
        Conveyor[] conveyors = new Conveyor[COUNT_CONVEYORS];

        conveyors[COUNT_CONVEYORS - 1] = new Conveyor(COUNT_CONVEYORS - 1, minDelay);
        for (int i = COUNT_CONVEYORS - 2; i > 0; i--) {
            conveyors[i] = new Conveyor(i, minDelay, conveyors[i + 1]);
        }
        conveyors[0] = new Conveyor(0, maxDelay, allTasks, conveyors[1]);

        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT_CONVEYORS; i++) {
            threads[i] = new Thread(conveyors[i]);
            threads[i].start();
        }

        for (int i = 0; i < COUNT_CONVEYORS; i++) {
            threads[i].join();
        }
        long end = System.currentTimeMillis();
        System.out.println(" Time: " + (end - start));
    }

    public static void linear(int minDelay, int maxDelay) {
        System.out.print("Linear Difference: " + (maxDelay - minDelay));
        List<Task> allTasks = generateTasks();
        Conveyor[] conveyors = new Conveyor[COUNT_CONVEYORS];

        conveyors[COUNT_CONVEYORS - 1] = new Conveyor(COUNT_CONVEYORS - 1, minDelay);
        for (int i = COUNT_CONVEYORS - 2; i > 0; i--) {
            conveyors[i] = new Conveyor(i, minDelay, conveyors[i + 1]);
        }
        conveyors[0] = new Conveyor(0, maxDelay, allTasks, conveyors[1]);

        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT_CONVEYORS; i++) {
            conveyors[i].run();
        }
        long end = System.currentTimeMillis();
        System.out.println(" Time: " + (end - start));
    }

    public static List<Task> generateTasks() {
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < COUNT_TASKS; i++) {
            result.add(new Task(i, false));
        }
        result.add(new Task(-1, true));
        return result;
    }
}
