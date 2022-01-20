import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


public class JCafe {
    static int[][] adjacencyMatrix;
    static int[][] residualAdjMatrix;
    static int[] parentArray;
    static boolean[] verticesVisited;
    static int[] coffeTypes;
    static int[] cakeTypes;
    static int verticeCount;
    static int coffeCount;
    static int cakeCount;
    static int matrixSize;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        verticeCount = in.nextInt();
        coffeCount = in.nextInt();
        cakeCount = in.nextInt();
        // Our size consists of all n m and k + 2 + 1, because indexing is from 1
        matrixSize = 2 * verticeCount + coffeCount + cakeCount + 3;
        adjacencyMatrix = new int[matrixSize][matrixSize];
        verticesVisited = new boolean[matrixSize];
        parentArray = new int[matrixSize];

        coffeTypes = new int[coffeCount + 1];
        cakeTypes = new int[cakeCount + 1];
        // Connect coffes
        for (int i = 1; i < coffeCount + 1; i++) {
            coffeTypes[i] = in.nextInt();
            adjacencyMatrix[1][i+1] = coffeTypes[i];
        }
        // Read cakes
        for (int i = 1; i < cakeCount + 1; i++) {
            cakeTypes[i] = in.nextInt();
        }
        // Connect coffes to people
        for (int i = 1; i < verticeCount + 1; i++) {
            int typeCount = in.nextInt();

            for (int j = 1; j < typeCount + 1; j++) {
                int type = in.nextInt();
                adjacencyMatrix[type + 1][1 + coffeCount + i] = 1;
            }
        }
        // Connect people to people(graph middle)
        for (int i = 1; i < verticeCount + 1; i++) {
            adjacencyMatrix[1 + coffeCount + i][1 + coffeCount + verticeCount + i] = 1;
        }
        // Connect cakes to graph
        for (int i = 1; i < verticeCount + 1; i++) {
            int typeCount = in.nextInt();
            // Debug matrix printing
            // for(int[] a : adjacencyMatrix) 
            //     System.out.println(Arrays.toString(a));
            //     System.out.println("---");
            for (int j = 1; j < typeCount + 1; j++) {
                int type = in.nextInt();
                adjacencyMatrix[1 + coffeCount + verticeCount + i][1 + coffeCount + 2 * verticeCount + type] = 1;
            }
        }
        // Connect cakes to the end Node
        for (int i = 1; i < cakeCount + 1; i++) {
            adjacencyMatrix[1 + coffeCount + 2 * verticeCount + i][matrixSize - 1] = cakeTypes[i];
        }
        // Result matrix
        // for(int[] a : adjacencyMatrix) 
        //     System.out.println(Arrays.toString(a));
        //     System.out.println("---");

        System.out.println(fordFulkersonAlgorithm(1, matrixSize - 1));
    
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

            for (int i = 1; i < matrixSize; i++) {
                if (residualAdjMatrix[vertex][i] != 0 && !verticesVisited[i]) {

                    if (i == end) {
                        parentArray[i] = vertex;
                        return true;
                    }

                    parentArray[i] = vertex;
                    verticesVisited[i] = true;
                    deque.push(i);
                    // System.out.println("Deque:" + deque.toString());
                }
            }
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
}
