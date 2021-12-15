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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
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

    private ArrayList<Registers> registersLandlord  = program.getRegistersLandlord();
    private ArrayList<MonthlyConsumption> monthlyConsumptionsLandlord = monthlyConsumptionsLandlord();
    private ArrayList<Integer> water = getWaterFromRooms();
    private ArrayList<Integer> electricity = getElectricityFromRooms();
    private ArrayList<Integer> gas = getGasFromRooms();
    private ArrayList<String> roomIDs = getRoomIDs();

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
    ArrayList<String> buildingIDs = getBuildingIDs();

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

    private ArrayList<String> trimmedBuildingID;
    private ArrayList<Integer> waterBuildings;
    private ArrayList<Integer> electricityBuildings;
    private ArrayList<Integer> gasBuildings;

    public void setConsumptionPerBuilding(){
        /*ArrayList <String> buildingIDsLandlord = buildingIDs;
        ArrayList <Integer> waterBuilding = new ArrayList<>();
        ArrayList <Integer> electricityBuilding = new ArrayList<>();
        ArrayList <Integer> gasBuilding = new ArrayList<>();
        ArrayList <String> trimmedBuildingIDs = new ArrayList<>();

        trimmedBuildingIDs.add(buildingIDsLandlord.get(0));

        for(int i = 0; i<buildingIDsLandlord.size(); i++){
            for(int j =0; j<buildingIDsLandlord.size(); j++){
                if (trimmedBuildingIDs.get(i).equals(buildingIDsLandlord.get(i))) {
                    trimmedBuildingIDs.add(buildingIDsLandlord.get(j));
                    waterBuilding.set(i, waterBuilding.get(i)+water.get(j));
                    electricityBuilding.set(i, electricityBuilding.get(i)+ electricity.get(j));
                    gasBuilding.set(i, gasBuilding.get(i)+gas.get(j));
                }

            }
        }

        trimmedBuildingID = trimmedBuildingIDs;
        waterBuildings = waterBuilding;
        electricityBuildings = electricityBuilding;
        gasBuildings = gasBuilding;*/
    }
    
    @FXML
    private BarChart<String, Integer> barChart1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("Water (m³)");
        for(int i = 0; i<water.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), water.get(i)));
        }

        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        series2.setName("Electricity (kWh)");
        for(int i = 0; i<electricity.size(); i++){
            series2.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), electricity.get(i)));
        }

        XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
        series3.setName("Gas (kWh)");
        for(int i = 0; i<gas.size(); i++){
            series3.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), gas.get(i)));
        }

        barChart.getData().addAll(series1, series2, series3);

        XYChart.Series<String, Integer> series4 = new XYChart.Series<>();
        series4.setName("Water (m³)");
        for(int i = 0; i<buildingIDs.size(); i++){
            series4.getData().add(new XYChart.Data<String, Integer>(buildingIDs.get(i), water.get(i)));
        }
        XYChart.Series<String, Integer> series5 = new XYChart.Series<>();
        series5.setName("Electricity (kWh)");
        for(int i = 0; i<buildingIDs.size(); i++){
            series5.getData().add(new XYChart.Data<String, Integer>(buildingIDs.get(i), electricity.get(i)));
        }
        XYChart.Series<String, Integer> series6 = new XYChart.Series<>();
        series6.setName("Gas (kWh)");
        for(int i = 0; i<buildingIDs.size(); i++){
            series6.getData().add(new XYChart.Data<String, Integer>(buildingIDs.get(i), gas.get(i)));
        }

        barChart1.getData().addAll(series4, series5, series6);


    }

}
