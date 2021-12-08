package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.*;

import java.awt.*;
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
    private ListView<String> myListViewBuilding;

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
    @FXML
    private TextField buildingIDRoom;
    @FXML
    private TextField buildingIDBuilding;




    String[] roomIDs = program.getRoomIDs();
    String[] buildingIDs = program.getBuildingIDs();

    String currentRoom;
    String currentBuilding;

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
                RoomNr.setPromptText(""+searchRoomNrStudent(currentRoom));
                buildingIDRoom.setPromptText(searchBuildingIDStudent(currentRoom));

            }


        });

        myListViewBuilding.getItems().addAll(buildingIDs);
        myListViewBuilding.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentBuilding = myListViewBuilding.getSelectionModel().getSelectedItem();
                Address.setPromptText(searchBuildingAdressStudent(currentBuilding));
                City.setPromptText(searchBuildingCity(currentBuilding));
                Country.setPromptText(searchBuildingCountry(currentBuilding));
                Zip.setPromptText(searchBuildingZip(currentBuilding));
                buildingIDBuilding.setPromptText(currentBuilding);

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

}
