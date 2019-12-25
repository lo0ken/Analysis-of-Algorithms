package graph;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Integer> track;
    private int distance;

    public Route(Graph graph, List<Integer> track) {
        this.track = new ArrayList<>(track);
        calculateDistance(graph.getDistance());
    }

    private void calculateDistance(int[][] graphDistances) {
        for (int i = 0; i < track.size() - 1; i++) {
            distance += graphDistances[track.get(i)][track.get(i + 1)];
        }
    }

    public int getDistance() {
        return distance;
    }
}
