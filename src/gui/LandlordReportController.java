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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LandlordReportController implements Initializable {
    ConservationApp program = ConservationApp.getInstance();
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ListView<Registers> myListView;
    @FXML
    private ListView<MonthlyConsumption> myListViewMonthlyConsumption;


    public void backToLandlordMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    private ArrayList<Registers> registersLandlord;
    private ArrayList<MonthlyConsumption> monthlyConsumptionsLandlord;
    private ArrayList<Integer> water;
    private ArrayList<Integer> electricity;
    private ArrayList<Integer> gas;
    private ArrayList<String> roomIDs;

    @FXML
    private DatePicker datePicker;
    @FXML
    private Label monthInfo;

    public String toMonth(String date){
        String[] outputArray = date.split("/");
        return outputArray[1];
    }
    public String toYear(String date){
        String[] outputArray = date.split("/");
        return outputArray[2];
    }
    XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
    XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
    XYChart.Series<String, Integer> series3 = new XYChart.Series<>();

    public void showBarChart(){
        LocalDate date = datePicker.getValue();
        String dateString = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();

        registersLandlord = program.getRegistersLandlord(dateString);
        monthlyConsumptionsLandlord = monthlyConsumptionsLandlord();
        water = getWaterFromRooms();
        electricity = getElectricityFromRooms();
        gas = getGasFromRooms();
        roomIDs = getRoomIDs();
        buildingIDs = getBuildingIDs();

        for(int i = 0; i<water.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), water.get(i)));
        }

        for(int i = 0; i<electricity.size(); i++){
            series2.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), electricity.get(i)));
        }

        for(int i = 0; i<gas.size(); i++){
            series3.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), gas.get(i)));
        }

        barChart.getData().clear();
        barChart.getData().addAll(series1, series2, series3);

        switch (toMonth(dateString)){
            case "01":
                monthInfo.setText("January of the year "+toYear(dateString));
                break;
            case "02":
                monthInfo.setText("February of the year "+toYear(dateString));
                break;
            case "03":
                monthInfo.setText("March of the year "+toYear(dateString));
                break;
            case "04":
                monthInfo.setText("April of the year "+toYear(dateString));
                break;
            case "05":
                monthInfo.setText("May of the year "+toYear(dateString));
                break;
            case "06":
                monthInfo.setText("June of the year "+toYear(dateString));
                break;
            case "07":
                monthInfo.setText("July of the year "+toYear(dateString));
                break;
            case "08":
                monthInfo.setText("August of the year "+toYear(dateString));
                break;
            case "09":
                monthInfo.setText("September of the year "+toYear(dateString));
                break;
            case "10":
                monthInfo.setText("October of the year "+toYear(dateString));
                break;
            case "11":
                monthInfo.setText("November of the year "+toYear(dateString));
                break;
            case "12":
                monthInfo.setText("December of the year "+toYear(dateString));
                break;
        }

    }

    private ArrayList<Integer> getWaterFromRooms(){
        ArrayList<Integer> water =new ArrayList<>();
        for(MonthlyConsumption newMonthlyConsumption:monthlyConsumptionsLandlord){
            water.add(Integer.parseInt(newMonthlyConsumption.getWater()));
        }
        return water;
    }

    private ArrayList<Integer> getElectricityFromRooms(){
        ArrayList<Integer> electricity =new ArrayList<>();
        for(MonthlyConsumption newMonthlyConsumption:monthlyConsumptionsLandlord){
            electricity.add(Integer.parseInt(newMonthlyConsumption.getElectricity()));
        }
        return electricity;
    }

    private ArrayList<Integer> getGasFromRooms(){
        ArrayList<Integer> gas =new ArrayList<>();
        for(MonthlyConsumption newMonthlyConsumption:monthlyConsumptionsLandlord){
            gas.add(Integer.parseInt(newMonthlyConsumption.getGas()));
        }
        return gas;
    }

    @FXML
    private BarChart<String, Integer> barChart;



    public ArrayList<MonthlyConsumption>  monthlyConsumptionsLandlord(){
        ArrayList<MonthlyConsumption> monthlyConsumptions = new ArrayList<>();
        for(Registers newRegister: registersLandlord){
            for(MonthlyConsumption newMonthlyConsumption: program.getMonthlyConsumptions()) {
                if (newRegister.getRegistrationID().equals(newMonthlyConsumption.getRegistrationID())){
                    monthlyConsumptions.add(newMonthlyConsumption);
                }
            }
        }
        return monthlyConsumptions;
    }

    public ArrayList<String> getRoomIDs(){
        ArrayList<String> roomIDs = new ArrayList<>();
        for(Registers registers:registersLandlord){
            roomIDs.add(registers.getRoomID());
        }
        return roomIDs;
    }
    ArrayList<String> buildingIDs;

    public ArrayList<String> getBuildingIDs(){
        ArrayList<String> roomIDs = getRoomIDs();
        ArrayList<String> buildingIDs = new ArrayList<>();
        for(int i = 0; i< getRoomIDs().size(); i++) {
            for (BelongsTo newBelongsTo : program.getBelongsToArrayList()) {
                if (newBelongsTo.getRoomID().equals(roomIDs.get(i))){
                    buildingIDs.add(newBelongsTo.getBuildingID());
                }
            }
        }
        return buildingIDs;
    }

    public void clearBarChart(){
        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        barChart.getData().clear();
    }

    @FXML
    private BarChart<String, Integer> barChart1;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        series1.setName("Water (m³)");
        series2.setName("Electricity (kWh)");
        series3.setName("Gas (m³)");
    }

}
