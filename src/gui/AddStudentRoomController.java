package gui;

import database.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStudentRoomController implements Initializable {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ListView myListView;
    @FXML
    private ListView myListViewBuilding;
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
    private TextField characteristics;
    @FXML
    private TextField addressInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField zipInput;
    @FXML
    private Label buildingIDT;
    @FXML
    private Label buildinginfo;
    @FXML
    private ChoiceBox<String> buildingIDChoice;
    @FXML
    private TextField roomNrInput;
    @FXML
    private Label registerRoomInfo;
    @FXML
    private Label roomIDT;
    @FXML
    private TextField characteristicsInput;

    private Room currentRoom;
    private ArrayList<Room> listViewRooms = program.getRooms();
    private Building currentBuilding;
    private ArrayList<Building> listViewBuildings = program.getBuildings();
    private String currentChoiceAdd;

    public void backToMenu(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void removeBuilding(){
        Building currentBuildingRemove = currentBuilding;

        buildingIDChoice.getItems().remove(currentBuildingRemove.getBuildingID());

        DBBuilding.removeBuildingFromDatabase(currentBuilding);
        listViewBuildings.remove(currentBuildingRemove);

        program.setBuildings(DBBuilding.databaseReadBuilding());

        myListViewBuilding.getItems().clear();
        myListViewBuilding.getItems().addAll(program.getCurrentLandlordBuildings());
        program.setBuildings(listViewBuildings);
    }

    public void removeRoom(){
        Room currentRoomRemove = currentRoom;
        DBRoom.removeRoomFromDatabase(currentRoom);
        listViewRooms.remove(currentRoomRemove);

        program.setRooms(DBRoom.databaseReadRoom());

        myListView.getItems().clear();
        myListView.getItems().addAll(program.getCurrentLandlordRooms());
        program.setRooms(listViewRooms);
    }

    public void clearInputBuildingChange(){
        Address.setText("");
        City.setText("");
        Country.setText("");
        Zip.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListViewBuilding.getItems().addAll(program.getCurrentLandlordBuildings());
        myListViewBuilding.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Building>() {


            @Override
            public void changed(ObservableValue<? extends Building> observableValue, Building s, Building t1) {
                currentBuilding = (Building) myListViewBuilding.getSelectionModel().getSelectedItem();
            }
        });
        buildingIDChoice.getItems().addAll(program.getCurrentLandlordBuildingIDs());
        buildingIDChoice.setOnAction(this::getBuildingIDChoice);
        myListView.getItems().addAll(program.getCurrentLandlordRooms());
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Room>() {
            @Override
            public void changed(ObservableValue<? extends Room> observableValue, Room s, Room t1) {
                currentRoom =(Room) myListView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public String searchCharacteristics(String roomID){
        String output = null;
        for(Room newRoom: program.getRooms()){
            if(newRoom.getRoomID().equals(roomID)){
                output = newRoom.getCharacteristics();
            }
        }
        return output;
    }

    public void getBuildingIDChoice(ActionEvent event){
        currentChoiceAdd = buildingIDChoice.getValue();
    }

    public void addBuildingButton(){
        String address, country, city, zip;
        address = addressInput.getText();
        country = countryInput.getText();
        city = cityInput.getText();
        zip = zipInput.getText();

        if (!emptyFieldsBuilding()){
            if(buildingExists(address, country, city, zip) == false){
            int buildingID = (int)Math.floor(Math.random()*(99999-10000+1)+10000);
            String buildingIDString = ""+buildingID;
            Building newBuilding = new Building(buildingIDString, country, city, address, zip);
            DBBuilding.addBuildingToDatabase(newBuilding);

            ArrayList<Building> buildings = new ArrayList<>();
            buildings = program.getBuildings();
            buildings.add(newBuilding);
            program.setBuildings(buildings);

            buildinginfo.setText("Building successfully added!");
            buildingIDT.setText("The buildingID is " + buildingIDString + ", remember this well!");
            Ownership newOwnership = new Ownership(newBuilding.getBuildingID(), program.getCurrentLandlord().getLandlordID());
            DBOwnership.addOwnershipToDatabase(newOwnership);

            ArrayList<Ownership> ownerships = new ArrayList<>();
            ownerships = program.getOwnerships();
            ownerships.add(newOwnership);
            program.setOwnerships(ownerships);

            buildingIDChoice.getItems().add(newBuilding.getBuildingID());

            myListViewBuilding.getItems().clear();
            myListViewBuilding.getItems().addAll(program.getCurrentLandlordBuildings());

            clearBuildingInput();
        }
        else {
            buildinginfo.setText("The database already contains this building!");
        }}
        else{buildingIDT.setText("Please fill in every field!");}
    }

    public boolean buildingExists(String address, String country, String city, String zip){
        for(Building b : program.getBuildings()){
            if((b.getAddress().equals(address)) && (b.getCountry().equals(country)) && (b.getCity().equals(city)) && (b.getZip().equals(zip))){
                return true;
            }
        }
        return false;
    }

    public void addRoomButton(){
        String characteristics;
        int roomNr;
        roomNr = Integer.parseInt("0"+roomNrInput.getText());
        characteristics = characteristicsInput.getText();
        if(!emptyFieldsRoom()){

            if(!roomExists(buildingIDChoice.getValue(), roomNr)&&buildingIsFromLandlord(buildingIDChoice.getValue())){
            String roomIDString = buildingIDChoice.getValue() +"." + roomNrInput.getText();
            Room newRoom = new Room(roomNr, roomIDString, buildingIDChoice.getValue(), characteristics);
            DBRoom.addRoomToDatabase(newRoom);

            ArrayList<Room> roomsList = new ArrayList<>();
            roomsList = program.getRooms();
            roomsList.add(newRoom);
            program.setRooms(roomsList);

            registerRoomInfo.setText("Student room successfully added");
            roomIDT.setText("The roomID is " + roomIDString + ", remember this well!");
            BelongsTo newBelongsTo = new BelongsTo(newRoom.getBuildingID(), roomIDString);
            DBBelongsTo.addBelongingToDatabase(newBelongsTo);

            ArrayList<BelongsTo> belongsToArrayList = new ArrayList<>();
            belongsToArrayList = program.getBelongsToArrayList();
            belongsToArrayList.add(newBelongsTo);
            program.setBelongsToArrayList(belongsToArrayList);

            myListView.getItems().clear();
            myListView.getItems().addAll(program.getCurrentLandlordRooms());

            clearRoomInput();
        }
        else if (roomExists(buildingIDChoice.getValue(),roomNr)||!buildingIsFromLandlord(buildingIDChoice.getValue())){
            registerRoomInfo.setText("This room already exists!");
            clearRoomInput();
        }
    }
    else if (emptyFieldsRoom()){

        registerRoomInfo.setText("Please fill in every field!");
        }}
    public boolean roomExists(String buildingID,int roomNr){
        for(Building b : program.getCurrentLandlordBuildings()) {
            if (b.getBuildingID().equals(buildingID)) {
                for (Room r : program.getRoomsFromBuilding(b)) {
                    if ((r.getRoomNR() == roomNr)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void changeBuilding() {
        if(!Address.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("address", Address.getText(), currentBuilding.getBuildingID());
        }
        if(!City.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("city", City.getText(), currentBuilding.getBuildingID());
        }
        if(!Country.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("country", Country.getText(), currentBuilding.getBuildingID());
        }
        if(!Zip.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("zip", Zip.getText(), currentBuilding.getBuildingID());
        }
        program.setBuildings(DBBuilding.databaseReadBuilding());

        myListViewBuilding.getItems().clear();
        myListViewBuilding.getItems().addAll(program.getAppliancesStudent());

        clearInputBuildingChange();
    }

    public void changeRoom(){
        if(!RoomNr.getText().equals("")){
            DBRoom.changeRoomFromDatabase("roomNr", RoomNr.getText(), currentRoom.getRoomID());
        }
        if(buildingIDChoice.getValue() != null){
            DBRoom.changeRoomFromDatabase("buildingID", buildingIDChoice.getValue(), currentRoom.getRoomID());
        }
        if(!characteristics.getText().equals("")){
            DBRoom.changeRoomFromDatabase("characteristics", characteristics.getText(), currentRoom.getRoomID());
        }
        program.setRooms(DBRoom.databaseReadRoom());

        myListView.getItems().clear();
        myListView.getItems().addAll(program.getCurrentLandlordRooms());

        clearRoomInput();
    }

    public void clearBuildingInput(){
        addressInput.setText("");
        countryInput.setText("");
        cityInput.setText("");
        zipInput.setText("");
    }

    public void clearRoomInput(){
        roomNrInput.setText("");
        buildingIDChoice.setValue("");
        characteristicsInput.setText("");
    }

    public boolean emptyFieldsRoom(){
        int roomnr = Integer.parseInt("0"+ roomNrInput.getText());
        if((roomnr== 0)||(buildingIDChoice.getValue().equals(""))|| (characteristicsInput.getText().equals(""))) {
            return true;
        }
        return false;
    }

    public boolean emptyFieldsBuilding(){
        if ((addressInput.getText().equals(""))||(countryInput.getText().equals(""))||(cityInput.getText().equals(""))||(zipInput.getText().equals(""))){
            return true;
        }
        return false;
    }

    public boolean buildingIsFromLandlord(String buildingID){
        for(Building b : program.getCurrentLandlordBuildings()){
            if(b.getBuildingID().equals(buildingID)){
                return true;
            }
        }
        return false;
    }
}




