package algorithms;

import graph.Graph;
import graph.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AntAlgorithm {
    public static final int DAYS = 30;
    public static final double ALPHA = 0.7; // Параметр влияния длины пути
    public static final double BETA = 0.3; // Параметр влияния феронома
    public static final double FEROMON_QUANTITY = 4; // Кол-во феромона переносимого муравьём
    public static final double VAPEROTION_COEF = 0.1; // Коэффициент испарения феромона

    private static Random random = new Random();

    public static int getShortestRoute(Graph graph) {
        int minDistance = Integer.MAX_VALUE;
        int vertexCount = graph.getVertexCount();
        Route shortestRoute = null;

        double[][] pheromones = fillArrayWithValues(vertexCount, 0.1);

        List<Ant> ants;

        for (int i = 0; i < DAYS; i++) {
            ants = initAnts(graph);

            for (int j = 0; j < vertexCount - 1; j++) {
                double[][] pheromonesCur = fillArrayWithValues(vertexCount, 0.0);

                for (int k = 0; k < ants.size(); k++) {
                    double sumChance = 0, chance = 0;

                    Ant currentAnt = ants.get(k);
                    List<Integer> antTrack = currentAnt.getRoute().getTrack();
                    int currentVertex = antTrack.get(antTrack.size() - 1);

                    for (int vertexId = 0; vertexId < vertexCount; vertexId++) {
                        if (!currentAnt.visited[vertexId]) {
                            sumChance += Math.pow(pheromones[currentVertex][vertexId], ALPHA) * Math.pow(1 / (graph.getDistance()[currentVertex][vertexId]), BETA);
                        }
                    }

                    double x = random.nextDouble();
                    int t = 0;
                    for (; x > 0; t++) {
                        if (!currentAnt.visited[t]) {
                            chance = Math.pow(pheromones[currentVertex][t], ALPHA) * Math.pow(1 / (graph.getDistance()[currentVertex][t]), BETA);
                            chance /= sumChance;
                            x -= chance;
                        }
                    }
                    t--;
                    ants.get(k).visitVertex(t);
                    pheromonesCur[currentVertex][t] += FEROMON_QUANTITY / (graph.getDistance()[currentVertex][t]);
                }

                for (int k = 0; k < vertexCount; k++) {
                    for (int l = 0; l < vertexCount; l++) {
                        pheromones[k][l] = (1 - VAPEROTION_COEF) * pheromones[k][l] + pheromonesCur[k][l];
                    }
                }

                for (Ant ant : ants) {
                    ant.visitVertex(ant.getStartVertex());
                    int currentDistance = ant.getDistance();
                    System.out.println(currentDistance);
                    if (currentDistance < minDistance) {
                        minDistance = currentDistance;
                        shortestRoute = ant.getRoute();
                    }
                }
            }
        }
        return minDistance;
    }


    private static List<Ant> initAnts(Graph graph) {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            ants.add(new Ant(graph, 1));
        }
        return ants;
    }

    private static double[][] fillArrayWithValues(int size, double value) {
        double[][] arr = new double[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(arr[i], value);
        }
        return arr;
    }

    static class Ant {
        private Route route;
        private boolean[] visited;
        private int startVertex;

        Ant(Graph graph, int startVertex) {
            this.startVertex = startVertex;
            route = new Route(graph, new ArrayList<>() {{
                add(startVertex);
            }});
            visited = new boolean[graph.getVertexCount()];
            visited[startVertex] = true;
        }

        public void visitVertex(int vertexNumber) {
            route.addVertex(vertexNumber);
            visited[vertexNumber] = true;
        }

        public int getDistance() {
            return route.getDistance();
        }

        public int getStartVertex() {
            return startVertex;
        }

        public Route getRoute() {
            return route;
        }
    }
}
