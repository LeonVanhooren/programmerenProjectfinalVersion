package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;

public class LandlordMenuController {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label usernameLabelLandlord;

    public void setUsernameLandlord(String username){
        usernameLabelLandlord.setText("Welcome: "+username);
    }

    public void monthlyConsumptionMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterEnergyConsumption.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Consumption menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void studentRoomMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudentRoom.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student room/building menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void goToLandlordProfile(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordProfile.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Landlord profile");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void goToContractMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddContract.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Contract menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void goToLandlordReport(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordReport.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Landlord report");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void backToSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign in menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }
}
