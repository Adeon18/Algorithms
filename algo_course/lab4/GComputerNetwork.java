import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;



public class GComputerNetwork {

    static ArrayList<Integer>[] adjacencyList;
    static ArrayList<Integer>[] adjacencyReversedList;

    static boolean[] visited;
    static int[] verticeColor;
    static boolean[] incomingEdges;
    static boolean[] outgoingEdges;

    static ArrayList<Integer> verticeVisitOrder;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int computerCount = in.nextInt();
        int connectionCount = in.nextInt();
        int componentCount = 0;

        int fromPC;
        int toPC;

        adjacencyList = new ArrayList[computerCount];
        for (int i = 0; i < computerCount; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }

        adjacencyReversedList = new ArrayList[computerCount];
        for (int i = 0; i < computerCount; i++) {
            adjacencyReversedList[i] = new ArrayList<Integer>();
        }
        visited = new boolean[computerCount];
        verticeVisitOrder = new ArrayList<Integer>();
        // This line is here because Deque is stupid
        // verticeVisitOrder.add(0);
        verticeColor = new int[computerCount];
        Arrays.fill(verticeColor, -1);

        for (int i = 0; i < connectionCount; i++) {
            fromPC = in.nextInt();
            toPC = in.nextInt();

            adjacencyList[fromPC].add(toPC);
            adjacencyReversedList[toPC].add(fromPC);
        }
        // We depth first search the Arjacency list
        for (int i = 0; i < computerCount; i++) {
            if (visited[i]) {
                continue;
            }
            depthFirstSearch(i);
        }
        // We count components with the help of coloring / what was not colored, we color and add a component
        for (int i = 0; i < computerCount; i++) {
            // int orderVertex = verticeVisitOrder.pollFirst();
            int orderVertex = verticeVisitOrder.get(computerCount - i - 1);
            // System.out.println(verticeVisitOrder.toString());
            if (verticeColor[orderVertex] != -1) {
                continue;
            }
            colorDepthFirstSearch(orderVertex, componentCount);
            componentCount++;
        }

        incomingEdges = new boolean[componentCount];
        outgoingEdges = new boolean[componentCount];
        Arrays.fill(incomingEdges, false);
        Arrays.fill(outgoingEdges, false);
        
        // We check for incoming and outgoing edges, true means there IS an incoming or ourgoing edge
        // in the respective array. If the colors are the same, we simply continue
        for (int i = 0; i < computerCount; i++) {
            for (int j = 0; j < adjacencyList[i].size(); j++) {
                toPC = adjacencyList[i].get(j);

                if (verticeColor[i] == verticeColor[toPC]) {
                    continue;
                }
                outgoingEdges[verticeColor[i]] = true;
                incomingEdges[verticeColor[toPC]] = true;
            }
        }

        int inCount = 0;
        int outCount = 0;
        // Count the number of components without in or out - going edges.
        for (int i = 0; i < componentCount; i++) {
            if (!outgoingEdges[i]) {
                outCount++;
            }

            if (!incomingEdges[i]) {
                inCount++;
            }
        }
        if (componentCount == 1) {
            System.out.println(inCount + " " + 0);
        } else {
            System.out.println(inCount + " " + Math.max(inCount, outCount));
        }
    }
    // A DFS on a AdjacencyList with tracking path
    public static void depthFirstSearch(int start) {
        visited[start] = true;
        int toVerticesCount = adjacencyList[start].size();
        for (int i = 0; i < toVerticesCount; i++) {
            int toPC = adjacencyList[start].get(i);

            if (!visited[toPC]) {
                depthFirstSearch(toPC);
            }
        }

        // verticeVisitOrder.addLast(start);
        // System.out.println(start);
        verticeVisitOrder.add(start);
    }

    // A DFS on an AdjacencyList with graph coloring
    public static void colorDepthFirstSearch(int start, int color) {
        verticeColor[start] = color;
        int toVerticesCount = adjacencyReversedList[start].size();
        for (int i = 0; i < toVerticesCount; i++) {
            int toPC = adjacencyReversedList[start].get(i);
            if (verticeColor[toPC] == -1) {
                colorDepthFirstSearch(toPC, color);
            }
        }
    }
}
