package gui;

import database.DBMonthlyConsumption;
import database.DBRegisters;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;
import logic.MonthlyConsumption;
import logic.Registers;
import logic.Room;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterEnergyConsumptionController implements Initializable {
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
        String date = ld.getDayOfMonth()+"/"+ld.getMonthValue()+"/"+ld.getYear();
        System.out.println(date);

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

    @FXML
    private ListView<Registers> myListView;
    @FXML
    private ChoiceBox roomChoiceBox;


    public void showConsumption(){
        LocalDate date = datePicker.getValue();
        String dateString = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
        ArrayList<Integer> output = program.getMonthlyConsumptionFromRoom(dateString, (Room) roomChoiceBox.getValue());
        electricity.setText(String.valueOf(output.get(0)));
        gas.setText(String.valueOf(output.get(1)));
        water.setText(String.valueOf(output.get(2)));
    }

    private Registers currentRegister;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListView.getItems().addAll(program.getRegisters());
        roomChoiceBox.getItems().addAll(program.getCurrentLandlordRooms());
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Registers>() {
            @Override
            public void changed(ObservableValue<? extends Registers> observableValue, Registers registers, Registers t1) {
                currentRegister = myListView.getSelectionModel().getSelectedItem();
            }


    });

    }





}
