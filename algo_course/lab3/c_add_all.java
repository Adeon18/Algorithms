import java.util.Scanner;
import java.util.Arrays;

public class c_add_all {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        Integer numArray[] = new Integer[length];


        for (int i = 0; i < length; i++) {
            numArray[i] = in.nextInt();
        }
        Arrays.sort(numArray);
        
        int smallest_sum = numArray[0] + numArray[1];
        int i = 0;
        for (i = 2; i < length - 1; i++) {
            if (smallest_sum < numArray[i+1]) {
                smallest_sum = smallest_sum + (smallest_sum + numArray[i]);
            } else {
                smallest_sum = smallest_sum + (numArray[i] + numArray[i+1]);
                i++;
            }
        }
        if (i < length) {
            smallest_sum = smallest_sum + (smallest_sum + numArray[i]);
        }
        System.out.println(smallest_sum);
    }
}
