package gui;

import database.DBActions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Action;
import logic.Appliance;
import logic.ConservationApp;


import java.beans.PropertyChangeListener;
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

    private String[] applianceKindInput= {"Electricity", "Water", "Gas" };

    @FXML
    private ChoiceBox choiceBoxApplianceKind;
    @FXML
    private ChoiceBox choiceBoxElectricity;
    @FXML
    private ChoiceBox choiceBoxGas;
    @FXML
    private ChoiceBox choiceBoxWater;
    @FXML
    private TextArea description;
    @FXML
    private TextField savedAmount;
    @FXML
    private ListView myListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxElectricity.getItems().addAll(appliancesElectricity);
        choiceBoxGas.getItems().addAll(appliancesGas);
        choiceBoxWater.getItems().addAll(appliancesWater);
        choiceBoxApplianceKind.getItems().addAll(applianceKindInput);
        myListView.getItems().addAll(program.getActions());

    }

    public void addAction(){
        String descriptionString = description.getText();
        int savedAmountInteger = Integer.parseInt(savedAmount.getText());
        String applianceKind = (String) choiceBoxApplianceKind.getValue();

        Action newAction = new Action(descriptionString, applianceKind, savedAmountInteger, 0);

        ArrayList<Action> actions = new ArrayList<>();
        actions.add(newAction);
        program.setActions(actions);

        DBActions.addActionsToDatabase(newAction);

        myListView.getItems().clear();
        myListView.getItems().addAll(program.getActions());
    }


}
