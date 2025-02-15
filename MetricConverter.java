// Version: Assignment 3
import java.util.Scanner;

public class MetricConverter {
public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
        System.out.println("Welcome to metric converter!");
        System.out.println("\nPlease input your query. For example, 1 km = m.");
        System.out.println("Enter 'exit' or '-1' to exit the program.");
        
        while (true) {
            System.out.print("\nEnter conversion (e.g., '1 km = mile'): ");
            String userInput = scanner.nextLine().trim().toLowerCase();
            
            if (userInput.equals("exit") || userInput.equals("-1")) {
                System.out.println("Goodbye!");
                break;
            }
            
            String[] parts = userInput.split(" ");
            if (parts.length != 4 || !parts[2].equals("=")) {
                System.out.println("Invalid input format. Please enter a query like '1 km = mile'.");
                continue;
            }
            
            try {
                double value = Double.parseDouble(parts[0]);
                String fromUnit = parts[1];
                String toUnit = parts[3];
                
                double result = convert(value, fromUnit, toUnit);
                if (result == -1) {
                    System.out.println("Your input is not currently handled by this app. Try something like '1 kg = lb'.");
                } else {
                    System.out.printf("%.4f %s = %.4f %s\n", value, fromUnit, result, toUnit);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric value. Please enter a valid number.");
            }
        }   }
}

public static double convert(double value, String fromUnit, String toUnit) {
    return switch (fromUnit) {
        case "kg" -> toUnit.equals("lb") ? value * 2.20462 : -1;
        case "g" -> toUnit.equals("oz") ? value * 0.035274 : -1;
        case "km" -> toUnit.equals("mile") ? value * 0.621371 : -1;
        case "mm" -> toUnit.equals("inch") ? value * 0.0393701 : -1;
        default -> -1;
    };
}}