package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.ConservationApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {
    private ConservationApp program = ConservationApp.getInstance();
    @FXML
    private WebView myWebView;
    @FXML
    private TextField address;

    private WebEngine webEngine;

    private Parent root;
    private Scene scene;
    private Stage stage;

    public void backToApplianceMenu(ActionEvent event) throws IOException{


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplianceMenu.fxml"));
            root = loader.load();

            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Appliance menu");
            scene = new Scene(root);
            stage.setScene(scene);


    }


    public void loadPage(){
        webEngine.load("https://"+program.getCurrentSite());
    }
    public void loadAddress(){
        webEngine.load("https://"+address.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = myWebView.getEngine();
        loadPage();
    }
}
