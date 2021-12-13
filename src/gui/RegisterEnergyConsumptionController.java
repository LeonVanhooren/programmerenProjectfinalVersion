package gui;

import database.DBMonthlyConsumption;
import database.DBRegisters;
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
import logic.MonthlyConsumption;
import logic.Registers;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterEnergyConsumptionController {
    private ConservationApp program = ConservationApp.getInstance();

    private Parent root;
    private Scene scene;
    private Stage stage;


    public void landlordMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField roomID;
    @FXML
    private TextField electricity;
    @FXML
    private TextField water;
    @FXML
    private TextField gas;
    @FXML
    private Label consumptionInfo;


    public void addConsumption(ActionEvent event){
        LocalDate ld = datePicker.getValue();


        String time = ld.getYear()+""+ld.getMonthValue()+""+ld.getDayOfMonth()+"";
       Date date = new Date();



        String registrationID =""+Math.floor(Math.random()*(99999-91000+1)+91000);
        String roomIDString = roomID.getText();
        String electricityString = electricity.getText();
        String waterString = water.getText();
        String gasString = gas.getText();

        MonthlyConsumption newMonthlyConsumption = new MonthlyConsumption(registrationID, waterString, electricityString, gasString);
        Registers newRegisters = new Registers(date, registrationID, roomIDString);


        if(consumptionPresent(newRegisters)==false) {


            DBMonthlyConsumption.addMonthlyConsumptionToDatabase(newMonthlyConsumption);

            ArrayList<MonthlyConsumption> monthlyConsumptions = program.getMonthlyConsumptions();
            monthlyConsumptions.add(newMonthlyConsumption);
            program.setMonthlyConsumptions(monthlyConsumptions);


            DBRegisters.addRegistersToDatabase(newRegisters);

            ArrayList<Registers> registersArrayList = program.getRegisters();
            registersArrayList.add(newRegisters);
            program.setRegisters(registersArrayList);

            consumptionInfo.setText("Consumption is successfully added!");
        }
        else{
            consumptionInfo.setText("You already added consumption info for this room!");
        }
    }

    public boolean consumptionPresent(Registers registers){
        boolean output = false;
        for(Registers newRegisters:program.getRegisters()){
            if(newRegisters.getDate().equals(registers.getDate())&&newRegisters.getRoomID().equals(registers.getRoomID())){
                output = true;
            }
        }
        return output;
    }
}
