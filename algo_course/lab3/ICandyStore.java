import java.util.Arrays;
import java.util.Scanner;


public class ICandyStore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Main Loop
        while (true) {
            int candyNum = in.nextInt();
            int totalMoney = (int)(in.nextDouble() * 100);
            // the ONLY Break Case
            if (candyNum == 0) {
                break;
            }

            int calories[] = new int[candyNum];
            int cost[] = new int[candyNum];
            // Input management
            for (int i = 0; i < candyNum; i++) {
                calories[i] = in.nextInt();
                cost[i] = (int)(in.nextDouble() * 100);
            }
            int caloriesForPrices[] = new int[totalMoney + 1];

            for (int i = 0; i < candyNum; i++) {
                for (int j = cost[i]; j < totalMoney + 1; j++) {
                    int caloriesForPrice = caloriesForPrices[j - cost[i]] + calories[i];
                    if (caloriesForPrice > caloriesForPrices[j]) {
                        caloriesForPrices[j] = caloriesForPrice;
                    }
                }
            }   
            System.out.println(caloriesForPrices[totalMoney]);
        }

    }
}
