
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Temperature Converter ===");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Choose an option: ");

        int choice = sc.nextInt();

        System.out.print("Enter temperature: ");
        double temperature = sc.nextDouble();

        if (choice == 1) {
            double fahrenheit = (temperature * 9.0 / 5.0) + 32;
            System.out.printf("Result: %.2f Fahrenheit%n", fahrenheit);
        } else if (choice == 2) {
            double celsius = (temperature - 32) * 5.0 / 9.0;
            System.out.printf("Result: %.2f Celsius%n", celsius);
        } else {
            System.out.println("Invalid option. Choose 1 or 2.");
        }

        sc.close();
    }
}
