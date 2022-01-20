import java.util.Scanner;


public class DMaximumPalindrome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        String result = "";
        
        for (int i = 0; i < inputString.length(); i++) {
            for (int j = 0; j < inputString.length() - i; j++) {
                // System.out.println("0:" + inputString.substring(j, j + i));
                // System.out.println("1:" + inputString.substring(j, j + (int)Math.floor((float)i / (float)2) + 1));
                // System.out.println("2:" + inputString.substring(j, j + i));
                if (i % 2 == 0) {
                    // System.out.println("NEW" + i);// new StringBuilder(inputString.substring(j + (int)Math.floor((float)i / (float)2) + 1, j + i)).reverse().toString()
                    // System.out.println("0:" + inputString.substring(j, j + i / 2));
                    // System.out.println("1:" + inputString.substring(j + i / 2, j + i));
                    if ((inputString.substring(j, j + i / 2).equals(new StringBuilder(inputString.substring(j + i / 2, j + i)).reverse().toString()) && result.length() < i)) {
                        result = inputString.substring(j, j + i);
                    }
                } else {
                    // System.out.println("NEW" + i);// new StringBuilder(inputString.substring(j + i / 2, j + i)).reverse().toString()
                    // System.out.println("0:" + inputString.substring(j, j + (int)Math.floor((float)i / (float)2)));
                    // System.out.println("1:" + inputString.substring(j + (int)Math.floor((float)i / (float)2) + 1, j + i));
                    if ((inputString.substring(j, j + (int)Math.floor((float)i / (float)2)).equals(new StringBuilder(inputString.substring(j + (int)Math.floor((float)i / (float)2) + 1, j + i)).reverse().toString()) && result.length() < i)) {
                        // System.out.println("OVERWRITE" + inputString.substring(j, j + i));
                        result = inputString.substring(j, j + i);
                    }
                }
            }
        }

        System.out.println(result);
    }
}