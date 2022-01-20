import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class JRailwayTickets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lengthArr[] = new int[3];
        int priceArr[] = new int[3];
        
        for (int i = 0; i < 3; i++) {
            lengthArr[i] = in.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            priceArr[i] = in.nextInt();
        }

        int numOfStations = in.nextInt();

        // Get the start and end positions
        int start = in.nextInt(); int end = in.nextInt();
        if (start > end) {
            int holder = start;
            start = end;
            end = holder;
        }
        // Fill in the distances list
        List<Integer> distList = new ArrayList<Integer>();
        distList.add(0);
        for (int i = 0; i < numOfStations - 1; i++) {
            distList.add(in.nextInt());
        }
        // A fixed size array to store all the paths(Initialised as infinity)
        int storedPricesData[] = new int[numOfStations];
        Arrays.fill(storedPricesData, Integer.MAX_VALUE);
        storedPricesData[start - 1] = 0;

        for (int i = start; i < end; i++) {
            for (int j = start - 1; j < i; j++) {
                int diff = distList.get(i) - distList.get(j);
                if (diff <= lengthArr[2]) {
                    int price = 0;
                    // Choose the price
                    if (diff <= lengthArr[0]) {
                        price = priceArr[0];
                    }  else if (diff <= lengthArr[1]) {
                        price = priceArr[1];
                    } else if (diff <= lengthArr[2]) {
                        price = priceArr[2];
                    }
                    // If we found a more optimal price, write it to our array
                    if (storedPricesData[j] + price < storedPricesData[i]){
                        storedPricesData[i] = storedPricesData[j] + price;
                    }
                }
            }
        }

        System.out.println(storedPricesData[end - 1]);
    }
}
