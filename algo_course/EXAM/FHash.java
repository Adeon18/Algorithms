import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FHash {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int operationCount = in.nextInt();
        int data[][] = new int[operationCount][2];
        // System.out.println(Math.pow(2, 30));
        List<Integer> hashTable = new ArrayList<Integer>();

        for (int i = 0; i < operationCount; i++) {
            data[i][0] = in.nextInt();
            data[i][1] = in.nextInt();

        }

        for (int i = 0; i < operationCount; i++) {
            if (data[i][0] == 1) {
                int h = data[i][0];

                while (!hashTable.contains(h % (int)Math.pow(2, 30))) {
                    h++;
                    hashTable.add(data[i][0]);
                } 
            } else if (data[i][0] == 2) {
                if (hashTable.contains(data[i][0] % (int)Math.pow(2, 30))) {
                    System.out.println(x);
                }
            }
        }
    }
}
