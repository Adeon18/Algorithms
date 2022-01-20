import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class EACMContestAndBlackout {

    private static int parentEdges[];
    private static List<Integer> nodesInMST;
    private static Edge edges[];

    public static class Edge implements Comparable<Edge>{
        private int from;
        private int to;
        private Integer weight;

        public Edge(int from, int to, Integer weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Edge() {

        }

        @Override
        public int compareTo(Edge e) {
            return this.getWeight().compareTo(e.getWeight());
        }

        public Integer getWeight() {
            return weight;
        }

        public int getTo() {
            return to;
        }

        public int getFrom() {
            return from;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int verticeCount = in.nextInt();
        int edgeCount = in.nextInt();

        int cheapestConnection = 0;
        int secondCheapestConnection = 0;
        int secondCheapestConnectionFinal = Integer.MAX_VALUE;

        parentEdges = new int[verticeCount + 1];
        for (int i = 1; i < verticeCount + 1; i++) {
            parentEdges[i] = i;
        }
        nodesInMST = new ArrayList<Integer>();

        edges = new Edge[edgeCount];
 
        for (int i = 0; i < edgeCount; i++) {
            edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        Arrays.sort(edges);

        for (int i = 0; i < edgeCount; i++) {
            cheapestConnection = cheapestMSTWeight(i, cheapestConnection);
        }

        System.out.print(cheapestConnection + " ");
        // Save the original List of nodes in MST
        List<Integer> nodesInMSTOrigin = new ArrayList<>(nodesInMST);
        int length = nodesInMSTOrigin.size();
        for (int i = 0; i < length; i++) {
            nodesInMST.clear();
            // We will have new parent nodes
            for (int q = 1; q < verticeCount + 1; q++) {
                parentEdges[q] = q;
            }
            // Remove the vertices from mst one by one to find the second mst
            for (int j = 0; j < edgeCount; j++) {
                if (nodesInMSTOrigin.get(i) != j) {
                    secondCheapestConnection = cheapestMSTWeight(j, secondCheapestConnection);
                }
            }
            // Check if it is a spanning tree
            if (nodesInMST.size() != verticeCount - 1){
                secondCheapestConnection = 0;
                continue;
            }
            

            secondCheapestConnectionFinal = Math.min(secondCheapestConnection, secondCheapestConnectionFinal);

            secondCheapestConnection = 0;
        }

        System.out.println(secondCheapestConnectionFinal);
    }
    // Parent find
    public static int findRec(int pos) {
        if (parentEdges[pos] == pos) {
            return pos;
        }
        parentEdges[pos] = findRec(parentEdges[pos]);
        return parentEdges[pos];
    }

    public static int cheapestMSTWeight(int pos, int mstSum){

        int edge = findRec(edges[pos].getFrom());
        int parentEdge = findRec(edges[pos].getTo());

        if (edge == parentEdge) {
            return mstSum;
        }
        // Set the parent to an edge, add it to mst e's, add the weight to our sum
        parentEdges[edge] = parentEdge;
        nodesInMST.add(pos);
        mstSum += edges[pos].getWeight();

        return mstSum;
    }
}
