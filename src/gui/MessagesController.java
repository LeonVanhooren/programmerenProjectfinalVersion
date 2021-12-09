package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagesController {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Scene scene;
    private Stage stage;

    public void accept() {

    }

    public void decline() {

    }

    public void backToStudentMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void initialize(URL location, ResourceBundle resources) {

        }
    }

