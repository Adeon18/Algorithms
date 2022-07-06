import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class AReverseTheGraph {
    static int verticeCount;
    static int edgeCount;
    static ArrayList<ArrayList<AdjacencyObject>> adjacencyList;
    static int edgesReversed[];

    public static class AdjacencyObject{
        private int toVertex;
        private int toWeight;

        public AdjacencyObject(int toVertex, int toWeight) {
            this.toVertex = toVertex;
            this.toWeight = toWeight;
        }

        public int getToVertex() {
            return toVertex;
        }

        public int getToWeight() {
            return toWeight;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        verticeCount = in.nextInt();
        edgeCount = in.nextInt();
        int vertexTo, vertexFrom;

        adjacencyList = new ArrayList<ArrayList<AdjacencyObject>>(verticeCount + 1);
        for (int i = 0; i < verticeCount + 1; i++) {
            adjacencyList.add(new ArrayList<AdjacencyObject>());
        }
        // System.out.println(adjacencyList.toString());

        for (int i = 1; i < edgeCount + 1; i++) {
            vertexFrom = in.nextInt();
            vertexTo = in.nextInt();

            adjacencyList.get(vertexFrom).add(new AdjacencyObject(vertexTo, 0));
            adjacencyList.get(vertexTo).add(new AdjacencyObject(vertexFrom, 1));
        }

        BreadthFirstSearch(1);
        
        // System.out.println(Arrays.toString(reversedEdges));
        if (edgesReversed[verticeCount] == Integer.MAX_VALUE) {
            edgesReversed[verticeCount] = -1;
        }
        System.out.println(edgesReversed[verticeCount]);
    }

    public static void BreadthFirstSearch(int start) {
        edgesReversed = new int[verticeCount + 1];

        Arrays.fill(edgesReversed, Integer.MAX_VALUE);
        edgesReversed[start] = 0;

        Deque<Integer> deque = new ArrayDeque<Integer>();
        deque.add(start);

        while(!deque.isEmpty()) {
            // if (deque.isEmpty()) {
            //     break;
            // }
            int vertex = deque.pollFirst();
            int toVertex = 0;
            int toWeight = 0;
            int vertexListSize = adjacencyList.get(vertex).size();
            for (int i = 0; i < vertexListSize; i++) {
                toVertex = adjacencyList.get(vertex).get(i).getToVertex();
                toWeight = adjacencyList.get(vertex).get(i).getToWeight();
                // The min amount of edges to be reversed to get to ToVertex
                // System.out.println(edgesReversed[vertex]);
                if (edgesReversed[vertex] + toWeight < edgesReversed[toVertex]) {
                    // System.out.println(Arrays.toString(edgesReversed));
                    edgesReversed[toVertex] = edgesReversed[vertex] + toWeight;
                    // We find the min amount of reversed edges, so we prioritize the smaller weight
                    if (toWeight == 0) {
                        deque.addFirst(toVertex);
                    } else {
                        deque.addLast(toVertex);
                    }
                }
            }
        }
    }
}

