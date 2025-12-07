package alexgessner.designiiassignment6.Controllers;

import alexgessner.designiiassignment6.AppState;
import alexgessner.designiiassignment6.Model.PreWorkout;
import alexgessner.designiiassignment6.Model.PreWorkoutDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML private TableView<PreWorkout> preWorkoutTableView;
    @FXML private Button submitButton;
    @FXML private Button exitButton;
    @FXML private TableColumn<PreWorkout, Integer> idClm;
    @FXML private TableColumn<PreWorkout, String> nameClm;
    @FXML private TableColumn<PreWorkout, Integer> cafClm;
    @FXML private TableColumn<PreWorkout, Integer> lCitClm;
    @FXML private TableColumn<PreWorkout, Integer> betaAlanClm;


    @Override
    @FXML public void initialize(URL url, ResourceBundle resourceBundle){
        //sets up columns and uses reflection to get values
        idClm.setCellValueFactory((cell -> new ReadOnlyObjectWrapper<>(cell.getValue().preWorkoutID())));
        nameClm.setCellValueFactory((cell -> new ReadOnlyObjectWrapper<>(cell.getValue().preName())));
        cafClm.setCellValueFactory((cell -> new ReadOnlyObjectWrapper<>(cell.getValue().caffeineAmount())));
        lCitClm.setCellValueFactory((cell -> new ReadOnlyObjectWrapper<>(cell.getValue().lCitrullineAmount())));
        betaAlanClm.setCellValueFactory((cell -> new ReadOnlyObjectWrapper<>(cell.getValue().betaAlanineAmount())));

        //sets tableView to be editable
        preWorkoutTableView.setEditable(true);
        idClm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nameClm.setCellFactory(TextFieldTableCell.forTableColumn());
        cafClm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lCitClm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        betaAlanClm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        preWorkoutTableView.setItems(AppState.preWorkoutList);

    }

    @FXML public void submitEdit(ActionEvent e) throws IOException {
        int row = 0; //Current row in the tableView. Only 1 row with 1 PreWorkout obj.

        //removes the current record from the database
        PreWorkoutDAO.removePreWorkout(AppState.preWorkoutList.getFirst());

        PreWorkout preWorkout = new PreWorkout(
                idClm.getCellData(row),
                nameClm.getCellData(row),
                cafClm.getCellData(row),
                lCitClm.getCellData(row),
                betaAlanClm.getCellData(row));

        //Adds the newly updated pre to the TableView and to the database
        AppState.preWorkoutList.set(0, preWorkout);
        PreWorkoutDAO.addPreWorkout(preWorkout);

        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        AppState.switchToMainScene(stage);
    }

    @FXML public void exitToMain(ActionEvent e) throws IOException{
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        AppState.switchToMainScene(stage);
    }

    @FXML public void idChanged(TableColumn.CellEditEvent editedID){
        PreWorkout preWorkout = (PreWorkout) editedID.getRowValue();

        PreWorkout updatedID = new PreWorkout(
                (int) editedID.getNewValue(),
                preWorkout.preName(),
                preWorkout.caffeineAmount(),
                preWorkout.lCitrullineAmount(),
                preWorkout.betaAlanineAmount()
        );
        //only 1 object in the List right now, replacing it with new Object
        AppState.preWorkoutList.set(0, updatedID);
        preWorkoutTableView.refresh();
    }

    @FXML public void nameChanged(TableColumn.CellEditEvent editedID){
        PreWorkout preWorkout = (PreWorkout) editedID.getRowValue();

        PreWorkout updatedName = new PreWorkout(
                preWorkout.preWorkoutID(),
                (String) editedID.getNewValue(),
                preWorkout.caffeineAmount(),
                preWorkout.lCitrullineAmount(),
                preWorkout.betaAlanineAmount()
        );

        AppState.preWorkoutList.set(0, updatedName);
        preWorkoutTableView.refresh();
    }

    @FXML public void cafChanged(TableColumn.CellEditEvent editedID){
        PreWorkout preWorkout = (PreWorkout) editedID.getRowValue();

        PreWorkout updatedCaf = new PreWorkout(
                preWorkout.preWorkoutID(),
                preWorkout.preName(),
                (int) editedID.getNewValue(),
                preWorkout.lCitrullineAmount(),
                preWorkout.betaAlanineAmount()
        );

        AppState.preWorkoutList.set(0, updatedCaf);
        preWorkoutTableView.refresh();
    }
    @FXML public void lCitChanged(TableColumn.CellEditEvent editedID){
        PreWorkout preWorkout = (PreWorkout) editedID.getRowValue();

        PreWorkout updatedLCit = new PreWorkout(
                preWorkout.preWorkoutID(),
                preWorkout.preName(),
                preWorkout.caffeineAmount(),
                (int) editedID.getNewValue(),
                preWorkout.betaAlanineAmount()
        );

        AppState.preWorkoutList.set(0, updatedLCit);
        preWorkoutTableView.refresh();
    }
    @FXML public void betaAlanChanged(TableColumn.CellEditEvent editedID){
        PreWorkout preWorkout = (PreWorkout) editedID.getRowValue();

        PreWorkout updatedBetaAlan = new PreWorkout(
                preWorkout.preWorkoutID(),
                preWorkout.preName(),
                preWorkout.caffeineAmount(),
                preWorkout.lCitrullineAmount(),
                (int) editedID.getNewValue()
        );

        AppState.preWorkoutList.set(0, updatedBetaAlan);
        preWorkoutTableView.refresh();
    }
}
