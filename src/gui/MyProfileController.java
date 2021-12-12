package gui;

import database.DBStudent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class
MyProfileController {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label studentNumber;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private Label roomMessage;


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

    public void change(){


    }

    public void showInfo(){

    }

    public void back(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StudentMenu.fxml"));
        Parent view = loader.load();



        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(view);
        stage.setScene(scene);
    }
}
