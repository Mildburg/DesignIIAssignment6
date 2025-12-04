package alexgessner.designiiassignment6.Controllers;
import alexgessner.designiiassignment6.*;

import alexgessner.designiiassignment6.Model.PreWorkout;
import alexgessner.designiiassignment6.Model.PreWorkoutDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PreWorkoutController implements Initializable{

    @FXML private TextField searchText;
    @FXML private Button newButton;
    @FXML private Button editButton;
    @FXML private Button searchButton;
    @FXML private ListView listViewPreWorkout;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){

    }
    @FXML
    private void searchPreWorkout(ActionEvent e){
        String nameSearch = searchText.getText();
        ObservableList<PreWorkout> searchedPreWorkouts = PreWorkoutDAO.searchPreWorkouts(nameSearch);

    }
}
