import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class g_platforms {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int platfNum = in.nextInt();
        int platfHeights[] = new int[platfNum];
        long wastedEnergy[] = new long[platfNum];
        // An array that stores info: Ypu come to platform j from arr[j]
        int whereFrom[] = new int[platfNum];
        whereFrom[0] = Integer.MIN_VALUE;
        // To store path(synamic because path cam be smaller)
        List<Integer> path = new ArrayList<Integer>();

        for (int i = 0; i < platfNum; i++) {
            platfHeights[i] = in.nextInt();
        }
        wastedEnergy[0] = 0;
        wastedEnergy[1] = Math.abs(platfHeights[1] - platfHeights[0]);
        
        long megaJump, normalJump;
        for (int i = 2; i < platfNum; i++) {
            megaJump = wastedEnergy[i-2] + 3 * Math.abs(platfHeights[i] - platfHeights[i-2]);
            normalJump = wastedEnergy[i-1] + Math.abs(platfHeights[i] - platfHeights[i-1]);

            if (megaJump < normalJump) {
                wastedEnergy[i] = megaJump;
                whereFrom[i] = i - 2;
            } else {
                wastedEnergy[i] = normalJump;
                whereFrom[i] = i - 1;
            }
        }
        // We start getting the path len and path itself, start by default from last elem.
        int fromIdx = platfNum - 1;
        path.add(fromIdx + 1);
        int pathPlatfAmount = 1;
        while (whereFrom[fromIdx] != Integer.MIN_VALUE) {
            fromIdx = whereFrom[fromIdx];
            pathPlatfAmount++;
            path.add(fromIdx + 1);
        }
        // Results
        System.out.println(wastedEnergy[platfNum - 1]);
        System.out.println(pathPlatfAmount);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }
}
