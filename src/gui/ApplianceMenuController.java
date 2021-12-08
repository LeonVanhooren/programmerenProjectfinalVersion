package gui;

import database.DBAppliance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Appliance;
import logic.ConservationApp;

import java.io.IOException;

public class ApplianceMenuController {
    private ConservationApp program = ConservationApp.getInstance();
    private Stage stage;
    private Parent root;
    private Scene scene;

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

    public void addAppliance(ActionEvent event){
        String applianceID, consumption, efficiency, QRCode;

        applianceID = applianceIDTF.getText();
        consumption = consumptionTF.getText();
        efficiency = efficiencyTF.getText();
        QRCode = QRCodeTF.getText();

        if(appliancePresent(applianceID)==true){
            setAddApplianceStatus("The database already contains this appliance!");
        }
        else{
            Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode);
            DBAppliance.addApplianceToDatabase(newAppliance);

            setAddApplianceStatus("The appliance is added to the database!");

        }

    }

    public void changeAppliance(ActionEvent event) {
        String applianceID, consumption, efficiency, QRCode;

        applianceID = applianceIDTF.getText();
        consumption = consumptionTF.getText();
        efficiency = efficiencyTF.getText();
        QRCode = QRCodeTF.getText();

        if (appliancePresent(applianceID) == true) {
            Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode);
            Appliance oldAppliance = new Appliance(applianceID, consumption, efficiency, QRCode);
            DBAppliance.changeApplianceFromDatabase(newAppliance, oldAppliance);

            setChangeApplianceStatus("The two appliances are changed in the database!");

        } else {
            setChangeApplianceStatus("The databasa doesn't contain the old appliance!");

        }
    }
    public void removeAppliance(ActionEvent event){
        String applianceID, consumption, efficiency, QRCode;

        applianceID = applianceIDTF.getText();
        consumption = consumptionTF.getText();
        efficiency = efficiencyTF.getText();
        QRCode = QRCodeTF.getText();



        if(appliancePresent(applianceID)==true){
            Appliance appliance = new Appliance(applianceID, consumption, efficiency, QRCode);
            DBAppliance.removeApplianceFromDatabase(appliance);

            setRemoveApplianceStatus("The appliance is removed from the database!");

        }
        else{ setRemoveApplianceStatus("The database doesn't contain this appliance!");
    }
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
