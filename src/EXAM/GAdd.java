import java.util.Arrays;
import java.util.Scanner;

public class GAdd {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberCount = in.nextInt();

        int numbersArray[] = new int[numberCount];
        int resultsArray[] = new int[10];

        for (int i = 0; i < numberCount; i++) {
            numbersArray[i] = in.nextInt();
        }

        for (int j = 0; j < numberCount - 1; j++) {
            int newNumber = (numbersArray[j] + numbersArray[j+1]) % 10;
            numbersArray[j+1] = newNumber;
            for (int z = j; z < numberCount - 1; z++) {
                newNumber = (numbersArray[z] * numbersArray[z+1]) % 10;
                numbersArray[z+1] = newNumber;
            }
            resultsArray[numbersArray[numberCount - 1] + 1]++;
            // System.out.println(Arrays.toString(numbersArray));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(resultsArray[i]);
        }
        
    }
}
