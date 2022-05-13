import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.ViewportUI;

public class BMeeting {
    static int verticeCount;
    static int edgeCount;
    static int adjacencyMatrix[][];
    static int adjacencyMatrixCopy[][];
    static boolean verticesVisited[];
    static int edgeVertices[][];
    static int minDistances[];
    static int passedEdges[][];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int toVertex;
        int fromVertex;

        verticeCount = in.nextInt();
        edgeCount = in.nextInt();

        adjacencyMatrix = new int[verticeCount+1][verticeCount+1];
        adjacencyMatrixCopy = adjacencyMatrix.clone();
        edgeVertices = new int[edgeCount][2];
        verticesVisited = new boolean[verticeCount+1];
        minDistances = new int[verticeCount + 1];
        


        Arrays.fill(minDistances, Integer.MAX_VALUE);
        Arrays.fill(verticesVisited, false);

        for (int i = 0; i < edgeCount; i++) {
            fromVertex = in.nextInt();
            toVertex = in.nextInt();
            edgeVertices[i][0] = fromVertex;
            edgeVertices[i][1] = toVertex;

            adjacencyMatrix[fromVertex][toVertex] = 1;
        }

        for (int i = 0; i < edgeCount; i++) {
            adjacencyMatrixCopy[edgeVertices[i][0]][edgeVertices[i][1]] = 0;
            //System.out.println(edgeVertices[i][0]);
            //System.out.println(edgeVertices[i][1]);

            dijkstraAlgorithm(1);

            if (minDistances[verticeCount] != Integer.MAX_VALUE) {
                System.out.println(minDistances[verticeCount]);
            } else {
                System.out.println(-1);
            }

            adjacencyMatrixCopy[edgeVertices[i][0]][edgeVertices[i][1]] = 1;
            Arrays.fill(minDistances, Integer.MAX_VALUE);
            Arrays.fill(verticesVisited, false);
        }
    }

    public static void dijkstraAlgorithm(int start) {

        minDistances[start] = 0;

        for (int i = 1; i < verticeCount + 1; i++) {
            int currentVertex = getMinIndex();
            // System.out.println(Arrays.toString(minDistances));
            // System.out.println(Arrays.toString(verticesVisited));
            // for(int q[]: adjacencyMatrixCopy) {
            //     System.out.println(Arrays.toString(q));
            // }

            verticesVisited[currentVertex] = true;

            for(int j = 1; j < verticeCount+1; j++) {
                if (!verticesVisited[j] && adjacencyMatrixCopy[currentVertex][j] != 0 && minDistances[currentVertex] != Integer.MAX_VALUE && adjacencyMatrixCopy[currentVertex][j] + minDistances[currentVertex] < minDistances[j]) {
                    minDistances[j] = adjacencyMatrixCopy[currentVertex][j] + minDistances[currentVertex];
                    //System.out.println("AAAA");
                }
            }
        }
    }

    public static int getMinIndex(){
        int minIndex = 1;
        int minValue = Integer.MAX_VALUE;

        for (int i = 1; i < verticeCount; i++) {
            if (minDistances[i] < minValue && !verticesVisited[i]){
                minValue = minDistances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
