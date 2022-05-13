import java.util.Scanner;

public class AMathProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberCount = in.nextInt();
        boolean isPowerNumber = false;
        int resultNumbers = 0;

        for (int i = 2; i < numberCount + 1; i++) {
            for (int j = 2; j <= i / 2; j++) {
                int currentNumber = j;
                while (true) {
                    // System.out.println(currentNumber);
                    // System.out.println();
                    currentNumber *= j;
                    if (currentNumber > i) {
                        isPowerNumber = false;
                        break;
                    }
                    if (currentNumber == i) {
                        // System.out.println(currentNumber);
                        // System.out.println("AAAA");
                        isPowerNumber = true;
                        break;
                    }
                }

                if (isPowerNumber) {
                    break;
                }
                //System.out.println("AAAA");
            }
            //System.out.println("BBB");
            if (!isPowerNumber) {
               // System.out.println(i);
                resultNumbers++;
                isPowerNumber = false;
            }
        }

        System.out.println(resultNumbers + 1);
    }
}
