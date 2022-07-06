import java.util.Scanner;


public class BPowerStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expString;
        int largestN = 0;
        boolean failureFlag = false;
        while (true) {
            largestN = 1;
            if (!in.hasNextLine()) {
                break;
            }
            String inpuString = in.nextLine();

            for (int i = 0; i < inpuString.length() / 2; i++) {
                expString = inpuString.substring(0, i+1);
                failureFlag = false;
                for (int j = 0; j < inpuString.length() - expString.length() + 1; j += expString.length()) {
                    if (inpuString.substring(j, j + expString.length()).equals(expString)) {
                        failureFlag = false;
                    } else {
                        failureFlag = true;
                    }
                    if (!failureFlag && j >= inpuString.length() - expString.length() && (inpuString.length() / expString.length()) > largestN) {
                        largestN = inpuString.length() / expString.length();
                        break;
                    }
                }
            }
            System.out.println(largestN);
        }
    }
}