import java.util.Scanner;
import java.util.Arrays;

public class a_planting_trees {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int minTime = 0;
        Integer treeArray[] = new Integer[length];


        for (int i = 0; i < length; i++) {
            treeArray[i] = in.nextInt();
        }
        Arrays.sort(treeArray);
        
        int day = 1;
        for (int i = length - 1; i >= 0; i--) {
            // System.out.println(day);
            if (day + treeArray[i] > minTime) {
                minTime = day + treeArray[i];
            }
            day++;
        }
        // System.out.println(Arrays.toString(treeArray));
        System.out.println(minTime + 1);
    }
}
