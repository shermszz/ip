package spongebob;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spongebob.ui.MainWindow;

/**
 * A GUI for Spongebob using FXML.
 */
public class Main extends Application {

    private Spongebob spongebob = new Spongebob("data/spongebob.txt"); // Initialize your logic here

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the logic instance into the controller
            fxmlLoader.<MainWindow>getController().setSpongebob(spongebob);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
