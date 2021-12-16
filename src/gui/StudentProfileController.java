package gui;

import database.DBStudent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {
    ConservationApp program = ConservationApp.getInstance();
    private Parent root;
    private Scene scene;
    private Stage stage;

    public void backToStudentMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField telephoneNr;
    @FXML
    private TextField password;
    @FXML
    private Label studentNumber;
    @FXML
    private Label roomMessage;
    @FXML
    private Label address;

    public void changeStudent(){
        if(!firstName.getText().equals("")){
            DBStudent.changeStudentFromDatabase("firstname", firstName.getText(),program.getCurrentStudent().getStudentID());
        }
        if(!lastName.getText().equals("")){
            DBStudent.changeStudentFromDatabase("lastname", lastName.getText(),program.getCurrentStudent().getStudentID());
        }
        if(!email.getText().equals("")){
            DBStudent.changeStudentFromDatabase("email", email.getText(),program.getCurrentStudent().getStudentID());
        }
        if(!telephoneNr.getText().equals("")){
            DBStudent.changeStudentFromDatabase("telephoneNr", telephoneNr.getText(), program.getCurrentStudent().getStudentID());
        }
        if(!password.getText().equals("")){
            DBStudent.changeStudentFromDatabase("password", password.getText(),program.getCurrentStudent().getStudentID());
        }

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
                buildingAdress = newBuilding.getAddress();
            }
        }
        return buildingAdress;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            firstName.setPromptText(program.getCurrentStudent().getFirstName());
            lastName.setPromptText(program.getCurrentStudent().getLastName());
            email.setPromptText(program.getCurrentStudent().getEmail());
            telephoneNr.setPromptText(program.getCurrentStudent().getTelephoneNr());
            password.setPromptText(program.getCurrentStudent().getPassword());
            studentNumber.setText("Student number: "+program.getCurrentStudent().getStudentID());

        String roomID = searchRoomStudent(program.getCurrentStudent());
        String buildingID = searchBuildingIDStudent(roomID);
        int roomNr = searchRoomNrStudent(roomID);
        String buildingAddress = searchBuildingAdressStudent(buildingID);

        String roomMessageString = "Room number: "+roomNr;
        roomMessage.setText(roomMessageString);
        String addressString = "Addres: "+buildingAddress;
        address.setText(addressString);
    }

}
