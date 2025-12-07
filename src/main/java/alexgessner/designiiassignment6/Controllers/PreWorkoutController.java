package alexgessner.designiiassignment6.Controllers;

import alexgessner.designiiassignment6.AppState;
import alexgessner.designiiassignment6.Model.PreWorkout;
import alexgessner.designiiassignment6.Model.PreWorkoutDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the main scene.
 */

public class PreWorkoutController implements Initializable{

    @FXML private TextField searchText;
    @FXML private Button newButton;
    @FXML private Button editButton;
    @FXML private Button searchButton;
    @FXML private ListView listViewPreWorkout;

    /**
     * Sets up the initial items in the database.
     * @param url - the url.
     * @param resourceBundle - the resourceBundle.
     */

    @FXML //Initializes the Listview to all items in the database
    public void initialize(URL url, ResourceBundle resourceBundle){
        listViewPreWorkout.setItems(AppState.preWorkoutList);
    }

    /**
     * Method for searching a preWorkout.
     * @param e - event of Clicking the Search Button.
     */
    @FXML //Logic for searching and updating the ListView
    private void searchPreWorkout(ActionEvent e){
        String nameSearch = searchText.getText();
        ObservableList<PreWorkout> searchedPreWorkouts;
        if(nameSearch.isEmpty()) {
            searchedPreWorkouts = PreWorkoutDAO.getPreWorkout();
            AppState.preWorkoutList = searchedPreWorkouts;
            listViewPreWorkout.setItems(AppState.preWorkoutList);
        }
        else {
            searchedPreWorkouts = PreWorkoutDAO.searchPreWorkouts(nameSearch);
            AppState.preWorkoutList = searchedPreWorkouts;
            listViewPreWorkout.setItems(AppState.preWorkoutList);
        }


    }

    /**
     * Deletes the Selected PreWorkout.
     * @param e - Event of clicking the delete Button.
     */
    @FXML private void deleteSelection(ActionEvent e){
        PreWorkout selectedPre = (PreWorkout) listViewPreWorkout.getSelectionModel().getSelectedItem();
        AppState.preWorkoutList.remove(selectedPre);
        PreWorkoutDAO.removePreWorkout(selectedPre);
    }

    /**
     * Method for refreshing the List of PreWorkouts to all in the database.
     * @param e - clicking refresh button.
     */
    @FXML //Refreshes ListView to all in database
    private void refreshList(ActionEvent e){
        ObservableList<PreWorkout> preWorkouts = PreWorkoutDAO.getPreWorkout();
        AppState.preWorkoutList = preWorkouts;
        listViewPreWorkout.setItems(AppState.preWorkoutList);
    }

    /**
     * Switching to the create scene.
     * @param e - clicking New Button
     * @throws IOException - throws exception.
     */
    @FXML
    private void switchSceneCreate(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        AppState.switchToCreateScene(stage);
    }

    /**
     * Switching to the edit scene.
     * @param e - clicking the edit Button.
     * @throws IOException - throws Exception.
     */
    @FXML
    private void switchSceneEdit(ActionEvent e) throws IOException {
        PreWorkout selectedPre = (PreWorkout) listViewPreWorkout.getSelectionModel().getSelectedItem();
        AppState.preWorkoutList = FXCollections.observableArrayList(selectedPre);
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        AppState.switchToEditScene(stage);
    }
}
