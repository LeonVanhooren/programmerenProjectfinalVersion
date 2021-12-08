package gui;

import database.DBBuilding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ResourceBundle;

public class AddStudentRoomController implements Initializable {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ListView<String> myListView;
    @FXML
    private TextField Address;
    @FXML
    private TextField City;
    @FXML
    private TextField Country;
    @FXML
    private TextField Zip;
    @FXML
    private TextField RoomNr;




    String[] roomIDs = program.getRoomIDs();

    String currentRoom;

    public void backToMenu(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListView.getItems().addAll(roomIDs);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentRoom = myListView.getSelectionModel().getSelectedItem();
                Address.setPromptText(searchBuildingAdressStudent(searchBuildingIDStudent(currentRoom)));
                City.setPromptText(searchBuildingCity(searchBuildingIDStudent(currentRoom)));
                Country.setPromptText(searchBuildingCountry(searchBuildingIDStudent(currentRoom)));
                Zip.setPromptText(searchBuildingZip(searchBuildingIDStudent(currentRoom)));
                RoomNr.setPromptText(""+searchRoomNrStudent(currentRoom));


            }
        });
    }



    public String searchRoomStudent(Student student){
        String roomID = null;
        for(Lease newLease: program.getLeases()){
            if(newLease.getStudentID().equals(student.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }

        return roomID;
    }

    public int searchRoomNrStudent(String roomID){
        int roomNr=0;
        for(Room newRoom: program.getRooms()){
            if(newRoom.getRoomID().equals(roomID)){
                roomNr = newRoom.getRoomNR();
            }
        }
        return roomNr;
    }

    public String searchBuildingIDStudent(String roomID){
        String buildingID = null;
        for(BelongsTo newBelongsTo: program.getBelongsToArrayList()){
            if(newBelongsTo.getRoomID().equals(roomID)){
                buildingID = newBelongsTo.getBuildingID();
            }
        }
        return buildingID;
    }

    public String searchBuildingAdressStudent(String buildingID){
        String buildingAdress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAdress = newBuilding.getAdress();
            }
        }
        return buildingAdress;
    }

    public String searchBuildingCountry(String buildingID){
        String buildingAdress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAdress = newBuilding.getCountry();
            }
        }
        return buildingAdress;
    }

    public String searchBuildingCity(String buildingID){
        String buildingAdress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAdress = newBuilding.getCity();
            }
        }
        return buildingAdress;
    }

    public String searchBuildingZip(String buildingID){
        String buildingAdress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAdress = newBuilding.getZip();
            }
        }
        return buildingAdress;
    }
    @FXML
    private TextField adressInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField zipInput;
    @FXML
    private Label buildingIDT;
    @FXML
    private Label roominfo;

    public void addBuildingButton(){
        String adress, country, city, zip;
        adress = adressInput.getText();
        country = countryInput.getText();
        city = cityInput.getText();
        zip = zipInput.getText();
        if(buildingExists(adress, country, city, zip) == false){
            int buildingID = (int)Math.floor(Math.random()*(99999-10000+1)+10000);
            String buildingIDString = ""+buildingID;
            Building newBuilding = new Building(buildingIDString, country, city, adress, zip);
            DBBuilding.addBuildingToDatabase(newBuilding);
            roominfo.setText("Building successfully added!");
            buildingIDT.setText("The buildingID is " + buildingIDString + ", remember this well!");
        }
        else{
            roominfo.setText("The database already contains this building!");
        }
    }

    public boolean buildingExists(String adress, String country, String city, String zip){
        for(Building b : program.getBuildings()){
            if((b.getAdress().equals(adress)) && (b.getCountry().equals(country)) && (b.getCity().equals(city)) && (b.getZip().equals(zip))){
                return true;
            }
        }
        return false;
    }

}
