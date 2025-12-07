package alexgessner.designiiassignment6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application to run the PreWorkoutCatalog
 */

public class PreWorkoutApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("PreWorkoutView"), 600,300);
        stage.setTitle("Pre Workout Catalog");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads an FXML file
     * @param fxml - the file name
     * @return - returns a parent to the Scene
     * @throws IOException - just throws the exception
     */
    public static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(PreWorkoutApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}