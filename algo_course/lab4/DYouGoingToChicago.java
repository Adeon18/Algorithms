import java.math.BigDecimal;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Scanner;


public class DYouGoingToChicago {
    static double[][] adjacencyMatrix;
    static boolean[] verticesVisited;
    static double[] biggestProbabilities;
    static int verticeCount;
    static int edgeCount;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        verticeCount = in.nextInt();
        edgeCount = in.nextInt();

        int toVertex;
        int fromVertex;
        double edgeProbability;

        adjacencyMatrix = new double[verticeCount + 1][verticeCount + 1];
        biggestProbabilities = new double[verticeCount + 1];
        Arrays.fill(biggestProbabilities, -1);
        verticesVisited = new boolean[verticeCount + 1];
        Arrays.fill(verticesVisited, false);

        for (int i = 0; i < edgeCount; i++) {
            toVertex = in.nextInt();
            fromVertex = in.nextInt();
            edgeProbability = in.nextFloat() / 100;
            
            adjacencyMatrix[toVertex][fromVertex] = edgeProbability;
            adjacencyMatrix[fromVertex][toVertex] = edgeProbability;
        }

        dijkstraAlgorithm(1);

        // System.out.println(Arrays.toString(shortestDistances));
        // for(double[] a : adjacencyMatrix) 
        //     System.out.println(Arrays.toString(a));

        // BigDecimal result = new BigDecimal(shortestDistances[verticeCount]);
        // System.out.format("%.6f percent\n", Math.round(biggestProbabilities[verticeCount] * (double)100000000.0) / (double)1000000.0);
        System.out.format("%.6f percent\n", biggestProbabilities[verticeCount] * 100.0);

    }
    // The modified Dijkstra algorithm to find the biggest probabiity(product) instead of the smallest distance
    public static void dijkstraAlgorithm(int start) {

        biggestProbabilities[start] = 1;

        for (int i = 1; i < verticeCount; i++) {
            int maxValueIndex = getMaxIndex();
            verticesVisited[maxValueIndex] = true;

            for (int j = 1; j < verticeCount + 1; j++) {
                if (!verticesVisited[j] && adjacencyMatrix[maxValueIndex][j] != 0 && biggestProbabilities[maxValueIndex] != -1.0 && biggestProbabilities[maxValueIndex] * adjacencyMatrix[maxValueIndex][j] > biggestProbabilities[j]) {
                    biggestProbabilities[j] = biggestProbabilities[maxValueIndex] * adjacencyMatrix[maxValueIndex][j];
                }
            }
        }
    }
    // Get the max probability from the 
    public static int getMaxIndex() {
        double maxValue = -1;
        int maxIndex = 0;
 
        for (int i = 1; i < verticeCount + 1; i++)
            if (biggestProbabilities[i] >= maxValue && verticesVisited[i] == false) {
                maxValue = biggestProbabilities[i];
                maxIndex = i;
            }
 
        return maxIndex;
    }
}
