import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


public class HDividingTheGraph {
    static int[][] adjacencyMatrix;
    static int[][] residualAdjMatrix;
    static int[] parentArray;
    static boolean[] verticesVisited;
    static int verticeCount;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        verticeCount = in.nextInt();
        
        adjacencyMatrix = new int[verticeCount][verticeCount];
        parentArray = new int[verticeCount];
        verticesVisited = new boolean[verticeCount];

        String line = in.nextLine();

        for (int i = 0; i < verticeCount; i++) {
            line = in.nextLine();
            // System.out.println(line);
            for (int j = 0; j < line.length(); j++) {
                adjacencyMatrix[i][j] = (int)line.charAt(j) - 48;
            }
        }

        // for(int[] a : adjacencyMatrix) 
        //         System.out.println(Arrays.toString(a));

        // Run ford fulkerson to get the cut
        fordFulkersonAlgorithm(1, verticeCount - 1);

        // Reload the visited vertices for dfs
        verticesVisited = new boolean[verticeCount];
        Arrays.fill(verticesVisited, false);
        // Run the dfs
        depthFirstSearch(1);

        // Print out the result
        for (int i = 0; i < verticesVisited.length; i++) {
            if (verticesVisited[i]) {
                i++;
                System.out.print(i + " ");
                i--;
            }
        }
        System.out.println();

        for (int i = 0; i < verticesVisited.length; i++) {
            if (!verticesVisited[i]) {
                i++;
                System.out.print(i + " ");
                i--;
            }
        }
        
    }

    // A simple BFS that returns true if there is a way from start to end
    // Uses ArrayDeque
    public static boolean breadthFirstSearch(int start, int end) {
        Arrays.fill(verticesVisited, false);

        verticesVisited[start] = true;
        parentArray[start] = -1;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        deque.push(start);

        while (true) {
            if (deque.isEmpty()) {
                //System.out.println("Deque Empty");
                break;
            }

            int vertex = deque.pollFirst();

            for (int i = 0; i < verticeCount; i++) {
                if (residualAdjMatrix[vertex][i] != 0 && !verticesVisited[i]) {

                    parentArray[i] = vertex;
                    verticesVisited[i] = true;
                    deque.push(i);
                    // System.out.println("Deque:" + deque.toString());
                }
            }
        }
        if (verticesVisited[end]) {
            return true;
        }
        return false;
    }
    // Ford Fulkerson Max flow algorithm
    public static int fordFulkersonAlgorithm(int start, int end) {

        residualAdjMatrix = adjacencyMatrix.clone();

        int maxFlow = 0;
        int pathFlow = Integer.MAX_VALUE;

        while (true) {
            // System.out.println(Arrays.toString(parentArray));
            // It works only if there is a path from start to end
            if (!breadthFirstSearch(start, end)) {
                break;
            }
            pathFlow = Integer.MAX_VALUE;
            
            // Find min residual capacity
            int i = end;
            while(i != start) {
                // if (i != 0)
                // System.out.println(i);
                int iParent = parentArray[i];

                if (residualAdjMatrix[iParent][i] < pathFlow) {
                    pathFlow = residualAdjMatrix[iParent][i];
                }
                i = parentArray[i];
            }
            // Update the residual graph
            i = end;
            while(i != start) {
                int iParent = parentArray[i];

                residualAdjMatrix[i][iParent] += pathFlow;
                residualAdjMatrix[iParent][i] -= pathFlow;

                i = parentArray[i];
            }

            maxFlow += pathFlow;
        }
        return maxFlow;
    }
    // A regular DFS on a Adjacency matrix
    public static void depthFirstSearch(int start) {

        verticesVisited[start] = true;

        for (int i = 0; i < verticeCount; i++) {
            if (!verticesVisited[i] && residualAdjMatrix[start][i] > 0) {
                depthFirstSearch(i);
            }
        }
    }
}

