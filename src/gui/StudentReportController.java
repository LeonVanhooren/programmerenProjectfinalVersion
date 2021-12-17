package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.Action;
import logic.Appliance;
import logic.ConservationApp;
import logic.Room;

import java.io.IOException;
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
    private LineChart areaChartElectricity;
    @FXML
    private LineChart areaChartGas;
    @FXML
    private LineChart areaChartWater;

    private XYChart.Series series1Electricity = new XYChart.Series();
    private XYChart.Series series2Electricity = new XYChart.Series();
    private XYChart.Series series3Electricity = new XYChart.Series();

    @FXML
    private Label electricityInfo;

    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
    private XYChart.Series series3 = new XYChart.Series();

    @FXML
    private Label gasInfo;

    private XYChart.Series series1Water = new XYChart.Series();
    private XYChart.Series series2Water = new XYChart.Series();
    private XYChart.Series series3Water = new XYChart.Series();

    @FXML
    private Label waterInfo;




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
        currentRoomInfo.setText(currentRoomInfo.getText()+" "+program.getRoomCurrentStudent().getRoomID());
        appliances.getItems().addAll(applianceNames());
        consumption.getItems().addAll(applianceConsumptions());
        series1.setName("Monthly consumption");
        series2.setName("Monthly conservation");
        series3.setName("netto-consumption");
        series1Water.setName("Monthly consumption");
        series2Water.setName("Monthly conservation");
        series3Water.setName("netto-consumption");
        series1Electricity.setName("Monthly consumption");
        series2Electricity.setName("Monthly conservation");
        series3Electricity.setName("netto-consumption");


    }

    public void clearElectricityGraph(){
        series1Electricity.getData().clear();
        series2Electricity.getData().clear();
        series3Electricity.getData().clear();
        electricityInfo.setText("Months: ");
        areaChartElectricity.getData().clear();
    }
    public void clearGasGraph(){
        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        gasInfo.setText("Months: ");
        areaChartGas.getData().clear();
    }
    public void clearWaterGraph(){
        series1Water.getData().clear();
        series2Water.getData().clear();
        series3Water.getData().clear();
        waterInfo.setText("Months: ");
        areaChartWater.getData().clear();
    }

    public void makeGasGraph(){
        LocalDate dateLocal = datePickerGas.getValue();
        String date = dateLocal.getDayOfMonth()+"/"+dateLocal.getMonthValue()+"/"+dateLocal.getYear();

        ArrayList<Appliance> appliances = program.getAppliancesFromRoom(program.getRoomCurrentStudent());
        ArrayList<Integer> monthlyConsumptionRoom = program.getConsumptionOfAppliances(appliances);

        String[] dateSplit = date.split("/");
        ArrayList<Action> actions = program.getActionsFromRoom(dateSplit,appliances);
        ArrayList<Integer> monthlyConservation = program.getMonthlyConservation(actions);

        ArrayList<Integer> monthlyConsumptionReducted = program.getMonthlyConsumptionReducted(monthlyConsumptionRoom, monthlyConservation);

        series1.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], monthlyConsumptionRoom.get(1)));

        series2.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], -monthlyConservation.get(1)));

        series3.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], monthlyConsumptionReducted.get(1)));

        gasInfo.setText(gasInfo.getText()+dateSplit[1]+"/"+dateSplit[2]+" --> ");

    }

    @FXML
    private void showGas(){
        areaChartGas.getData().clear();
        areaChartGas.getData().addAll(series1, series2, series3);

    }


    public void makeWaterGraph(){
        LocalDate dateLocal = datePickerWater.getValue();
        String date = dateLocal.getDayOfMonth()+"/"+dateLocal.getMonthValue()+"/"+dateLocal.getYear();

        ArrayList<Appliance> appliances = program.getAppliancesFromRoom(program.getRoomCurrentStudent());
        ArrayList<Integer> monthlyConsumptionRoom = program.getConsumptionOfAppliances(appliances);

        String[] dateSplit = date.split("/");
        ArrayList<Action> actions = program.getActionsFromRoom(dateSplit,appliances);
        ArrayList<Integer> monthlyConservation = program.getMonthlyConservation(actions);

        ArrayList<Integer> monthlyConsumptionReducted = program.getMonthlyConsumptionReducted(monthlyConsumptionRoom, monthlyConservation);

        series1Water.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], monthlyConsumptionRoom.get(2)));

        series2Water.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], -monthlyConservation.get(2)));

        series3Water.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], monthlyConsumptionReducted.get(2)));

        waterInfo.setText(waterInfo.getText()+dateSplit[1]+"/"+dateSplit[2]+" --> ");

    }

    @FXML
    private void showWater(){
        areaChartWater.getData().clear();
        areaChartWater.getData().addAll(series1Water, series2Water, series3Water);

    }

    public void makeElectricityGraph(){
        LocalDate dateLocal = datePickerElectricity.getValue();
        String date = dateLocal.getDayOfMonth()+"/"+dateLocal.getMonthValue()+"/"+dateLocal.getYear();

        ArrayList<Appliance> appliances = program.getAppliancesFromRoom(program.getRoomCurrentStudent());
        ArrayList<Integer> monthlyConsumptionRoom = program.getConsumptionOfAppliances(appliances);

        String[] dateSplit = date.split("/");
        ArrayList<Action> actions = program.getActionsFromRoom(dateSplit,appliances);
        ArrayList<Integer> monthlyConservation = program.getMonthlyConservation(actions);

        ArrayList<Integer> monthlyConsumptionReducted = program.getMonthlyConsumptionReducted(monthlyConsumptionRoom, monthlyConservation);

        series1Electricity.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], monthlyConsumptionRoom.get(0)));

        series2Electricity.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], -monthlyConservation.get(0)));

        series3Electricity.getData().add(new XYChart.Data<String, Integer>(dateSplit[1]+"/"+dateSplit[2], monthlyConsumptionReducted.get(0)));

        electricityInfo.setText(electricityInfo.getText()+dateSplit[1]+"/"+dateSplit[2]+" --> ");

    }

    @FXML
    private void showElectricity(){
        areaChartElectricity.getData().clear();
        areaChartElectricity.getData().addAll(series1Electricity, series2Electricity, series3Electricity);

    }
}
