package graph;

import java.awt.*;
import java.util.Random;

public class Graph {
    private final static int LONGEST_WAY = 40;
    private static Random random = new Random();
    private int vertexCount;
    private int[][] distance;
    private Point[] position;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        position = new Point[vertexCount];
        distance = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            position[i] = new Point(random.nextInt(LONGEST_WAY), random.nextInt(LONGEST_WAY));
        }

        for (int i = 0; i < vertexCount; i++) {
            distance[i][i] = -1;

            for (int j = i + 1; j < vertexCount; j++) {
                int way = (int) position[i].distance(position[j]);
                distance[i][j] = way;
                distance[j][i] = way;
            }
        }
    }

    public int[][] getDistance() {
        return distance;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
