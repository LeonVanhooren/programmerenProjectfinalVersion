package gui;

import database.DBContract;
import database.DBLease;
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
import logic.Lease;


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
        if(program.getCurrentContract().getStatus().equals("declined")){
            contractInfo.setText("The contract has already been accepted!");
        }

        if(program.getCurrentContract().getStatus().equals("accepted")){
            contractInfo.setText("The contract is already accepted!");
        }

        if(program.getCurrentContract().getStatus().equals("pending")){
            contractInfo.setText("The contract is successfully accepted!");
            DBContract.changeContractFromDatabase("status", "accepted", program.getCurrentContract().getContractNr());
            program.getCurrentContract().setStatus("accepted");
            status.setText("accepted");

            Lease newLease = new Lease(program.getCurrentContract().getStudentID(), program.getCurrentContract().getcontractRoomID());
            DBLease.addLeaseToDatabase(newLease);

            ArrayList<Lease> leases = program.getLeases();
            leases.add(newLease);
            program.setLeases(leases);
        }
    }

    public void decline() {

        if(program.getCurrentContract().getStatus().equals("declined")){
            contractInfo.setText("The contract is already declined!");
        }

        if(program.getCurrentContract().getStatus().equals("accepted")){
            contractInfo.setText("The contract has already been accepted!");
        }

        if(program.getCurrentContract().getStatus().equals("pending")){
            contractInfo.setText("The contract is successfully declined!");
            DBContract.changeContractFromDatabase("status", "declined", program.getCurrentContract().getContractNr());
            program.getCurrentContract().setStatus("declined");
            status.setText("declined");
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
        for(Contract newContract: program.getContracts()){
            if(newContract.getStudentID().equals(program.getCurrentStudent().getStudentID())){
                if(newContract.getStatus().equals("pending")){
                    
                    program.setCurrentContract(newContract);

                    contractInfo.setText("Pending contract with contract nr. "+newContract.getContractNr());
                    landlordID.setText(newContract.getLandlordID());
                    roomID.setText(newContract.getcontractRoomID());
                    duration.setText(""+newContract.getContractDuration()+" jaar");
                    startDate.setText(newContract.getStartDate());
                    status.setText(newContract.getStatus());
                }

                if(newContract.getStatus().equals("accepted")){
                    contractInfo.setText("Accepted contract with contract nr. "+newContract.getContractNr());
                    landlordID.setText(newContract.getLandlordID());
                    roomID.setText(newContract.getcontractRoomID());
                    duration.setText(""+newContract.getContractDuration()+" jaar");
                    startDate.setText(newContract.getStartDate());
                    status.setText(newContract.getStatus());
                }

                if(newContract.getStatus().equals("declined")){
                    contractInfo.setText("Declined contract with contract nr. "+newContract.getContractNr());
                    landlordID.setText(newContract.getLandlordID());
                    roomID.setText(newContract.getcontractRoomID());
                    duration.setText(""+newContract.getContractDuration()+" jaar");
                    startDate.setText(newContract.getStartDate());
                    status.setText(newContract.getStatus());
                }
            }
        }
    }
}

