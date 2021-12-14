package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;

public class AddContractController  {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void backToLandlordMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    private TextField studentIDInput;
    @FXML
    private DatePicker startDateInput;
    @FXML
    private TextField durationInput;
    @FXML
    private Label contractNr;
    @FXML
    private Label contractAdd;






}
