//Assignment 15
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToe5App extends Application {
    private static final int SIZE = 5;                // board is 5×5
    private final Button[][] buttons = new Button[SIZE][SIZE];
    private final char[][] board       = new char[SIZE][SIZE];
    private char currentPlayer = 'X';
    private final Label statusLabel = new Label("Turn: X");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("5×5 Tic‑Tac‑Toe");

        // build grid of buttons
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Button btn = new Button();
                btn.setPrefSize(60, 60);
                final int row = r, col = c;
                btn.setOnAction(e -> handleMove(row, col));
                buttons[r][c] = btn;
                grid.add(btn, c, r);
            }
        }

        // reset button
        Button reset = new Button("Reset");
        reset.setOnAction(e -> resetGame());

        // layout
        VBox root = new VBox(10, statusLabel, grid, reset);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void handleMove(int row, int col) {
        if (board[row][col] == '\0' && statusLabel.getText().startsWith("Turn")) {
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkWin(row, col)) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                disableAll();
            } else if (isBoardFull()) {
                statusLabel.setText("Draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Turn: " + currentPlayer);
            }
        }
    }

    // check all 4 directions for a run of SIZE
    private boolean checkWin(int row, int col) {
        return checkDir(row, col, 1, 0)    // vertical
            || checkDir(row, col, 0, 1)    // horizontal
            || checkDir(row, col, 1, 1)    // diag down‑right
            || checkDir(row, col, 1, -1);  // diag down‑left
    }

    private boolean checkDir(int row, int col, int dr, int dc) {
        int count = 1 + countLine(row, col, dr, dc) + countLine(row, col, -dr, -dc);
        return count >= SIZE;
    }

    private int countLine(int r, int c, int dr, int dc) {
        int cnt = 0;
        for (int nr = r + dr, nc = c + dc;
             nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && board[nr][nc] == currentPlayer;
             nr += dr, nc += dc) {
            cnt++;
        }
        return cnt;
    }

    private boolean isBoardFull() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (board[r][c] == '\0') return false;
        return true;
    }

    private void disableAll() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                buttons[r][c].setDisable(true);
    }

    private void resetGame() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = '\0';
                buttons[r][c].setText("");
                buttons[r][c].setDisable(false);
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Turn: X");
    }

    public static void main(String[] args) {
        launch(args);
    }
}