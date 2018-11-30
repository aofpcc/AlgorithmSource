/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedy;

/**
 * @author Gaming PC
 */
public class DijkstraAlgorithm {

    private static int NO_PARENT = -1;

    public static void dijkstra(int[][] adjacencyVertices, int startVertex) {
        int nVertice = adjacencyVertices.length;

        int[] shortestDistances = new int[nVertice];
        boolean[] added = new boolean[nVertice];

        for (int i = 0; i < nVertice; ++i) {
            shortestDistances[i] = Integer.MAX_VALUE;
            added[i] = false;
        }

        shortestDistances[startVertex] = 0;

        int[] parents = new int[nVertice];

        parents[startVertex] = NO_PARENT;

        for (int i = 1; i < nVertice; ++i) {
            int shortestDistance = Integer.MAX_VALUE;
            int nearestVertex = -1;
            for (int j = 0; j < nVertice; ++j) {
                if (!added[j] && shortestDistances[j] < shortestDistance) {
                    nearestVertex = j;
                    shortestDistance = shortestDistances[j];
                }
            }

            added[nearestVertex] = true;

            for (int j = 0; j < nVertice; ++j) {
                int distance = adjacencyVertices[nearestVertex][j];
                if (distance > 0 && distance + shortestDistance < shortestDistances[j]) {
                    shortestDistances[j] = distance + shortestDistance;
                    parents[j] = nearestVertex;
                }
            }
        }
        printSolution(shortestDistances, parents, startVertex);
    }

    public static void printSolution(int[] shortestDistances, int[] parents, int startVertex) {
        System.out.println("Vertex\t\tDistances\tPaths");
        for (int i = 0; i < shortestDistances.length; ++i) {
            if (i == startVertex) {
                continue;
            }
            System.out.print(startVertex + " -> " + i + "\t\t");
            System.out.print(shortestDistances[i] + "\t\t");
            printPath(i, parents);
            System.out.println("");
        }
    }

    public static void printPath(int current, int[] parents) {
        if (current == NO_PARENT) {
            return;
        }
        printPath(parents[current], parents);
        System.out.print(current + " ");
    }

    public static void main(String... args) {
        int graph[][] = new int[][]{
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        dijkstra(graph, 0);
    }
}
