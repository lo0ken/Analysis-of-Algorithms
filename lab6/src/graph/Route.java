package graph;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Integer> track;
    private int distance;
    private int[][] graphDistances;

    public Route(Graph graph, List<Integer> track) {
        this.track = new ArrayList<>(track);
        graphDistances = graph.getDistance();
        distance = 0;
    }

    public void addVertex(int v) {
        track.add(v);
    }

    public int getDistance() {
        distance = 0;
        for (int i = 0; i < track.size() - 1; i++) {
            distance += graphDistances[track.get(i)][track.get(i + 1)];
        }
        return distance;
    }

    public List<Integer> getTrack() {
        return track;
    }
}
