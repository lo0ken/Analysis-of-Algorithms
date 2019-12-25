package algorithms;

import graph.Graph;
import graph.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BruteForce {
    public static int getShortestRoute(Graph graph) {
        int[] arr = initArray(graph.getVertexCount());
        var allTracks = findAllRoutes(arr);
        //var allTracks = new TracksManipulator(IntStream.of(routeIndexes).boxed().collect(Collectors.toList())).getAllTracks();

        int minDistance = Integer.MAX_VALUE;

        for (List<Integer> track : allTracks) {
            Route currentRoute = new Route(graph, track);
            int currentDistance = currentRoute.getDistance();
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
            }
        }

        return minDistance;
    }

    private static int factorial(int n) {
        return (n > 0) ? n * factorial(n - 1) : 1;
    }

    private static int[] initArray(int count) {
        int[] arr = new int[count + 1];
        for (int i = 0; i < count; i++) {
            arr[i] = i;
        }
        arr[count] = 0;
        return arr;
    }

    private static List<List<Integer>> findAllRoutes(int[] arr) {
        List<List<Integer>> routes = new ArrayList<>();

        if (arr.length == 3) {
            routes.add(new ArrayList<>() {{
                add(arr[0]);
                add(arr[1]);
                add(arr[2]);
            }});
            return routes;
        }

        int count = factorial(arr.length - 2);
        int max = arr.length - 2;
        int shift = max;
        while (count > 0) {
            int t = arr[shift];
            arr[shift] = arr[shift - 1];
            arr[shift - 1] = t;
            routes.add(IntStream.of(arr).boxed().collect(Collectors.toList()));
            count--;
            if (shift < 3) {
                shift = max;
            } else {
                shift--;
            }
        }
        return routes;
    }
}
