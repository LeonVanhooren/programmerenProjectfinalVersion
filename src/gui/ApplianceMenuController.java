package gui;

import database.DBAppliance;
import database.DBContains;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ApplianceMenuController implements Initializable {
    private ConservationApp program = ConservationApp.getInstance();
    private Stage stage;
    private Parent root;
    private Scene scene;

    private String currentApplianceID;

    public void backToStudentMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    private TextField QRCodeTF;
    @FXML
    private TextField applianceIDTF;
    @FXML
    private TextField consumptionTF;
    @FXML
    private TextField efficiencyTF;
    @FXML
    private Label addApplianceLabel;
    @FXML
    private Label RemoveApplianceLabel;
    @FXML
    private Label ChangeApplianceLabel;
    @FXML
    private Label applianceID;
    @FXML
    private TextField applianceNameTF;


    public void addAppliance(ActionEvent event){
        String  consumption, efficiency, QRCode, applianceName;
        int applianceIDint;

        applianceIDint = (int)Math.floor(Math.random()*(999-100+1)+999);
        String applianceIDString = "1"+applianceIDint;
        consumption = consumptionTF.getText();
        efficiency = efficiencyTF.getText();
        QRCode = QRCodeTF.getText();
        applianceName = applianceNameTF.getText();

        if(appliancePresent(applianceName)==true){
            setAddApplianceStatus("The database already contains this appliance!");
        }
        else{
            Appliance newAppliance = new Appliance(applianceIDString, consumption, efficiency, QRCode, applianceName, choiceBoxAdd.getValue());
            applianceID.setText("The appliance ID is: "+applianceIDString);

            DBAppliance.addApplianceToDatabase(newAppliance);
            ArrayList<Appliance> appliances = program.getAppliances();
            appliances.add(newAppliance);
            program.setAppliances(appliances);

            Contains newContains = new Contains(searchRoomID(program.getCurrentStudent()), applianceIDString);
            DBContains.addContainsToDatabase(newContains);
            ArrayList<Contains> containsArrayList = program.getContainsArrayList();
            containsArrayList.add(newContains);
            program.setContainsArrayList(containsArrayList);

            this.addApplianceList(newAppliance);
            this.refresh();



            setAddApplianceStatus("The appliance is added to the database!");

        }


    }

    public void removeApplianceList(Appliance appliance){
        ArrayList<Appliance> applianceArrayList = new ArrayList<>();

        for(int i=0; i<appliances.length;i++){
            applianceArrayList.add(appliances[i]);
        }
        applianceArrayList.remove(appliance);

        Appliance[] appliances1 = new Appliance[applianceArrayList.size()];
        for(int i=0; i<applianceArrayList.size(); i++){
            appliances1[i] = applianceArrayList.get(i);
        }
        appliances = appliances1;
    }

    public void addApplianceList(Appliance appliance){
        ArrayList<Appliance> applianceArrayList = new ArrayList<>();

        for(int i=0; i<appliances.length;i++){
            applianceArrayList.add(appliances[i]);
        }
        applianceArrayList.add(appliance);

        Appliance[] appliances1 = new Appliance[applianceArrayList.size()];
        for(int i=0; i<applianceArrayList.size(); i++){
            appliances1[i] = applianceArrayList.get(i);
        }
        appliances = appliances1;
    }

    public void changeApplianceList(Appliance newAppliance, Appliance oldAppliance){
        ArrayList<Appliance> applianceArrayList = new ArrayList<>();

        for(int i=0; i<appliances.length;i++){
            applianceArrayList.add(appliances[i]);
        }
        applianceArrayList.remove(oldAppliance);
        applianceArrayList.add(newAppliance);

        Appliance[] appliances1 = new Appliance[applianceArrayList.size()];
        for(int i=0; i<applianceArrayList.size(); i++){
            appliances1[i] = applianceArrayList.get(i);
        }
        appliances = appliances1;
    }

    public void refresh(){
        myListView.getItems().clear();
        myListView.getItems().addAll(appliances);
    }

    @FXML
    private TextField applianceNameChange;
    @FXML
    private TextField consumptionChange;
    @FXML
    private TextField efficiencyChange;
    @FXML
    private TextField QRCodeChange;

    public void changeAppliance(ActionEvent event) {
        if(!applianceNameChange.getText().equals("")){
            DBAppliance.changeApplianceFromDatabase("applianceName", applianceNameChange.getText(), currentAppliance.getApplianceID());
        }
        if(!consumptionChange.getText().equals("")){
            DBAppliance.changeApplianceFromDatabase("consumption", consumptionChange.getText(), currentAppliance.getApplianceID());
        }
        if(!efficiencyChange.getText().equals("")){
            DBAppliance.changeApplianceFromDatabase("efficiency", efficiencyChange.getText(), currentAppliance.getApplianceID());
        }
        if (!QRCodeChange.getText().equals("")){
            DBAppliance.changeApplianceFromDatabase("QR-code", QRCodeChange.getText(), currentAppliance.getApplianceID());
        }
        if(!choiceBoxChange.getValue().equals("")){
            DBAppliance.changeApplianceFromDatabase("applianceKind", choiceBoxChange.getValue(), currentAppliance.getApplianceID());
        }

        changeApplianceList(searchApplianceChange(currentAppliance.getApplianceID()) , currentAppliance);
        refresh();


    }

    public Appliance searchApplianceChange(String applianceID){
        ArrayList<Appliance> appliances = DBAppliance.databaseReadAppliance();
        Appliance appliance1 = null;
        for(Appliance newAppliance:appliances){
            if(newAppliance.getApplianceID().equals(applianceID)){
                appliance1 = newAppliance;
            }
        }
        return appliance1;
    }

    @FXML
    private ListView<Appliance> myListView;


    public String searchRoomID(Student student){
        String output=null;
        for(Lease newLease: program.getLeases()){
            if(newLease.getStudentID().equals(student.getStudentID())){
                output =newLease.getRoomID();
            }
        }
        return output;
    }


     public String[] getApplianceIDs(String roomID){
         ArrayList<String> output1 = new ArrayList<>();
         for (Contains newContains: program.getContainsArrayList()){
             if(newContains.getRoomID().equals(roomID)){
                 output1.add(newContains.getApplianceID());
             }
         }
         String[] output = new String[output1.size()];
         for (int j = 0; j< output1.size();j++) {
             output[j] = output1.get(j);
         }
         return output;
     }

     @FXML
     private Label applianceIDChange;
    @FXML
    private ChoiceBox<String> choiceBoxAdd;
    @FXML
    private ChoiceBox<String> choiceBoxChange;

    private String[] choices ={"Electricity", "Water", "Gas"};

    private String currentChoiceAdd;
    private String currentChoiceChange;

    public void getCurrentAdd(ActionEvent event){
        currentChoiceAdd = choiceBoxAdd.getValue();
    }

    public void getCurrentChange(ActionEvent event){
        currentChoiceChange = choiceBoxChange.getValue();
    }

    private Appliance currentAppliance;

    private Appliance[] appliances = program.getAppliancesStudent();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxAdd.getItems().addAll(choices);
        choiceBoxAdd.setOnAction(this::getCurrentAdd);
        choiceBoxChange.getItems().addAll(choices);
        choiceBoxChange.setOnAction(this::getCurrentChange);

        myListView.getItems().addAll(appliances);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Appliance>() {
            @Override
            public void changed(ObservableValue<? extends Appliance> observableValue, Appliance appliance, Appliance t1) {
                currentAppliance = myListView.getSelectionModel().getSelectedItem();
                System.out.println(currentAppliance);
                if(!currentAppliance.equals(null)){
                consumptionChange.setPromptText(currentAppliance.getConsumption());
                efficiencyChange.setPromptText(currentAppliance.getEfficiency());
                applianceIDChange.setText("Appliance ID:"+currentAppliance.getApplianceID());
                applianceNameChange.setPromptText(currentAppliance.getApplianceName());
                QRCodeChange.setPromptText(currentAppliance.getQRCode());
                choiceBoxChange.setValue(currentAppliance.getApplianceKind());
                }
            }



    });

    }



    public Appliance searchAppliance(String applianceID, Student student){
        Appliance output = null;
        for(Appliance newAppliance: program.getAppliances()){
            if(newAppliance.getApplianceID().equals(applianceID)){
                output = newAppliance;
            }
        }
        return output;
    }

    @FXML
    private TextField applianceRemove;

    public void removeAppliance(ActionEvent event){
        String removeAppliance = applianceRemove.getText();
        Appliance newAppliance=null;

        for(Appliance newApp: program.getAppliancesStudent()){
            if(newApp.getApplianceName().equals(removeAppliance)){
                newAppliance = newApp;
            }
        }

        if(appliancePresent(removeAppliance)==true&&!newAppliance.equals(null)){

            ArrayList<Appliance> appliances = program.getAppliances();
            appliances.remove(newAppliance);
            program.setAppliances(appliances);

            DBAppliance.removeApplianceFromDatabase(newAppliance);

            Contains newContains = new Contains(searchRoomID(program.getCurrentStudent()), newAppliance.getApplianceID());

            ArrayList<Contains> containsArrayList = new ArrayList<>();
            containsArrayList.remove(newContains);
            program.setContainsArrayList(containsArrayList);

            DBContains.removeContainsFromDatabase(newContains);

            this.removeApplianceList(newAppliance);
            this.refresh();

            RemoveApplianceLabel.setText("Appliance is removed!");

        }
        else{
            RemoveApplianceLabel.setText("You did not input an existing appliance!");
        }





    }


    //we moeten een appliance id number generator doen en een extra vakje voor appliance naam zodat meerdere laptops kunnen toegevoegd worden!!!!!!!!

    public boolean appliancePresent(String applianceName){
        for(Appliance newAppliance: program.getAppliancesStudent()){
            if(newAppliance.getApplianceName().equals(applianceName)){
                return true;
            }
        }
        return false;
    }



    public void setAddApplianceStatus(String output){
        addApplianceLabel.setText(output);

    }

    public void setRemoveApplianceStatus(String output){
        RemoveApplianceLabel.setText(output);

    }
    public void setChangeApplianceStatus(String output){
        ChangeApplianceLabel.setText(output);

    }


}
