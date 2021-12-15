package gui;

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
import javafx.stage.Stage;
import logic.ConservationApp;
import logic.Contract;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessagesController implements Initializable {

    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Scene scene;
    private Stage stage;

    private ArrayList<Contract> contracts = program.getContracts();

    private Contract currentContract;

    @FXML
    private Label landlordID;
    @FXML
    private Label roomID;
    @FXML
    private Label duration;
    @FXML
    private Label startDate;
    @FXML
    private Label status;
    @FXML
    private Label contractInfo;

    public void accept() {
        if(currentContract.getStatus().equals("declined")){

        }

        if(currentContract.getStatus().equals("accepted")){

        }

        if(currentContract.getStatus().equals("pending")){

        }
    }

    public void decline() {

        if(currentContract.getStatus().equals("declined")){
            contractInfo.setText("The contract is already declined!");
        }

        if(currentContract.getStatus().equals("accepted")){
            contractInfo.setText("The contract has already been accepted!");
        }

        if(currentContract.getStatus().equals("pending")){
            contractInfo.setText("The contract is successfully declined!");

        }

    }

    public void backToStudentMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Contract contractStudent = null;

        for(Contract newContract: program.getContracts()){
            if(newContract.getStudentID().equals(program.getCurrentStudent().getStudentID())){
                contractStudent = newContract;
                currentContract = newContract;
            }
        }

        if(!contractStudent.equals(null)&&contractStudent.getStatus().equals("pending")){
            contractInfo.setText("Pending contract with contract nr. "+contractStudent.getContractNr());
            landlordID.setText(contractStudent.getLandlordID());
            roomID.setText(contractStudent.getcontractRoomID());
            duration.setText(""+contractStudent.getContractDuration()+" jaar");
            startDate.setText(contractStudent.getStartDate());
            status.setText(contractStudent.getStatus());
        }

        if(!contractStudent.equals(null)&&contractStudent.getStatus().equals("accepted")){
            contractInfo.setText("Accepted contract with contract nr. "+contractStudent.getContractNr());
            landlordID.setText(contractStudent.getLandlordID());
            roomID.setText(contractStudent.getcontractRoomID());
            duration.setText(""+contractStudent.getContractDuration()+" jaar");
            startDate.setText(contractStudent.getStartDate());
            status.setText(contractStudent.getStatus());
        }

        if(!contractStudent.equals(null)&&contractStudent.getStatus().equals("declined")){
            contractInfo.setText("Declined contract with contract nr. "+contractStudent.getContractNr());
            landlordID.setText(contractStudent.getLandlordID());
            roomID.setText(contractStudent.getcontractRoomID());
            duration.setText(""+contractStudent.getContractDuration()+" jaar");
            startDate.setText(contractStudent.getStartDate());
            status.setText(contractStudent.getStatus());
        }



        }
    }

