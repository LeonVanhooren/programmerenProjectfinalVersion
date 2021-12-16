package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import logic.Appliance;
import logic.ConservationApp;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnergyConservationActionsController implements Initializable {

    private ConservationApp program = ConservationApp.getInstance();

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void backToStudentMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StudentMenu.fxml"));
        root = loader.load();



        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    private ArrayList<Appliance> appliancesElectricity = program.getAppliancesCurrentStudentElectricity();
    private ArrayList<Appliance> appliancesWater = program.getAppliancesCurrentStudentWater();
    private ArrayList<Appliance> appliancesGas = program.getAppliancesCurrentStudentGas();



    @FXML
    private ChoiceBox choiceBoxElectricity;
    @FXML
    private ChoiceBox choiceBoxGas;
    @FXML
    private ChoiceBox choiceBoxWater;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxElectricity.getItems().addAll(appliancesElectricity);
        choiceBoxGas.getItems().addAll(appliancesGas);
        choiceBoxWater.getItems().addAll(appliancesWater);

    }


}
