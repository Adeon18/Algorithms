import java.util.Scanner;

public class h_route2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int pathLength = in.nextInt();

        int matrix[][] = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; i < size; i++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int idxi = 0; int idxj = 0;
        long biggest_sum = matrix[idxi][idxj];
        for (int i = 1; i < pathLength; i++){

            for (int j = 1; j < size; j++) {
                
            }

        }

    
    }
}
