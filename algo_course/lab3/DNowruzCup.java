import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.*;
import java.util.Collections;

public class DNowruzCup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int length = 2*n;

        List<Long> contestantsArrOriginal = new ArrayList<Long>();

        for (int i = 0; i < length; i++) {
            contestantsArrOriginal.add(in.nextLong());
        }
        long amanchik = contestantsArrOriginal.remove(0);
        Collections.sort(contestantsArrOriginal);
        List<Long> contestantsArrHighest = new ArrayList<>(contestantsArrOriginal);
        List<Long> contestantsArrLowest = new ArrayList<>(contestantsArrOriginal);
        
        // System.out.println(contestantsArrOriginal.toString());

        int highest = 1;
        int lowest = 1;
        long powerAmanchikHighest = amanchik + contestantsArrHighest.remove(contestantsArrHighest.size() - 1);
        long powerAmanchikLowest = amanchik + contestantsArrLowest.remove(0);
        // System.out.println(powerAmanchikHighest + " " + powerAmanchikLowest);

        for (int i = 1; i < contestantsArrHighest.size() - 2; i++) {
            for (int j = i + 1; j < contestantsArrHighest.size(); j++) {
                if (powerAmanchikHighest < contestantsArrHighest.get(i) + contestantsArrHighest.get(j)) {
                    highest += 1;
                    if (i >= contestantsArrHighest.size()) {
                        i = contestantsArrHighest.size() - 2;
                        break;
                    } else {
                        contestantsArrHighest.remove(i);
                        j--;
                        i--;
                    }
                    if  (j >= contestantsArrHighest.size()) {
                        j = contestantsArrHighest.size() - 1;
                        break;
                    } else {
                        contestantsArrHighest.remove(j);
                        j--;
                        i--;
                    }
                }
            }
        }

        for (int i = 1; i < contestantsArrLowest.size() - 2; i++) {
            for (int j = i + 1; j < contestantsArrLowest.size(); j++) {
                if (powerAmanchikLowest < contestantsArrLowest.get(i) + contestantsArrLowest.get(j)) {
                    lowest += 1;
                    if (i >= contestantsArrLowest.size()) {
                        i = contestantsArrLowest.size() - 2;
                        break;
                    } else {
                        contestantsArrLowest.remove(i);
                        j--;
                        i--;
                    }
                    if  (j >= contestantsArrLowest.size()) {
                        j = contestantsArrLowest.size() - 1;
                        break;
                    } else {
                        contestantsArrLowest.remove(j);
                        j--;
                        i--;
                    }
                }
            }
        }
        
        
        System.out.println(highest + " " + lowest);

    }
}
