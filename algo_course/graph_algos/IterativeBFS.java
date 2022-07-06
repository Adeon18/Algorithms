import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class IterativeBFS {
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

        iterativeBreadthFirstSearch(2);
    }

    public static void iterativeBreadthFirstSearch(int start) {
        
        Deque<Integer> deque = new ArrayDeque<Integer>();

        verticesVisited[start] = true;
        deque.push(start);
        System.out.println(start);

        while (!deque.isEmpty()) {
            int currentVertex = deque.pollFirst();

            for (int i = 0; i < adjacencyMatrix[0].length; i++) {
                if (adjacencyMatrix[currentVertex][i] != 0 && !verticesVisited[i]) {

                    System.out.println(i);
                    verticesVisited[i] = true;
                    deque.push(i);
                }
            }
        }
    }
}