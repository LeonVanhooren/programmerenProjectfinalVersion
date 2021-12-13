package gui;

import database.DBAppliance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ApplianceMenuController implements Initializable {
    private ConservationApp program = ConservationApp.getInstance();
    private Stage stage;
    private Parent root;
    private Scene scene;

    private String currentApplianceName;

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
    private TextField applianceNameTF;

    public void addAppliance(ActionEvent event){
        String applianceID, consumption, efficiency, QRCode, applianceName;

        applianceID = applianceIDTF.getText();
        consumption = consumptionTF.getText();
        efficiency = efficiencyTF.getText();
        QRCode = QRCodeTF.getText();
        applianceName = applianceNameTF.getText();

        if(appliancePresent(applianceID)==true){
            setAddApplianceStatus("The database already contains this appliance!");
        }
        else{
            Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode, applianceName);



            DBAppliance.addApplianceToDatabase(newAppliance);

            setAddApplianceStatus("The appliance is added to the database!");

        }

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
        String applianceID, consumption, efficiency, QRCode, applianceName;

        applianceID = applianceIDChange.getText();
        consumption = consumptionChange.getText();
        efficiency = efficiencyChange.getText();
        QRCode = QRCodeChange.getText();
        applianceName = applianceNameChange.getText();


    }

    @FXML
    private ListView<String> myListView;


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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListView.getItems().addAll(getApplianceIDs(searchRoomID(program.getCurrentStudent())));
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String currentApplianceID = myListView.getSelectionModel().getSelectedItem();
                consumptionChange.setPromptText(searchAppliance(currentApplianceID, program.getCurrentStudent()).getConsumption());
                efficiencyChange.setPromptText(searchAppliance(currentApplianceID, program.getCurrentStudent()).getEfficiency());
                applianceIDChange.setText("Appliance ID:"+searchAppliance(currentApplianceID, program.getCurrentStudent()).getApplianceID());
                applianceNameChange.setPromptText(searchAppliance(currentApplianceID, program.getCurrentStudent()).getApplianceName());
                QRCodeChange.setPromptText(searchAppliance(currentApplianceID, program.getCurrentStudent()).getQRCode());
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

    public void removeAppliance(ActionEvent event){
        String applianceID, consumption, efficiency, QRCode;
    }


    //we moeten een appliance id number generator doen en een extra vakje voor appliance naam zodat meerdere laptops kunnen toegevoegd worden!!!!!!!!

    public boolean appliancePresent(String applianceID){
        for(Appliance newAppliance: program.getAppliances()){
            if(newAppliance.getApplianceID().equals(applianceID)){
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
