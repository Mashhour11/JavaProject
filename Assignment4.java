import java.util.Scanner;

public class Assignment4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {  // ✅ Auto-closes scanner
            System.out.println("\nPlease input a string to reverse");
            String userInput = scanner.nextLine();
            String output = "";

            for (int i = 1; i <= userInput.length(); i++) {
                output = output + userInput.charAt(userInput.length() - i);
            }

            System.out.println(output);
        }  // ✅ Scanner is automatically closed here
    }
}

