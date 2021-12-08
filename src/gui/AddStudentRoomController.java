package gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Building;
import logic.ConservationApp;
import logic.Room;

public class AddStudentRoomController {
    private ConservationApp program = ConservationApp.getInstance();
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private TextField adressInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField zipInput;
    @FXML
    private TextField roomNrInput;
    @FXML
    private Label status;

    public void addRoomButton(){
        String adress, city, country, zip;
        int roomNr;
        adress = adressInput.getText();
        city = cityInput.getText();
        country = countryInput.getText();
        zip = zipInput.getText();
        roomNr = Integer.parseInt(roomNrInput.getText());

    }


    public boolean roomExists(String adress, String city, String country, String zip, int nr){
        for(Building b : program.getBuildings()){
            if ((b.getAdress().equals(adress)) || (b.getCity().equals(city)) || (b.getCountry().equals(country)) || (b.get)){
                for(Room r : b.getRooms()){
                    if(r.getRoomNR() == nr);{
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void clearInput(){
        adressInput.setText("");
        cityInput.setText("");
        countryInput.setText("");
        zipInput.setText("");
        roomNrInput.setText("");
    }

}
