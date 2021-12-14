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
import logic.ConservationApp;
import logic.MonthlyConsumption;
import logic.Registers;
import logic.Room;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("Water");
        for(int i = 0; i<water.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), water.get(i)));
        }
        System.out.println(water);


        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        series2.setName("Electricity");
        for(int i = 0; i<electricity.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), electricity.get(i)));
        }
        System.out.println(electricity);


        XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
        series3.setName("Gas");
        for(int i = 0; i<gas.size(); i++){
            series1.getData().add(new XYChart.Data<String, Integer>(roomIDs.get(i), gas.get(i)));
        }
        System.out.println(gas);
        System.out.println(roomIDs);

        barChart.getData().addAll(series1, series2, series3);


    }

}
