package shermszz.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shermszz.Shermszz;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Shermszz shermszz;

    // Images are located in src/main/resources/images
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image shermszzImage = new Image(this.getClass().getResourceAsStream("/images/DaSpongebob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Shermszz instance */
    public void setShermszz(Shermszz s) {
        shermszz = s;

        // Add the welcome message dialog immediately after injection
        dialogContainer.getChildren().add(
                DialogBox.getShermszzDialog(shermszz.getWelcomeMessage(), shermszzImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Shermszz's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = shermszz.getResponse(input); // We need to implement this in Shermszz class

        // Add both dialog boxes to the container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getShermszzDialog(response, shermszzImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye".trim())) {
            // Create a pause so the user can read the goodbye message before closing
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}
