package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;
import logic.*;
import database.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddContractController  {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void backToLandlordMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    private TextField studentIDInput;
    @FXML
    private TextField roomIDInput;
    @FXML
    private DatePicker startDateInput;
    @FXML
    private TextField durationInput;
    @FXML
    private Label contractNr;
    @FXML
    private Label contractAdd;

    public void addContract(){
        String studentID, roomID, startDate;
        int duration;
        LocalDate ld = startDateInput.getValue();
        startDate = ld.getDayOfMonth()+ "/" +ld.getMonthValue()+ "/" +ld.getYear();
        duration = Integer.parseInt("0" + durationInput);
        studentID = studentIDInput.getText();
        roomID = roomIDInput.getText();

        if(!contractExists(studentID, roomID)&&roomExists(roomID)&&!emptyField()&&studentExists(studentID)){
                int contractNr = (int) Math.floor(Math.random() * (999 - 100 + 1) + 100);
                String contractNrString = "" + contractNr;
                Contract newContract = new Contract(studentID, program.getCurrentLandlord().getLandlordID(), contractNrString, startDate, duration, "pending", roomID);
                DBContract.addContractToDatabase(newContract);

                ArrayList<Contract> contracts = new ArrayList<>();
                contracts = program.getContracts();
                contracts.add(newContract);
                program.setContracts(contracts);

                contractAdd.setText("Contract has been drawn up and sent to student!");
                this.contractNr.setText("The contractNr is " + contractNrString);

                clearContractInput();
            }
        else if(contractExists(studentID, roomID)){
            contractAdd.setText("It's not possible to make 2 contracts for the same student/room!");
            this.contractNr.setText("Please try again");
            clearContractInput();
        }
        else if(!roomExists(roomID)){
            contractAdd.setText("This room doesn't exist");
            this.contractNr.setText("Please try again");
            clearContractInput();
        }
        else if(emptyField()){
            contractAdd.setText("");
            this.contractNr.setText("All fields are required");
        }
        else if(!studentExists(studentID)){
            contractAdd.setText("This student doesn't exist");
            this.contractNr.setText("Please try again");
            clearContractInput();
        }
    }
    public boolean contractExists(String studentID, String roomID){
        for(Contract newContract: program.getContracts()){
            if(newContract.getStudentID().equals(studentID)||newContract.getcontractRoomID().equals(roomID)){
                return true;
            }
        }
        return false;
    }
    public boolean roomExists(String roomID){
        for(Room newRoom: program.getCurrentLandlordRooms()){
            if(newRoom.getRoomID().equals(roomID)){
                return true;
            }
        }
        return false;
    }
    public boolean emptyField(){
        if((studentIDInput.getText().equals(""))||(startDateInput == null)||(roomIDInput.getText().equals(""))||(durationInput.getText().equals(""))){
            return true;
        }
        return false;
    }
    public boolean studentExists(String studentID){
        for(Student newStudent: program.getStudents()){
            if(newStudent.getStudentID().equals(studentID)){
                return true;
            }
        }
        return false;
    }

    public void clearContractInput(){
        studentIDInput.setText("");
        roomIDInput.setText("");
        durationInput.setText("");
        startDateInput.getEditor().clear();
    }
}
