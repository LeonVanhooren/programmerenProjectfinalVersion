package gui;

import database.DBActions;
import database.DBAppliance;
import database.DBContains;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.stage.Stage;
import logic.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ApplianceMenuController implements Initializable {
    private ConservationApp program = ConservationApp.getInstance();
    private Stage stage;
    private Parent root;
    private Scene scene;

    private String currentApplianceID;

    public void backToStudentMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentMenu.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    private TextField QRCodeTF;
    @FXML
    private TextField applianceIDTF;
    @FXML
    private TextField consumptionTF;
    @FXML
    private Label addApplianceLabel;
    @FXML
    private Label RemoveApplianceLabel;
    @FXML
    private Label ChangeApplianceLabel;
    @FXML
    private Label applianceID;
    @FXML
    private TextField applianceNameTF;


    public void addAppliance(ActionEvent event) {
        String consumption, QRCode, applianceName;
        int applianceIDint;

        applianceIDint = (int) Math.floor(Math.random() * (999 - 100 + 1) + 999);
        String applianceIDString = "1" + applianceIDint;
        consumption = consumptionTF.getText();
        QRCode = QRCodeTF.getText();
        applianceName = applianceNameTF.getText();

        if (appliancePresent(applianceName) == true) {
            setAddApplianceStatus("The database already contains this appliance!");
        } else {
            Appliance newAppliance = new Appliance(applianceIDString, consumption, choiceBoxAdd1.getValue(), QRCode, applianceName, choiceBoxAdd.getValue());
            applianceID.setText("The appliance ID is: " + applianceIDString);

            DBAppliance.addApplianceToDatabase(newAppliance);
            ArrayList<Appliance> appliances = program.getAppliances();
            appliances.add(newAppliance);
            program.setAppliances(appliances);

            Contains newContains = new Contains(searchRoomID(program.getCurrentStudent()), applianceIDString);
            DBContains.addContainsToDatabase(newContains);
            ArrayList<Contains> containsArrayList = program.getContainsArrayList();
            containsArrayList.add(newContains);
            program.setContainsArrayList(containsArrayList);

            this.appliances.add(newAppliance);
            myListView.getItems().clear();
            myListView.getItems().addAll(this.appliances);
            clearInput();


            setAddApplianceStatus("The appliance is added to the database!");

        }


    }


    public void refresh() {
        myListView.getItems().clear();
        myListView.getItems().addAll(appliances);
    }
    public void clearInput(){
        applianceNameTF.setText("");
        consumptionTF.setText("");
        choiceBoxAdd1.setValue("");
        QRCodeTF.setText("");
        choiceBoxAdd.setValue("");
    }
    public void clearInputChange(){
        applianceNameChange.setText("");
        consumptionChange.setText("");
        choiceBoxChange1.setValue("");
        QRCodeChange.setText("");
        choiceBoxChange.setValue("");
    }

    @FXML
    private TextField applianceNameChange;
    @FXML
    private TextField consumptionChange;
    @FXML
    private TextField QRCodeChange;

    public void changeAppliance(ActionEvent event) {
        if (!applianceNameChange.getText().equals("")) {
            DBAppliance.changeApplianceFromDatabase("applianceName", applianceNameChange.getText(), currentAppliance.getApplianceID());
        }
        if (!consumptionChange.getText().equals("")) {
            DBAppliance.changeApplianceFromDatabase("consumption", consumptionChange.getText(), currentAppliance.getApplianceID());
        }
        if (choiceBoxChange1.getValue() != null) {
            DBAppliance.changeApplianceFromDatabase("efficiency", choiceBoxChange1.getValue(), currentAppliance.getApplianceID());
        }
        if (!QRCodeChange.getText().equals("")) {
            DBAppliance.changeApplianceFromDatabase("qrCode", QRCodeChange.getText(), currentAppliance.getApplianceID());
        }
        if (choiceBoxChange.getValue() != null) {
            DBAppliance.changeApplianceFromDatabase("applianceKind", choiceBoxChange.getValue(), currentAppliance.getApplianceID());
        }

        program.setAppliances(DBAppliance.databaseReadAppliance());

        myListView.getItems().clear();
        myListView.getItems().addAll(program.getAppliancesStudent());
        clearInputChange();

    }

    public Appliance searchApplianceChange(String applianceID) {
        ArrayList<Appliance> appliances = DBAppliance.databaseReadAppliance();
        Appliance appliance1 = null;
        for (Appliance newAppliance : appliances) {
            if (newAppliance.getApplianceID().equals(applianceID)) {
                appliance1 = newAppliance;
            }
        }
        return appliance1;
    }

    @FXML
    private ListView myListView;


    public String searchRoomID(Student student) {
        String output = null;
        for (Lease newLease : program.getLeases()) {
            if (newLease.getStudentID().equals(student.getStudentID())) {
                output = newLease.getRoomID();
            }
        }
        return output;
    }


    public String[] getApplianceIDs(String roomID) {
        ArrayList<String> output1 = new ArrayList<>();
        for (Contains newContains : program.getContainsArrayList()) {
            if (newContains.getRoomID().equals(roomID)) {
                output1.add(newContains.getApplianceID());
            }
        }
        String[] output = new String[output1.size()];
        for (int j = 0; j < output1.size(); j++) {
            output[j] = output1.get(j);
        }
        return output;
    }

    @FXML
    private Label applianceIDChange;
    @FXML
    private ChoiceBox<String> choiceBoxAdd1;
    @FXML
    private ChoiceBox<String> choiceBoxChange1;
    @FXML
    private ChoiceBox<String> choiceBoxAdd;
    @FXML
    private ChoiceBox<String> choiceBoxChange;

    private String[] choices1 = {"A", "B", "C", "D", "E", "F", "G"};

    private String currentChoiceAdd1;
    private String currentChoiceChange1;

    public void getCurrentAdd1(ActionEvent event){
        currentChoiceAdd1 = choiceBoxAdd1.getValue();
    }
    public void getCurrentChange1(ActionEvent event){
        currentChoiceChange1 = choiceBoxChange1.getValue();
    }

    private String[] choices = {"Electricity", "Water", "Gas"};

    private String currentChoiceAdd;
    private String currentChoiceChange;

    public void getCurrentAdd(ActionEvent event) {
        currentChoiceAdd = choiceBoxAdd.getValue();
    }

    public void getCurrentChange(ActionEvent event) {
        currentChoiceChange = choiceBoxChange.getValue();
    }

    private Appliance currentAppliance;


    private ArrayList<Appliance> appliances = program.getAppliancesStudent();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxAdd.getItems().addAll(choices);
        choiceBoxAdd.setOnAction(this::getCurrentAdd);
        choiceBoxChange.getItems().addAll(choices);
        choiceBoxChange.setOnAction(this::getCurrentChange);
        choiceBoxAdd1.getItems().addAll(choices1);
        choiceBoxAdd1.setOnAction(this::getCurrentAdd1);
        choiceBoxChange1.getItems().addAll(choices1);
        choiceBoxChange1.setOnAction(this::getCurrentChange1);
        myListView.getItems().addAll(program.getAppliancesStudent());
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Appliance>() {
            @Override
            public void changed(ObservableValue<? extends Appliance> observableValue, Appliance appliance, Appliance t1) {
                    currentAppliance = (Appliance) myListView.getSelectionModel().getSelectedItem();
            }
        });
    }


    public Appliance searchAppliance(String applianceID, Student student) {
        Appliance output = null;
        for (Appliance newAppliance : program.getAppliances()) {
            if (newAppliance.getApplianceID().equals(applianceID)) {
                output = newAppliance;
            }
        }
        return output;
    }


    //we moeten een appliance id number generator doen en een extra vakje voor appliance naam zodat meerdere laptops kunnen toegevoegd worden!!!!!!!!

    public boolean appliancePresent(String applianceName) {
        for (Appliance newAppliance : program.getAppliancesStudent()) {
            if (newAppliance.getApplianceName().equals(applianceName)) {
                return true;
            }
        }
        return false;
    }


    public void setAddApplianceStatus(String output) {
        addApplianceLabel.setText(output);

    }

    public void setRemoveApplianceStatus(String output) {
        RemoveApplianceLabel.setText(output);

    }

    public void setChangeApplianceStatus(String output) {
        ChangeApplianceLabel.setText(output);

    }
    @FXML
    private TextField applianceRemove;

    public void removeListItem(ActionEvent event) {

        final int selectedIdx = myListView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            Appliance itemToRemove = (Appliance) myListView.getSelectionModel().getSelectedItem();

            final int newSelectedIdx =
                    (selectedIdx == myListView.getItems().size() - 1)
                            ? selectedIdx - 1
                            : selectedIdx;

            ArrayList<Appliance> appliances = program.getAppliances();
            appliances.remove(itemToRemove);
            program.setAppliances(appliances);

            Contains newContains = new Contains(searchRoomID(program.getCurrentStudent()), itemToRemove.getApplianceID());

            ArrayList<Contains> containsArrayList = program.getContainsArrayList();
            containsArrayList.remove(newContains);
            program.setContainsArrayList(containsArrayList);

            DBContains.removeContainsFromDatabase(newContains);

            DBAppliance.removeApplianceFromDatabase(itemToRemove);

            myListView.getItems().remove(selectedIdx);
            myListView.getSelectionModel().select(newSelectedIdx);

        }
        myListView.refresh();
    }

    public void goToSite(ActionEvent event) throws IOException {
        program.setCurrentSite(QRCodeTF.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WebView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appliance site");
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void removeAppliance(){
        Appliance currentApplianceRemove = currentAppliance;
        DBAppliance.removeApplianceFromDatabase(currentAppliance);
        appliances.remove(currentApplianceRemove);

        program.setAppliances(DBAppliance.databaseReadAppliance());

        myListView.getItems().clear();
        myListView.getItems().addAll(program.getAppliancesStudent());
        program.setAppliances(appliances);

    }
}
