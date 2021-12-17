package gui;

import database.DBLandlord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandlordProfileController implements Initializable {
    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label landlordID;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField telephoneNr;
    @FXML
    private TextField password;

    public void backToMenu(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setPromptText(program.getCurrentLandlord().getFirstName());
        lastName.setPromptText(program.getCurrentLandlord().getLastName());
        email.setPromptText(program.getCurrentLandlord().getEmail());
        password.setPromptText(program.getCurrentLandlord().getPassWord());
        landlordID.setText("Landlord ID: "+program.getCurrentLandlord().getLandlordID());
        telephoneNr.setPromptText(program.getCurrentLandlord().getTelephoneNR());
    }

    public void changeLandlord(){
        if(!firstName.getText().equals("")){
            DBLandlord.changeLandlordFromDatabase("firstname", firstName.getText(), program.getCurrentLandlord().getLandlordID());
        }
        if(!lastName.getText().equals("")){
            DBLandlord.changeLandlordFromDatabase("lastname", lastName.getText(), program.getCurrentLandlord().getLandlordID());
        }
        if(!email.getText().equals("")){
            DBLandlord.changeLandlordFromDatabase("email", email.getText(), program.getCurrentLandlord().getLandlordID());
        }
        if(!password.getText().equals("")){
            DBLandlord.changeLandlordFromDatabase("password", password.getText(), program.getCurrentLandlord().getLandlordID());
        }
        if(!telephoneNr.getText().equals("")){
            DBLandlord.changeLandlordFromDatabase("telephoneNr", telephoneNr.getText(), program.getCurrentLandlord().getLandlordID());
        }
    }
}
