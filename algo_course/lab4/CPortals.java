import java.util.Arrays;
import java.util.Scanner;

/*
Portals

Some planets of the galaxy, where Peter Pyatochkin recently moved, are connected with portals.
If planets A and B are connected, it means that each planet has a special device - portal - for teleportation between them.
The creature that enters the portal on the planet A, instantly appears on the planet B and vice versa.

The same portal can not be used to teleport to different planets.
If some pair of planets are not connected yet, they can be united, but a new portal must be built on each planet.
Building portals requires considerable outlay and can cost differently on different planets.

We say that there is a teleport way between two planets, if you can get from one planet to another, teleporting one or more times (using intermediate planets).
Unfortunately, there is no teleportation way between any two planets.

You are given the communication scheme among the planets and the cost of the portal on each planet.
Determine the lowest amount of money you need to spend to ensure the existence of a teleportation path between each pair of planets in the galaxy.

Input---
The first line contains two integers: the number of planets in the galaxy n (n ≤ 1000) and the number of planets pairs m, directly connected with portals.

Second line contains n positive integers p1, p2, …, pn - the cost to build a new portal, respectively on the first, on the second, ...., on the n-th planet. None of the costs exceed 106.

Each of the next m lines contains two positive integers ai and bi (1 ≤ ai < bi ≤ n, 1 ≤ i ≤ m) that describe the pair of connected planets. Each pair is given in input only one time.

Output---
Print the smallest amount of money that should be spent on the portals construction on some planets so that there would be the teleportation path between each pair of planets in the galaxy.

*/


public class CPortals {
    static int[][] adjacencyMatrix;
    static int[] minComponentCost;
    static int[] newPortalCost;
    static boolean[] verticesVisited;
    static int verticeCount;
    static int edgeCount;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        verticeCount = in.nextInt();
        edgeCount = in.nextInt();

        int toVertex;
        int fromVertex;

        adjacencyMatrix = new int[verticeCount + 1][verticeCount + 1];
        newPortalCost = new int[verticeCount + 1];
        minComponentCost = new int[verticeCount];
        verticesVisited = new boolean[verticeCount + 1];
        Arrays.fill(minComponentCost, Integer.MAX_VALUE);

        for (int i = 1; i < verticeCount + 1; i++) {
            newPortalCost[i] = in.nextInt();
        }

        // Build the adjacencyMatrix
        for (int i = 0; i < edgeCount; i++) {
            toVertex = in.nextInt();
            fromVertex = in.nextInt();

            adjacencyMatrix[toVertex][fromVertex] = newPortalCost[toVertex];
            adjacencyMatrix[fromVertex][toVertex] = newPortalCost[fromVertex];
        }

        int componentCount = 0;
        for (int i = 1; i < verticeCount + 1; i++) {
            if (!verticesVisited[i]) {
                depthFirstSearch(i, componentCount);
                componentCount++;
                // System.out.println(Arrays.toString(verticesVisited));
            }
        }

        // System.out.println(Arrays.toString(minComponentCost));
        // System.out.println(componentCount);
        int minOfMinComponentCost = Integer.MAX_VALUE;
        int sumOfMinComponentCost = 0;
        for (int i = 0; i < componentCount; i++) {
            if (minOfMinComponentCost > minComponentCost[i]) {
                minOfMinComponentCost = minComponentCost[i];
            }
            sumOfMinComponentCost += minComponentCost[i];
        }

        System.out.println(minOfMinComponentCost * (componentCount - 2) + sumOfMinComponentCost);
    }

    public static void depthFirstSearch(int start, int componentNumber) {

        verticesVisited[start] = true;
        if (minComponentCost[componentNumber] > newPortalCost[start]) {
            minComponentCost[componentNumber] = newPortalCost[start];
        }

        for (int i = 1; i < verticeCount + 1; i++) {
            if (!verticesVisited[i] && adjacencyMatrix[start][i] > 0) {
                depthFirstSearch(i, componentNumber);
            }
        }
    }
}
