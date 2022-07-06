import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class GMath {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int taskCount = in.nextInt();

        int numArray[] = new int[taskCount];

        for (int i = 0; i < taskCount; i++) {
            numArray[i] = in.nextInt();
        }

        Arrays.sort(numArray);
        System.out.println(Arrays.toString(numArray));

        int start = 1;

        int minNumCount = 0;

        for (int i = 0; i < taskCount; i++) {
            if (numArray[i] == i + 1) {
                minNumCount++;
                start++;
            } else {
                break;
            }
        }
        System.out.println(minNumCount);
    }
}
