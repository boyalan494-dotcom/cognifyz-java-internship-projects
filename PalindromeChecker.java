
import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Palindrome Checker ===");
        System.out.print("Enter a word or phrase: ");

        String input = sc.nextLine();

        // Remove spaces and punctuation, and ignore case
        String cleaned = input.replaceAll("[^a-zA-Z0-9]", "")
                              .toLowerCase();

        String reversed = new StringBuilder(cleaned)
                              .reverse()
                              .toString();

        if (cleaned.isEmpty()) {
            System.out.println("Please enter some letters or numbers.");
        } else if (cleaned.equals(reversed)) {
            System.out.println("It is a palindrome!");
        } else {
            System.out.println("It is not a palindrome.");
        }

        sc.close();
    }
}
