import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ETheGreatRevegetation {

    static ArrayList<Edge>[] adjacencyList;
    static int[] grassTypeArray;
    static boolean impossibleFlag = false;

    // A class that stores to node we are pointing to and from, and if the connection cow only adts the same type of grass
    public static class Edge {
        private int fromNode;
        private int toNode;
        private boolean isSame;

        public Edge(int fromNode, int toNode, boolean isSame) {
            this.fromNode = fromNode;
            this.toNode = toNode;
            this.isSame = isSame;
        }

        public String toString() {
            return "Edge(" + fromNode + ", " + toNode + ", " + isSame + ")";
        }

        public int getFromNode(){
            return fromNode;
        }

        public int getToNode() {
            return toNode;
        }

        public boolean getIsSame() {
            return isSame;
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
    
        int pastureCount = in.nextInt();
        int cowCount = in.nextInt();
        int groupAmount = 0;

        int[][] favPasturesArray = new int[cowCount+1][2];
        String cowDiets[] = new String[cowCount+1];
        adjacencyList = new ArrayList[pastureCount+1];

        for (int i = 0; i < pastureCount + 1; i++) {
            adjacencyList[i] = new ArrayList<Edge>();
        }
        grassTypeArray = new int[pastureCount];


        for (int i = 1; i < cowCount + 1; i++) {
            cowDiets[i] = in.next();
            favPasturesArray[i][0] = in.nextInt() - 1;
            favPasturesArray[i][1] = in.nextInt() - 1;

            if (cowDiets[i].equals("D")) {
                adjacencyList[favPasturesArray[i][0]].add(new Edge(favPasturesArray[i][0], favPasturesArray[i][1], false));
                adjacencyList[favPasturesArray[i][1]].add(new Edge(favPasturesArray[i][1], favPasturesArray[i][0], false));
            } else if (cowDiets[i].equals("S")) {
                adjacencyList[favPasturesArray[i][0]].add(new Edge(favPasturesArray[i][0], favPasturesArray[i][1], true));
                adjacencyList[favPasturesArray[i][1]].add(new Edge(favPasturesArray[i][0], favPasturesArray[i][0], true));
            } else {
                System.out.println("WHAT");
            }
        }

        for (int i = 0; i < pastureCount; i++) {
            if (impossibleFlag) {
                break;
            }

            if (grassTypeArray[i] == 0) {
                grassTypeArray[i] = 2 * groupAmount + 1;
                floodFillAlgorithm(i);
                groupAmount += 1;
            }
        }
        if (!impossibleFlag) {
            String result = "1";
            for (int i = 0; i < groupAmount; i++) {
                result += "0";
            }
            System.out.println(result);
        } else {
            System.out.println(0);
        }
    }
    // A special implementation of the floodFill algorithm
    private static void floodFillAlgorithm(int start) {

        for (int i = 0; i < adjacencyList[start].size(); i++) {
            int toNode = adjacencyList[start].get(i).getToNode();
            // System.out.println(impossibleFlag);
            // System.out.println(Arrays.toString(adjacencyList[start].toArray()));
            if (grassTypeArray[start] % 2 == 0) {
                // If cow eats the same grass => the 2 neighboor fiels should be of same grass type.
                if (adjacencyList[start].get(i).getIsSame()) {
                    if (grassTypeArray[toNode] == 0) {
                        grassTypeArray[toNode] = grassTypeArray[start];
                        floodFillAlgorithm(toNode);
                    } else if (grassTypeArray[toNode] != grassTypeArray[start]) {
                        impossibleFlag = true;
                        return;
                    }
                } else {
                    // If the cow does not eat the same grass and % 2 == 0, we decrement 1 from the arr.
                    if (grassTypeArray[toNode] == 0) {
                        grassTypeArray[toNode] = grassTypeArray[start] - 1;
                        floodFillAlgorithm(toNode);
                    } else if (grassTypeArray[toNode] == grassTypeArray[start]) {
                        impossibleFlag = true;
                        return;
                    }
                }
            } else {
                // If cow eats the same grass => the 2 neighboor fiels should be of same grass type.
                if (adjacencyList[start].get(i).getIsSame()) {
                    if (grassTypeArray[toNode] == 0) {
                        grassTypeArray[toNode] = grassTypeArray[start];
                        floodFillAlgorithm(toNode);
                    } else if (grassTypeArray[toNode] != grassTypeArray[start]) {
                        impossibleFlag = true;
                        return;
                    }
                } else {
                     // If the cow does not eat the same grass and % 2 == 1, we add 1 to the arr.
                    if (grassTypeArray[toNode] == 0) {
                        grassTypeArray[toNode] = grassTypeArray[start] + 1;
                        floodFillAlgorithm(toNode);
                    } else if (grassTypeArray[toNode] == grassTypeArray[start]) {
                        impossibleFlag = true;
                        return;
                    }
                }
            }
        }
    }
}