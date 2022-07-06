import java.util.Arrays;
import java.util.Stack;


public class RecursiveDFS {
    public static int adjacencyMatrix[][];
    public static boolean verticesVisited[];
    public static final int MATRIX_SIZE = 4;
    public static void main(String[] args) {

        adjacencyMatrix = new int[][]{{0, 1, 1, 0},
                                      {0, 0, 1, 0},
                                      {1, 0, 0, 1},
                                      {0, 0, 0, 1}};
        
        verticesVisited = new boolean[MATRIX_SIZE];
        Arrays.fill(verticesVisited, false);

        depthFirstSearch(2);
    }

    public static void depthFirstSearch(int start) {

        verticesVisited[start] = true;
        System.out.println(start);

        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (!verticesVisited[i] && adjacencyMatrix[start][i] > 0) {
                depthFirstSearch(i);
            }
        }
    }
}