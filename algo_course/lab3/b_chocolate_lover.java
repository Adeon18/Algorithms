import java.util.Scanner;

public class b_chocolate_lover {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long amount = in.nextLong();
        long mass_a = in.nextLong();
        long mass_b = in.nextLong();
        

        if (amount % 2 == 0) {
            System.out.println((amount / 2) * (mass_a + mass_b));
        } else {
            if (mass_a > mass_b) {
                System.out.println((amount / 2) * mass_b + ((amount + 1) / 2) * mass_a);
            } else {
                System.out.println((amount / 2) * mass_a + ((amount + 1) / 2) * mass_b);
            }
        }

    }
}
