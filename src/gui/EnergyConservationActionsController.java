package gui;

import database.DBActions;
import database.DBSavesBy;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.Action;
import logic.Appliance;
import logic.ConservationApp;
import logic.SavesBy;


import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnergyConservationActionsController implements Initializable {

    private ConservationApp programs = ConservationApp.getInstance();

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private Label electricityInfo;
    @FXML
    private Label gasInfo;
    @FXML
    private Label waterInfo;
    @FXML
    private DatePicker datePickerGas;
    @FXML
    private DatePicker datePickerElectricity;
    @FXML
    private DatePicker datePickerWater;
    @FXML
    private BarChart barChartGas;
    @FXML
    private BarChart barChartWater;
    @FXML
    private BarChart barChartElectricity;

    private String[] applianceKindInput= {"Electricity", "Water", "Gas" };

    @FXML
    private ChoiceBox<String> choiceBoxApplianceKind;
    @FXML
    private ChoiceBox<Appliance> choiceBoxElectricity;
    @FXML
    private ChoiceBox<Appliance> choiceBoxGas;
    @FXML
    private ChoiceBox<Appliance> choiceBoxWater;
    @FXML
    private TextArea description;
    @FXML
    private TextField savedAmount;
    @FXML
    private ListView myListView;
    @FXML
    private ChoiceBox<Action> choiceBoxElecAction;
    @FXML
    private ChoiceBox<Action> choiceBoxGasAction;
    @FXML
    private ChoiceBox<Action> choiceBoxWaterAction;

    private Action currentAction;
    private ArrayList<Action> listViewActions = programs.getActions();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxElectricity.getItems().addAll(appliancesElectricity);
        choiceBoxGas.getItems().addAll(appliancesGas);
        choiceBoxWater.getItems().addAll(appliancesWater);
        choiceBoxApplianceKind.getItems().addAll(applianceKindInput);
        choiceBoxGasAction.getItems().addAll(gasActions);
        choiceBoxElecAction.getItems().addAll(electricityActions);
        choiceBoxWaterAction.getItems().addAll(waterActions);

        showBarChartGas();
        showBarChartWater();
        showBarChartElectricity();

        myListView.getItems().addAll(listViewActions);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Action>() {
            @Override
            public void changed(ObservableValue<? extends Action> observableValue, Action action, Action t1) {
                   currentAction = (Action) myListView.getSelectionModel().getSelectedItem();
                   description.setPromptText(currentAction.getDescription());
                   savedAmount.setPromptText(""+currentAction.getSavedAmount());
                   choiceBoxApplianceKind.setValue(currentAction.getApplianceKind());
            }
        });
    }

    public void removeAction(){
        Action currentActionRemove = currentAction;

        ArrayList<Action> removeList = listViewActions;
        DBActions.removeActionFromDatabase(currentActionRemove);
        removeList.remove(currentActionRemove);

        myListView.getItems().clear();
        myListView.getItems().addAll(removeList);
        programs.setActions(removeList);
    }

    public void addAction(){
        String descriptionString = description.getText();
        int savedAmountInteger = Integer.parseInt(savedAmount.getText());
        String applianceKind = (String) choiceBoxApplianceKind.getValue();
        int actionID = (int) Math.floor(Math.random() * (999 - 100 + 1) + 100);
        String actionIDString = ""+actionID;

        Action newAction = new Action(actionIDString, applianceKind, 0, savedAmountInteger, descriptionString);

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
        description.setText("");
        savedAmount.setText("");
        choiceBoxApplianceKind.setValue("");
    }

    public void setActionOnElectricityAppliance(){

        Appliance currentAppliance = choiceBoxElectricity.getValue();
        LocalDate date = datePickerElectricity.getValue();
        String dateString = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
        Action currentAction = choiceBoxElecAction.getValue();

        SavesBy newSavesBy = new SavesBy(currentAction.getActionID(), currentAppliance.getApplianceID(), dateString );

        if(savesByPresent(newSavesBy)==false){
        ArrayList<SavesBy> savesByArrayList = programs.getSavesByArrayList();
        savesByArrayList.add(newSavesBy);
        programs.setSavesByArrayList(savesByArrayList);


        DBSavesBy.addSavesByToDatabase(newSavesBy);
        DBActions.changeActionFromDatabase("recommended", currentAction.getRecommended()+1, currentAction.getActionID());
        electricityInfo.setText("The conservation action is successfully added!");
        }
        else{
            electricityInfo.setText("You already added this action to this appliance today!");
        }
    }

    public void setActionOnGasAppliance(){
        Appliance currentAppliance = choiceBoxGas.getValue();
        LocalDate date = datePickerGas.getValue();
        String dateString = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
        Action currentAction = choiceBoxGasAction.getValue();

        SavesBy newSavesBy = new SavesBy(currentAction.getActionID(), currentAppliance.getApplianceID(), dateString );

        if(savesByPresent(newSavesBy)==false){
        ArrayList<SavesBy> savesByArrayList = programs.getSavesByArrayList();
        savesByArrayList.add(newSavesBy);
        programs.setSavesByArrayList(savesByArrayList);


        DBSavesBy.addSavesByToDatabase(newSavesBy);
        DBActions.changeActionFromDatabase("recommended", currentAction.getRecommended()+1, currentAction.getActionID());
        gasInfo.setText("The conservation action is successfully added!");
        }
        else{
            gasInfo.setText("You already added this action to this appliance today!");
        }
    }

    public void setActionOnWaterAppliance(){

        Appliance currentAppliance = choiceBoxWater.getValue();
        LocalDate date = datePickerWater.getValue();
        String dateString = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
        Action currentAction = choiceBoxWaterAction.getValue();

        SavesBy newSavesBy = new SavesBy(currentAction.getActionID(), currentAppliance.getApplianceID(), dateString );

        if(savesByPresent(newSavesBy)==false) {
            ArrayList<SavesBy> savesByArrayList = programs.getSavesByArrayList();
            savesByArrayList.add(newSavesBy);
            programs.setSavesByArrayList(savesByArrayList);


            DBSavesBy.addSavesByToDatabase(newSavesBy);
            DBActions.changeActionFromDatabase("recommended", currentAction.getRecommended()+1, currentAction.getActionID());

            waterInfo.setText("The conservation action is successfully added!");
        }
        else{
            waterInfo.setText("You already added this action to this appliance today!");
        }
    }

    public boolean savesByPresent(SavesBy savesBy){
        for(SavesBy newSavesBy: programs.getSavesByArrayList()) {
            if (savesBy.getDate().equals(newSavesBy.getDate())&&savesBy.getApplianceID().equals(newSavesBy.getApplianceID())&&
            savesBy.getActionID().equals(newSavesBy.getActionID())){
                return true;
            }
        }
        return false;
    }

    public void showBarChartGas(){

        ArrayList<Integer> recommended = new ArrayList<>();
        ArrayList<String> actionsIDs = new ArrayList<>();
        for(Action newAction: programs.getGasActions()) {
            recommended.add(newAction.getRecommended());
            actionsIDs.add(newAction.getActionID());
        }

        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("amount of times the action is used");
        for(int i = 0; i<actionsIDs.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(actionsIDs.get(i), recommended.get(i)));
        }
        barChartGas.getData().addAll(series1);
    }

    public void showBarChartWater(){

        ArrayList<Integer> recommended = new ArrayList<>();
        ArrayList<String> actionsIDs = new ArrayList<>();
        for(Action newAction: programs.getWaterActions()) {
            recommended.add(newAction.getRecommended());
            actionsIDs.add(newAction.getActionID());
        }

        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("amount of times the action is used");
        for(int i = 0; i<actionsIDs.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(actionsIDs.get(i), recommended.get(i)));
        }
        barChartWater.getData().addAll(series1);
    }

    public void showBarChartElectricity(){

        ArrayList<Integer> recommended = new ArrayList<>();
        ArrayList<String> actionsIDs = new ArrayList<>();
        for(Action newAction: programs.getElectricityActions()) {
            recommended.add(newAction.getRecommended());
            actionsIDs.add(newAction.getActionID());
        }

        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("amount of times the action is used");
        for(int i = 0; i<actionsIDs.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(actionsIDs.get(i), recommended.get(i)));
        }
        barChartElectricity.getData().addAll(series1);
    }
}
