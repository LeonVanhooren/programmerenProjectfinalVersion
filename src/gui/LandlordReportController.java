package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.*;
import java.io.IOException;
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
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label monthInfo;
    @FXML
    private BarChart<String, Integer> barChart1;

    private ArrayList<Registers> registersLandlord;
    private ArrayList<MonthlyConsumption> monthlyConsumptionsLandlord;
    private ArrayList<Integer> water;
    private ArrayList<Integer> electricity;
    private ArrayList<Integer> gas;
    private ArrayList<String> roomIDs;


    public void backToLandlordMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("landlord menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }

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

    public void showBarChartLandlord(){
        barChart.getData().clear();
        barChart.getData().addAll(series1, series2, series3);

    }

    public void makeBarChart(){
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

        switch (toMonth(dateString)){
            case "01":
                monthInfo.setText(monthInfo.getText()+" "+"January of the year "+toYear(dateString));
                break;
            case "02":
                monthInfo.setText(monthInfo.getText()+" "+"February of the year "+toYear(dateString));
                break;
            case "03":
                monthInfo.setText(monthInfo.getText()+" "+"March of the year "+toYear(dateString));
                break;
            case "04":
                monthInfo.setText(monthInfo.getText()+" "+"April of the year "+toYear(dateString));
                break;
            case "05":
                monthInfo.setText(monthInfo.getText()+" "+"May of the year "+toYear(dateString));
                break;
            case "06":
                monthInfo.setText(monthInfo.getText()+" "+"June of the year "+toYear(dateString));
                break;
            case "07":
                monthInfo.setText(monthInfo.getText()+" "+"July of the year "+toYear(dateString));
                break;
            case "08":
                monthInfo.setText(monthInfo.getText()+" "+"August of the year "+toYear(dateString));
                break;
            case "09":
                monthInfo.setText(monthInfo.getText()+" "+"September of the year "+toYear(dateString));
                break;
            case "10":
                monthInfo.setText(monthInfo.getText()+" "+"October of the year "+toYear(dateString));
                break;
            case "11":
                monthInfo.setText(monthInfo.getText()+" "+"November of the year "+toYear(dateString));
                break;
            case "12":
                monthInfo.setText(monthInfo.getText()+" "+"December of the year "+toYear(dateString));
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
        monthInfo.setText("Months to compare: ");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        series1.setName("Water (m³)");
        series2.setName("Electricity (kWh)");
        series3.setName("Gas (m³)");
    }
}
