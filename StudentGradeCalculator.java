
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Student Grade Calculator ===");
        System.out.print("Enter number of subjects: ");

        int subjects = sc.nextInt();

        if (subjects <= 0) {
            System.out.println("Please enter at least one subject.");
            sc.close();
            return;
        }

        double total = 0;

        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            double marks = sc.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Enter marks from 0 to 100.");
                sc.close();
                return;
            }

            total += marks;
        }

        double average = total / subjects;

        System.out.println("Total marks: " + total);
        System.out.printf("Average: %.2f%%%n", average);

        if (average >= 90) {
            System.out.println("Grade: A");
        } else if (average >= 80) {
            System.out.println("Grade: B");
        } else if (average >= 70) {
            System.out.println("Grade: C");
        } else if (average >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }

        sc.close();
    }
}
