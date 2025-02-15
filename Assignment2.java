import java.util.Scanner;

public class Assignment2 {
    public static void main(String[] args) {
        float grade;
        char letter = 'A';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input a grade between 0 and 100");
        grade = scanner.nextFloat();

        // Validate input to ensure it is within range
        while (grade < 0 || grade > 100) {
            System.out.println("Grade must be between 0 and 100. Please input again");
            grade = scanner.nextFloat();
        }

        // Convert numeric grade to letter grade
        if (grade >= 90) {
            letter = 'A';
        } else if (grade >= 80) {
            letter = 'B';
        } else if (grade >= 70) {
            letter = 'C';
        } else if (grade >= 60) {
            letter = 'D';
        } else {
            letter = 'F';
        }

        // Output the grade and letter grade
        System.out.println("Grade = " + grade);
        System.out.println("Letter = " + letter);

        // Close scanner
        scanner.close();
    }
}
    