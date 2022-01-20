import java.util.Arrays;
import java.util.Scanner;

/* 
Eolymp Wormholes Problem 

Uses Floyd-Warshall Algorithm.

Baaaarely passes on Java.
*/

public class BWormholes {
    static int[][] adjacencyMatrix;
    static int[][] floydClosestDist;
    static int verticeCount;
    static int edgeCount;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int toVertex;
        int fromVertex;
        int holeWeight;

        verticeCount = in.nextInt();
        edgeCount = in.nextInt();

        adjacencyMatrix = new int[verticeCount][verticeCount];
        // Build adjacency matrix
        for (int i = 0; i < edgeCount; i++) {
            fromVertex = in.nextInt();
            toVertex = in.nextInt();
            holeWeight = in.nextInt();

            adjacencyMatrix[fromVertex][toVertex] = holeWeight;
        }
        // Fill all zeros with Infinity
        for (int i = 0; i < verticeCount; i++) {
            for (int j = 0; j < verticeCount; j++) {
                if (adjacencyMatrix[i][j] == 0) {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // for(int[] a : adjacencyMatrix) 
        //     System.out.println(Arrays.toString(a));
        floydWarshallAlgorithm();
        // for(int[] a : floydClosestDist) 
        //     System.out.println(Arrays.toString(a));
        checkForTravelPossibility();
    }
    // A simple Floyd-Warshall Algorithm
    public static void floydWarshallAlgorithm() {
        floydClosestDist = adjacencyMatrix.clone();

        for (int i = 0; i < verticeCount; i++) {
            for (int j = 0; j < verticeCount; j++) {
                if (floydClosestDist[i][j] != Integer.MAX_VALUE) {
                    for (int k = 0; k < verticeCount; k++) {
                        if (floydClosestDist[j][k] != Integer.MAX_VALUE) {
                            if (floydClosestDist[i][j] + floydClosestDist[j][k] < floydClosestDist[i][k]) {
                                floydClosestDist[i][k] = floydClosestDist[i][j] + floydClosestDist[j][k];
                            }
                        }
                    }
                }
            }
        }
    }
    // Determine whether you can time travel
    public static void checkForTravelPossibility() {
        for (int i = 0; i < verticeCount; i++) {
            for (int j = 0; j < verticeCount; j++) {
                if (floydClosestDist[i][j] != Integer.MAX_VALUE && floydClosestDist[j][i] != Integer.MAX_VALUE) {
                    if (floydClosestDist[i][j] + floydClosestDist[j][i] < 0) {
                        System.out.println("possible");
                        return;
                    }
                }
            }
        }
        System.out.println("not possible");
        return;
    }
}