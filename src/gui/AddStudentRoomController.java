package gui;

import database.*;
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
import java.util.ArrayList;
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
    @FXML
    private TextField characteristics;
    @FXML
    private Label characteristicsLabel;


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



    public String[] roomIDsLandlord(String[] buildingIDs){
        ArrayList<String> output1 = new ArrayList<>();

        for(int i =0; i <buildingIDs.length;i++){
            for(BelongsTo newBelongsTo:program.getBelongsToArrayList()){
                if(buildingIDs[i].equals(newBelongsTo.getBuildingID())){
                    output1.add(newBelongsTo.getRoomID());
                }
            }
        }
        String[] output = new String[output1.size()];
        for (int j = 0; j< output1.size();j++) {
            output[j] = output1.get(j);
        }
        return output;
    }





    public void removeBuilding(){
        Building building = null;
        for(Building newBuilding: program.getBuildings()){
            if (newBuilding.getBuildingID().equals(currentBuilding)){
                building = newBuilding;
            }
        }
        ArrayList<Building> buildings = program.getBuildings();
        buildings.remove(building);
        program.setBuildings(buildings);

       DBBuilding.removeBuildingFromDatabase(currentBuilding);

    }

    @FXML
    private Label roomIDroom;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListViewBuilding.getItems().addAll(program.getBuildingIDsLandlord());
        myListViewBuilding.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentBuilding = myListViewBuilding.getSelectionModel().getSelectedItem();
                City.setPromptText(searchBuildingCity(currentBuilding));
                Country.setPromptText(searchBuildingCountry(currentBuilding));
                Zip.setPromptText(searchBuildingZip(currentBuilding));
                Address.setPromptText(searchBuildingAddressStudent(currentBuilding));
            }
            
        });
        myListView.getItems().addAll(program.getRoomIDsLandlord(program.getBuildingIDsLandlord()));

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentRoom = myListView.getSelectionModel().getSelectedItem();
                roomIDroom.setText(currentRoom);
                buildingIDRoom.setPromptText(searchBuildingIDStudent(currentRoom));
                RoomNr.setPromptText(""+searchRoomNrStudent(currentRoom));
                characteristics.setPromptText(searchCharacteristics(currentRoom));
            }


        });

    }
    public void refreshBuildingListView(){
        myListViewBuilding.getItems().clear();
        myListViewBuilding.getItems().addAll(program.getBuildingIDsLandlord());
    }
    public void refreshRoomListView(){
        myListView.getItems().clear();
        myListView.getItems().addAll(program.getRoomIDsLandlord(program.getBuildingIDsLandlord()));
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

    public String searchBuildingAddressStudent(String buildingID){
        String buildingAddress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAddress = newBuilding.getAddress();
            }
        }
        return buildingAddress;
    }

    public String searchBuildingCountry(String buildingID){
        String buildingAddress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAddress = newBuilding.getCountry();
            }
        }
        return buildingAddress;
    }

    public String searchBuildingCity(String buildingID){
        String buildingAddress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAddress = newBuilding.getCity();
            }
        }
        return buildingAddress;
    }

    public String searchBuildingZip(String buildingID){
        String buildingAddress = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                buildingAddress = newBuilding.getZip();
            }
        }
        return buildingAddress;
    }
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

            myListViewBuilding.getItems().clear();
            myListViewBuilding.getItems().addAll(program.getBuildingIDsLandlord());

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

    @FXML
    private TextField roomNrInput;
    @FXML
    private TextField buildingIDInput;
    @FXML
    private Label registerRoomInfo;
    @FXML
    private Label roomIDT;
    @FXML
    private TextField characteristicsInput;

    public void addRoomButton(){
        String buildingID, characteristics;
        int roomNr;
        roomNr = Integer.parseInt("0"+roomNrInput.getText());
        buildingID = buildingIDInput.getText();
        characteristics = characteristicsInput.getText();
        if(!emptyFieldsRoom()){

            if(!roomExists(buildingID, roomNr)&&buildingIsFromLandlord(buildingID)){
            String roomIDString = buildingID +"." + roomNrInput.getText();
            Room newRoom = new Room(roomNr, roomIDString, buildingID, characteristics);
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
            myListView.getItems().addAll(program.getRoomIDsLandlord(program.getBuildingIDsLandlord()));

            clearRoomInput();
        }
        else if (roomExists(buildingID,roomNr)||!buildingIsFromLandlord(buildingID)){
            registerRoomInfo.setText("This room already exists/this building doesn't exists!");
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
            DBBuilding.changeBuildingFromDatabase("address", Address.getText(), currentBuilding);
        }
        if(!City.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("city", City.getText(), currentBuilding);
        }
        if(!Country.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("country", Country.getText(), currentBuilding);
        }
        if(!Zip.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("zip", Zip.getText(), currentBuilding);
        }
    }
    public void changeRoom(){
        if(!RoomNr.getText().equals("")){
            DBRoom.changeRoomFromDatabase("roomNr", RoomNr.getText(), currentRoom);
        }
        if(!buildingIDRoom.getText().equals("")){
            DBRoom.changeRoomFromDatabase("buildingID", buildingIDRoom.getText(), currentRoom);
        }
        if(!characteristics.getText().equals("")){
            DBRoom.changeRoomFromDatabase("characteristics", characteristics.getText(), currentRoom);
        }
    }
    public void clearBuildingInput(){
        addressInput.setText("");
        countryInput.setText("");
        cityInput.setText("");
        zipInput.setText("");
    }
    public void clearRoomInput(){
        roomNrInput.setText("");
        buildingIDInput.setText("");
        characteristicsInput.setText("");
    }

    public boolean emptyFieldsRoom(){
        int roomnr = Integer.parseInt("0"+ roomNrInput.getText());
        if((roomnr== 0)||(buildingIDInput.getText().equals(""))|| (characteristicsInput.getText().equals(""))) {
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




