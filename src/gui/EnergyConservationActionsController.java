package gui;

import database.DBActions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private ConservationApp programs = ConservationApp.getInstance();

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

    private ArrayList<Appliance> appliancesElectricity = programs.getAppliancesCurrentStudentElectricity();
    private ArrayList<Appliance> appliancesWater = programs.getAppliancesCurrentStudentWater();
    private ArrayList<Appliance> appliancesGas = programs.getAppliancesCurrentStudentGas();
    private ArrayList<Action> electricityActions = programs.getElectricityActions();
    private ArrayList<Action> gasActions = programs.getGasActions();
    private ArrayList<Action> waterActions = programs.getWaterActions();

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
    @FXML
    private ChoiceBox choiceBoxElecAction;
    @FXML
    private ChoiceBox choiceBoxGasAction;
    @FXML
    private ChoiceBox choiceBoxWaterAction;

    private Action currentAction;
    private ArrayList<Action> listViewActions = programs.getActions();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxElectricity.getItems().addAll(appliancesElectricity);
        choiceBoxGas.getItems().addAll(appliancesGas);
        choiceBoxWater.getItems().addAll(appliancesWater);
        choiceBoxApplianceKind.getItems().addAll(applianceKindInput);
        choiceBoxGasAction.getItems().addAll(gasActions);
        choiceBoxElecAction.getItems().addAll(electricityActions);
        choiceBoxWaterAction.getItems().addAll(waterActions);

        myListView.getItems().addAll(listViewActions);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Action>() {
            @Override
            public void changed(ObservableValue<? extends Action> observableValue, Action action, Action t1) {
                   currentAction = (Action) myListView.getSelectionModel().getSelectedItem();
                   /*description.setPromptText(currentAction.getDescription());
                   savedAmount.setPromptText(""+currentAction.getSavedAmount());
                   choiceBoxApplianceKind.setValue(currentAction.getApplianceKind());*/

            }


        });

    }

    public void addAction(){
        String descriptionString = description.getText();
        int savedAmountInteger = Integer.parseInt(savedAmount.getText());
        String applianceKind = (String) choiceBoxApplianceKind.getValue();

        Action newAction = new Action(descriptionString, applianceKind, savedAmountInteger, 0);

        ArrayList<Action> actions = new ArrayList<>();
        actions.add(newAction);
        programs.setActions(actions);

        DBActions.addActionsToDatabase(newAction);

        if(newAction.getApplianceKind().equals("Electricity")){
            choiceBoxElecAction.getItems().add(newAction);
        }
        if(newAction.getApplianceKind().equals("Gas")){
            choiceBoxGasAction.getItems().add(newAction);
        }
        if(newAction.getApplianceKind().equals("Water")){
            choiceBoxWaterAction.getItems().add(newAction);
        }
        listViewActions.add(newAction);
        myListView.getItems().clear();
        myListView.getItems().addAll(listViewActions);
    }




}
