import java.io.*;
import java.util.*;

public class MatrixMultiplication {
    public static void main(String[] args) {
        switch (args.length) {
            case 2 -> processFiles(args[0], args[1]);
            case 1 -> {
                try {
                    int n = Integer.parseInt(args[0]);
                    generateAndMultiplyMatrices(n);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please provide either two file names or an integer.");
                }
            }
            default -> System.out.println("Usage: java MatrixMultiplication <file1> <file2> OR java MatrixMultiplication <integer>");
        }
    }

    private static void processFiles(String file1, String file2) {
        try {
            int[][] matrix1 = readMatrixFromFile(file1);
            int[][] matrix2 = readMatrixFromFile(file2);

            if (matrix1[0].length != matrix2.length) {
                System.out.println("Matrix dimensions do not match for multiplication.");
                return;
            }

            int[][] result = multiplyMatrices(matrix1, matrix2);
            saveMatrixToFile(result, "matrix3.txt");
            System.out.println("Matrix multiplication successful. Result saved in matrix3.txt");

        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    private static void generateAndMultiplyMatrices(int n) {
        try {
            int[][] matrix1 = generateRandomMatrix(n, n);
            int[][] matrix2 = generateRandomMatrix(n, n);

            saveMatrixToFile(matrix1, "matrix1.txt");
            saveMatrixToFile(matrix2, "matrix2.txt");

            int[][] result = multiplyMatrices(matrix1, matrix2);
            saveMatrixToFile(result, "matrix3.txt");

            System.out.println("Random matrices generated and saved to files. Result saved in matrix3.txt");

        } catch (IOException e) {
            System.out.println("Error writing to files: " + e.getMessage());
        }
    }

    private static int[][] readMatrixFromFile(String filename) throws IOException {
        List<int[]> matrixList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                int[] row = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
                matrixList.add(row);
            }
        }
        return matrixList.toArray(int[][]::new); // ✅ Optimized version (Fixes the last warning)
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix2[0].length;
        int commonDim = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < commonDim; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    private static int[][] generateRandomMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(10); // Random numbers between 0-9
            }
        }
        return matrix;
    }

    private static void saveMatrixToFile(int[][] matrix, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int[] row : matrix) {
                for (int value : row) {
                    writer.write(value + " ");
                }
                writer.newLine();
            }
        }
    }
}