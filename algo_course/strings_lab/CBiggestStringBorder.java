import java.util.Arrays;
import java.util.Scanner;


public class CBiggestStringBorder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inpuString = in.nextLine();
        int larr[] = new int[inpuString.length()];
        int lft = 0;
        int rgt = 0;
        for (int i = 1; i < inpuString.length(); i++) {
            if (i < rgt) {
                larr[i] = Math.min(rgt - i + 1, larr[i - lft]);
            }
            while (i + larr[i] < inpuString.length() && inpuString.charAt(larr[i]) == inpuString.charAt(i + larr[i])) {
                larr[i]++;
            }
            if (i + larr[i] - 1 > rgt) {
                lft = i;
                rgt = i + larr[i] - 1;
            }
        }
        System.out.println(Arrays.stream(larr).max().getAsInt());
    }
}