package alexgessner.designiiassignment6;

import alexgessner.designiiassignment6.*;
import alexgessner.designiiassignment6.Model.PreWorkout;
import alexgessner.designiiassignment6.Model.PreWorkoutDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppState {
    public static ObservableList<PreWorkout> preWorkoutList = FXCollections.observableArrayList(PreWorkoutDAO.getPreWorkout());

    public static void switchToCreateScene(Stage stage) throws IOException {
            Scene scene = new Scene(PreWorkoutApplication.loadFXML("NewPreView"), 600, 400);
            stage.setTitle("Create New Pre Workout");
            stage.setScene(scene);
            stage.show();
    }

    public static void switchToEditScene(Stage stage) throws IOException {
        Scene scene = new Scene(PreWorkoutApplication.loadFXML("EditView"), 640, 300);
        stage.setTitle("Edit a Pre Workout");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToMainScene(Stage stage) throws IOException{
        Scene scene = new Scene(PreWorkoutApplication.loadFXML("PreWorkoutView"), 640, 300);
        stage.setTitle("PreWorkout Catalog");
        stage.setScene(scene);
        stage.show();
    }

}
