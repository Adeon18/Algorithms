import java.util.Arrays;

public class DijkstraAlgorithm {

    public static int adjacencyMatrix[][];
    public static int pathLengths[];
    public static boolean verticesVisited[];
    public static final int MATRIX_SIZE = 5;
    public static void main(String[] args) {

        adjacencyMatrix = new int[][]{{0, 10, 2, 0, 0},
                                      {0, 0, 0, 1, 2},
                                      {0, 1, 0, 5, 8},
                                      {0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0}};
        
        pathLengths = new int[MATRIX_SIZE];
        verticesVisited = new boolean[MATRIX_SIZE];

        Arrays.fill(pathLengths, Integer.MAX_VALUE);
        Arrays.fill(verticesVisited, false);

        dijkstraAlgorithm(0);

        System.out.println(Arrays.toString(pathLengths));
    }

    public static void dijkstraAlgorithm(int start) {

        pathLengths[start] = 0;

        for (int i = 0; i < MATRIX_SIZE; i++) {
            int minIndex = getMinIndex();

            verticesVisited[minIndex] = true;

            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (adjacencyMatrix[minIndex][j] != 0 && !verticesVisited[j] && pathLengths[minIndex] != Integer.MAX_VALUE && adjacencyMatrix[minIndex][j] + pathLengths[minIndex] < pathLengths[j]){
                    pathLengths[j] = adjacencyMatrix[minIndex][j] + pathLengths[minIndex];
                }
            }
        }
    }

    public static int getMinIndex() {
        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;
 
        for (int i = 1; i < MATRIX_SIZE; i++)
            if (pathLengths[i] < minValue && verticesVisited[i] == false) {
                minValue = pathLengths[i];
                minIndex = i;
            }
 
        return minIndex;
    }
}
