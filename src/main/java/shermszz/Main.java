package shermszz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shermszz.ui.MainWindow;

/**
 * A GUI for Shermszz using FXML.
 */
public class Main extends Application {

    private Shermszz shermszz = new Shermszz("data/shermszz.txt"); // Initialize your logic here

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the logic instance into the controller
            fxmlLoader.<MainWindow>getController().setShermszz(shermszz);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
