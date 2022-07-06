import java.util.Arrays;
import java.util.Scanner;

public class IAttackOfNPus {

    static double EPS = 0.0000000001;

    static Entity[] pirates;
    static Entity[] tentacles;
    static Entity captain;
    static Entity octopus;
    static double[][] distanceMatrix;
    static Edge[] edgesArray;
    static int[] totalHolderArray;
    static boolean[] presenceChecker;
    static int indexHolder[];
    
    static int totalHolder;
    static int tentaclesCount;
    static int piratesCount;
    
    // A representation of a tentacle/ a pirate
    public static class Entity {
        double x;
        double y;
        double speed = 0;

        public Entity(double x, double y, double speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public Entity(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getSpeed() {
            return speed;
        }
    }
    // A class edge for helping manage tentacles and pirates
    public static class Edge {
        int toIndex;
        int nextIndex;

        public Edge(int toIndex, int nextIndex) {
            this.toIndex = toIndex;
            this.nextIndex = nextIndex;
        }

        public Edge() {

        }

        public int getToIndex() {
            return toIndex;
        }

        public void setToIndex(int toIndex) {
            this.toIndex = toIndex;
        }

        public int getNextIndex() {
            return nextIndex;
        }

        public void setNextIndex(int nextIndex) {
            this.nextIndex = nextIndex;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int testCases = in.nextInt();
        
        for (int test = 0; test < testCases; test++) {
            tentaclesCount = in.nextInt();
            piratesCount = in.nextInt();

            pirates = new Entity[piratesCount];
            tentacles = new Entity[tentaclesCount];
            distanceMatrix = new double[piratesCount][tentaclesCount];

            edgesArray = new Edge[piratesCount * tentaclesCount];
            for (int i = 0; i < piratesCount * tentaclesCount; i++) {
                edgesArray[i] = new Edge();
            }
            totalHolderArray = new int[piratesCount];
            indexHolder = new int[piratesCount];

            // Captain and pirated
            captain = new Entity(in.nextDouble(), in.nextDouble(), in.nextDouble());

            for (int i = 0; i < piratesCount; i++) {
                pirates[i] = new Entity(in.nextDouble(), in.nextDouble(), in.nextDouble());
            }

            // Octopus head and tenticles
            octopus = new Entity(in.nextDouble(), in.nextDouble());

            for (int i = 0; i < tentaclesCount; i++) {
                tentacles[i] = new Entity(in.nextDouble(), in.nextDouble());
            }

            double maxPoint = -1;
            double minPoint = 0;
            double midPoint;
            // Get the max time
            for (int i = 0; i < piratesCount; i++) {
                for (int j = 0; j < tentaclesCount; j++) {
                    distanceMatrix[i][j] = getDistance(pirates[i], tentacles[j]) / pirates[i].getSpeed();
                    if (maxPoint < distanceMatrix[i][j]) {
                        maxPoint = distanceMatrix[i][j];
                    }
                }
            }
            // Check if we can make our time smaller
            while (minPoint < maxPoint) {
                midPoint = (maxPoint + minPoint) / 2.0;

                if (isAcceptable(midPoint)) {
                    maxPoint = midPoint - EPS;
                } else  {
                    minPoint = midPoint + EPS;
                }
            }

            System.out.format("%.9f\n", minPoint + getDistance(captain, octopus) / captain.getSpeed());
        }
    }
    // Compute the distance betwee 2 points
    public static double getDistance(Entity first, Entity second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }
    // Check if we can make you max time smaller.
    public static boolean isAcceptable(double time) {
        totalHolder = 1;

        totalHolderArray = new int[piratesCount];
        indexHolder = new int[piratesCount];

        for (int i = 0; i < piratesCount; i++) {
            for (int j = 0; j < tentaclesCount; j++) {
                // For each time that is less that max time
                if (distanceMatrix[i][j] < time) {
                    edgesArray[totalHolder].setToIndex(j);
                    edgesArray[totalHolder].setNextIndex(totalHolderArray[i]);
                    totalHolderArray[i] = totalHolder;
                    totalHolder++;
                }
            }
        }
        // Check if each tentacle in trapped(managed by the pirate)
        int temp = 0;
        for (int i = 0; i < piratesCount; i++) {
            presenceChecker = new boolean[piratesCount];
            if (findIndex(i)) {
                temp++;
            }
        }
        // If the exact amount of tentacles was managed, we succeeded
        if (temp == tentaclesCount) {
            return true;
        }
        return false;
    }
    // Checks for tentacle management
    public static boolean findIndex(int index) {
        int i = totalHolderArray[index];
        while (i != 0) {
            int toIndex = edgesArray[i].getToIndex();
            // System.out.println("Entered Recursion FindIndex 1");
            // System.out.println(i);
            // Check for a respective tentacle being trapped
            if (!presenceChecker[toIndex]) {
                presenceChecker[toIndex] = true;
                // System.out.println("Entered Recursion FindIndex 2");
                if (indexHolder[toIndex] == 0 || findIndex(indexHolder[toIndex])) {
                    indexHolder[toIndex] = index;
                    // System.out.println("Entered Recursion FindIndex 3");
                    return true;
                }
            }
            // Move on to the next pirate
            i++;
            i = edgesArray[i].getNextIndex();
        }
        return false;
    }
}
