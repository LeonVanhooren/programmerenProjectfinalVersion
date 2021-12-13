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

    public String[] buildingIDsLandlord(String landlordID){
        ArrayList<String> output = new ArrayList<>();

        for(Ownership newOwnership: program.getOwnerships()){
            if(newOwnership.getLandlordID().equals(landlordID)){
                output.add(newOwnership.getBuildingID());
            }
        }

        String[] outputString = new String[output.size()];
        for(int i = 0; i< output.size(); i++){
            outputString[i]=output.get(i);
        }
        return outputString;
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

    public void refreshBuildingListView(){
        myListViewBuilding.getItems().clear();
        myListViewBuilding.getItems().addAll(buildingIDsLandlord(program.getCurrentLandlord().getLandlordID()));
    }

    public void refreshRoomListView(){
        myListView.getItems().clear();
        myListView.getItems().addAll(roomIDsLandlord(buildingIDsLandlord(program.getCurrentLandlord().getLandlordID())));
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
    private Label buildinginfo;

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
        }
        else{
            buildinginfo.setText("The database already contains this building!");
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

    @FXML
    private TextField roomNrInput;
    @FXML
    private TextField buildingIDInput;
    @FXML
    private Label registerRoomInfo;
    @FXML
    private Label roomIDT;

    public void addRoomButton(){
        String buildingID;
        int roomNr;
        roomNr = Integer.parseInt(roomNrInput.getText());
        buildingID = buildingIDInput.getText();
        if(!roomExists(buildingID, roomNr)){
            String roomIDString = buildingID +"." + roomNr;
            Room newRoom = new Room("blabla", buildingID, roomNr, roomIDString);
            DBRoom.addRoomToDatabase(newRoom);
            registerRoomInfo.setText("Student room successfully added");
            roomIDT.setText("The roomID is " + roomIDString + ", remember this well!");
        }
        else{
            registerRoomInfo.setText("The database already contains this room!");
        }
    }
    public boolean roomExists(String buildingID,int roomNr){
        for (Room r : program.getRooms()){
            if((r.getBuildingID().equals(buildingID)) && (r.getRoomNR() == roomNr)){
                return true;
            }
        }
        return false;
    }

    public void changeBuilding(ActionEvent event) {
        if(!Address.getText().equals("")){
            DBBuilding.changeBuildingFromDatabase("adress", Address.getText(), currentBuilding);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListViewBuilding.getItems().addAll(buildingIDsLandlord(program.getCurrentLandlord().getLandlordID()));
        myListViewBuilding.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String currentBuilding = myListViewBuilding.getSelectionModel().getSelectedItem();
                countryInput.setPromptText(searchBuilding(currentBuilding, program.getCurrentLandlord()).getCity());

            }


        });

    }
    public Building searchBuilding(String buildingID, Landlord landlord){
        Building output = null;
        for(Building newBuilding: program.getBuildings()){
            if(newBuilding.getBuildingID().equals(buildingID)){
                output = newBuilding;
            }
        }
        return output;
    }
}




