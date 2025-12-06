package alexgessner.designiiassignment6.Controllers;

import alexgessner.designiiassignment6.AppState;
import alexgessner.designiiassignment6.Model.PreWorkout;
import alexgessner.designiiassignment6.Model.PreWorkoutDAO;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class NewPreController {

    @FXML private TextField nameText;
    @FXML private TextField caffeineText;
    @FXML private TextField lCitrullineText;
    @FXML private TextField betaAlanineText;
    @FXML private Button saveButton;
    @FXML private Button exitButton;
    @FXML private ProgressBar saveProgress;

    @FXML private void saveEntry(ActionEvent e) throws IOException {
        String preName = nameText.getText();
        int caffeineAmount = Integer.parseInt(caffeineText.getText());
        int lCitrullineAmount = Integer.parseInt(lCitrullineText.getText());
        int betaAlanineAmount = Integer.parseInt(betaAlanineText.getText());
        int lastUsedID = PreWorkoutDAO.getLastID() + 1;

        //generates a new record of the PreWorkout
        PreWorkout savedPreWorkout = new PreWorkout(lastUsedID, preName, caffeineAmount, lCitrullineAmount, betaAlanineAmount);

        PreWorkoutDAO.addPreWorkout(savedPreWorkout);

        saveProgress.setProgress(0);
        //progress bar timeline for animation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(saveProgress.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(saveProgress.progressProperty(), 1))
        );

        timeline.setOnFinished(ev -> {
            Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
            try {
                AppState.switchToMainScene(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        timeline.play();
    }

    @FXML private void exitToMain(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        AppState.switchToMainScene(stage);
    }




}
