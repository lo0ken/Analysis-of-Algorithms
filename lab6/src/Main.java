import algorithms.AntAlgorithm;
import algorithms.BruteForce;
import graph.Graph;

public class Main {

    // Код алгоритмов плохой, сделан только для сдачи лабы :)
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        System.out.println(getTimeBrute(graph));
        System.out.println(getTimeAnt(graph));
    }

    public static long getTimeBrute(Graph graph) {
        int repeats = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < repeats; i++) {
            BruteForce.getShortestRoute(graph);
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public static long getTimeAnt(Graph graph) {
        int repeats = 10;
        long start = System.currentTimeMillis();
        for (int i = 0; i < repeats; i++) {
            AntAlgorithm.getShortestRoute(graph);
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }
}
