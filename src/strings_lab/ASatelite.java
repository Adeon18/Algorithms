import java.util.Scanner;


public class ASatelite {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String transBegin = in.nextLine();
        String transEnd = in.nextLine();
        String overlap;
        int maxOverlapLength = 0;

        for (int i = 0; i < transBegin.length(); i++) {
            overlap = transBegin.substring(i);
            if (overlap.equals(transEnd.subSequence(0, overlap.length()))) {
                maxOverlapLength = overlap.length();
                break;
            }
        }
        System.out.println(maxOverlapLength);
    }
}