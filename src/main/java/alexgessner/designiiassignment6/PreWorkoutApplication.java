package alexgessner.designiiassignment6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PreWorkoutApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("PreWorkoutView"), 600,300);
        stage.setTitle("Pre Workout Catalog");
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(PreWorkoutApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}