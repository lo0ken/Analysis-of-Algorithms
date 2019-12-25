package algorithms;

import graph.Graph;
import graph.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BruteForce {
    public static Route getShortestRoute(Graph graph) {
        int[] routeIndexes = new int[graph.getVertexCount() - 1];
        for (int i = 0; i < graph.getVertexCount() - 1; i++) {
            routeIndexes[i] = i + 1;
        }

        var allTracks = new TracksManipulator(IntStream.of(routeIndexes).boxed().collect(Collectors.toList())).getAllTracks();

        int minDistance = Integer.MAX_VALUE;
        Route shortestRoute = null;

        for (List<Integer> track : allTracks) {
            track.add(0, 0);
            track.add(0);
            Route currentRoute = new Route(graph, track);
            int currentDistance = currentRoute.getDistance();
            if (currentDistance < minDistance) {
                minDistance =  currentDistance;
                shortestRoute = currentRoute;
            }
        }

        return shortestRoute;
    }

    private static class TracksManipulator {
        private List<Integer> vertexes;
        private List<Integer> currentRoute;
        private List<List<Integer>> result;

        TracksManipulator(List<Integer> vertexes) {
            this.vertexes = vertexes;
            currentRoute = new ArrayList<>();
            result = new ArrayList<>();
        }

        public List<List<Integer>> getAllTracks() {
            if (vertexes.size() == 0) {
                result.add(currentRoute);
                return result;
            }

            for (int i = 0; i < vertexes.size(); i++) {
                List<Integer> nextVertexes = new ArrayList<>(vertexes);
                nextVertexes.remove(i);
                List<Integer> nextRoute = new ArrayList<>(currentRoute);
                nextRoute.add(vertexes.get(i));

                vertexes = nextVertexes;
                currentRoute = nextRoute;
                getAllTracks();
            }
            return result;
        }
    }
}
