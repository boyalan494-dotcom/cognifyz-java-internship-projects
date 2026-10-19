
import java.security.SecureRandom;
import java.util.Scanner;

public class RandomPasswordGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "0123456789" +
                "!@#$%&*";

        System.out.println("=== Random Password Generator ===");
        System.out.print("Enter password length (8-64): ");

        int length = sc.nextInt();

        if (length < 8 || length > 64) {
            System.out.println("Please choose a length between 8 and 64.");
            sc.close();
            return;
        }

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        System.out.println("Generated password: " + password);
        sc.close();
    }
}
