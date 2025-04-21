import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the title for the window
        primaryStage.setTitle("Habit Hero");

        // Create an instance of GUIManager to set up the GUI
        GUIManager gui = new GUIManager();
        gui.setupUI(primaryStage); // Method to set up the UI components

        // Create a scene and set the primary stage
        Scene scene = new Scene(gui.getLayout(), 800, 600);
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}
