package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.Action;
import logic.Appliance;
import logic.ConservationApp;
import logic.Room;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentReportController implements Initializable {
    ConservationApp program = ConservationApp.getInstance();
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private ListView appliances;
    @FXML
    private ListView consumption;
    @FXML
    private ListView actions;
    @FXML
    private ListView energySaved;
    @FXML
    private Label currentRoomInfo;
    @FXML
    private DatePicker datePickerAppliances;
    @FXML
    private DatePicker datePickerElectricity;
    @FXML
    private DatePicker datePickerGas;
    @FXML
    private DatePicker datePickerWater;
    @FXML
    private AreaChart areaChartElectricity;
    @FXML
    private AreaChart areaChartGas;
    @FXML
    private AreaChart areaChartWater;



    public void backToStudentMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StudentMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public ArrayList<String> applianceNames(){
        ArrayList<String> output = new ArrayList<>();
        for(Appliance newAppliance: program.getAppliancesStudent()){
            output.add(newAppliance.getApplianceName());
        }
        return output;
    }

    public ArrayList<String> applianceConsumptions(){
        ArrayList<String> output = new ArrayList<>();
        for(Appliance newAppliance: program.getAppliancesStudent()){
            switch(newAppliance.getApplianceKind()){
                case "Electricity":
                    output.add(newAppliance.getConsumption()+" kWh electricity");
                    break;
                case "Gas":
                    output.add(newAppliance.getConsumption()+" m³ gas");
                    break;
                case "Water":
                    output.add(newAppliance.getConsumption()+" m³ water");
                    break;
            }
        }
        return output;
    }

    public void showActionsAndEnergySaved(){
        actions.getItems().clear();
        energySaved.getItems().clear();
        LocalDate date =datePickerAppliances.getValue();
        String dateString = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
        Room currentRoom = program.getRoomCurrentStudent();
        String[] dateStringSplit = dateString.split("/");
        ArrayList<Appliance> appliances = program.getAppliancesFromRoom(currentRoom);
        ArrayList<Action> outputActions = program.getActionsFromRoom(dateStringSplit, appliances);
        ArrayList<String> outputEnergySaved = program.getSavesBysConservations(outputActions);

        actions.getItems().addAll(outputActions);
        energySaved.getItems().addAll(outputEnergySaved);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appliances.getItems().addAll(applianceNames());
        consumption.getItems().addAll(applianceConsumptions());


    }
}
