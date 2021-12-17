package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.ConservationApp;
import java.io.IOException;

public class StudentMenuController {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;


    public void applianceMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplianceMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appliance menu");
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

    public void goToStudentProfile(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfile.fxml"));
        root = loader.load();


        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student profile");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void goToConservationMenu(ActionEvent event)throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EnergyConservationActions.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Energy conservation menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void goToMessages(ActionEvent event)throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Messages.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Messages");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void goToStudentReport(ActionEvent event)throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentReport.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student report");
        scene = new Scene(root);
        stage.setScene(scene);
    }

}
