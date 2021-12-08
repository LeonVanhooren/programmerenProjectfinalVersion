package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;

public class ApplianceMenuController {
    private ConservationApp program = ConservationApp.getInstance();
    private Stage stage;
    private Parent root;
    private Scene scene;
    public void applianceMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplianceMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appliance menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }
}
