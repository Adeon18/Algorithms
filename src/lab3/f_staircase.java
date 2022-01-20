import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class f_staircase {
    private static int length;
    private static List<Integer> stairNums;
    private static List<Integer> stairCost;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        length = in.nextInt();
        stairNums = new ArrayList<Integer>();
        stairCost = new ArrayList<Integer>();
        
        // Before and after add 0
        stairNums.add(0);
        for (int i = 0; i < length; i++) {
            stairNums.add(in.nextInt());
        }
        stairNums.add(0);

        int k = in.nextInt();

        for (int i = 0; i < length + 2; i++) {
            stairCost.add(0);
        }

        System.out.println(findIdx(0, k));

    }

    public static int findIdx(int idx, int maxStep){

        if (idx >= length+2) {
            return 0;
        }

        if (stairCost.get(idx) != 0) {
            return stairCost.get(idx);
        }

        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= maxStep; i++) {
            result = Math.max(result, findIdx(idx + i, maxStep));
        }

        stairCost.set(idx, stairNums.get(idx) + result);
        return stairCost.get(idx);
    } 
}