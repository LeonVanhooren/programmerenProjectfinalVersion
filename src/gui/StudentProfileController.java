package gui;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {
    ConservationApp program = ConservationApp.getInstance();
    private Parent root;
    private Scene scene;
    private Stage stage;

    public void backToStudentMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Label studentNumber;
    @FXML
    private Label roomMessage;

    public void changeStudent(){
        if(!firstName.getText().equals(null)){
            DB
        }
        if(!lastName.getText().equals(null)){

        }
        if(!email.getText().equals(null)){

        }
        if(!password.getText().equals(null)){

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
