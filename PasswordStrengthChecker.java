
import java.util.Scanner;

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a password to check: ");
        String password = sc.nextLine();

        int score = 0;

        if (password.length() >= 8) {
            score++;
        }
        if (password.matches(".*[A-Z].*")) {
            score++;
        }
        if (password.matches(".*[a-z].*")) {
            score++;
        }
        if (password.matches(".*[0-9].*")) {
            score++;
        }
        if (password.matches(".*[^a-zA-Z0-9].*")) {
            score++;
        }

        System.out.println("\nPassword length: " + password.length());

        if (score <= 2) {
            System.out.println("Strength: WEAK");
        } else if (score <= 4) {
            System.out.println("Strength: MEDIUM");
        } else {
            System.out.println("Strength: STRONG");
        }

        System.out.println("Criteria passed: " + score + "/5");

        sc.close();
    }
}
