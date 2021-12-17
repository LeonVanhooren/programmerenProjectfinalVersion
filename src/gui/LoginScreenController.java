package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;
import logic.Landlord;
import logic.Student;

import java.io.IOException;

public class LoginScreenController {

    private ConservationApp program = ConservationApp.getInstance();

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private Label loginInfoStudent;
    @FXML
    private TextField studentUsernameInput;
    @FXML
    private PasswordField studentPasswordInput;
    @FXML
    private TextField landlordUsernameInput;
    @FXML
    private PasswordField landlordPasswordInput;
    @FXML
    private Label loginInfoLandlord;

    public void studentSignIn(ActionEvent event) throws IOException {
        String[] outputStudent = new String[2];

        outputStudent[0] = studentUsernameInput.getText();
        outputStudent[1] = studentPasswordInput.getText();
        if (!emptyfieldsStudent()){
        if(studentPresent(outputStudent[0],outputStudent[1] )==true){

            loginInfoStudent.setText("This matches an account in the DB!");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StudentMenu.fxml"));
            Parent view = loader.load();

            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Student menu");
            scene = new Scene(view);
            stage.setScene(scene);

        }
        else{
            loginInfoStudent.setText("This doesn't match an account in the DB!");
            studentUsernameInput.setText("");
            studentPasswordInput.setText("");

        }}
        else{loginInfoStudent.setText("Please fill in every field!");

    }}

    public boolean studentPresent(String studentID, String password){
        for(Student newStudent:program.getStudents()){
            if(((newStudent.getStudentID().equals(studentID))||(newStudent.getEmail().equals(studentID)))&&(newStudent.getPassword().equals(password))){
                program.setCurrentStudent(newStudent);
                return true;
            }
        }
        return false;
    }

    public void studentRegister(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStudent.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Student register menu");
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void landlordSignIn(ActionEvent event) throws IOException{
        String[] outputLandlord = new String[2];

        outputLandlord[0] = landlordUsernameInput.getText();
        outputLandlord[1] = landlordPasswordInput.getText();
        if (!emptyfieldsLandlord()){
        if(landlordPresent(outputLandlord[0],outputLandlord[1] )==true){
            loginInfoLandlord.setText("This matches an account in the DB!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("LandlordMenu.fxml"));
            root = loader.load();

            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Landlord menu");
            scene = new Scene(root);
            stage.setScene(scene);

        }
        else{

            loginInfoLandlord.setText("This doesn't match an account in the DB!");
            landlordUsernameInput.setText("");
            landlordPasswordInput.setText("");
        }}
        else{ loginInfoLandlord.setText("Please fill in every field!");
    }}

    public boolean landlordPresent(String landlordID, String password) {
        for (Landlord newLandlord : program.getLandlords()) {
            if (((newLandlord.getLandlordID().equals(landlordID))||(newLandlord.getEmail().equals(landlordID))) && ((newLandlord.getPassWord().equals(password)))) {
                program.setCurrentLandlord(newLandlord);
                return true;
            }
        }
        return false;
    }

    private Parent root1;
    private Scene scene1;
    private Stage stage1;

    public void landlordRegister(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterLandlord.fxml"));
        root1 = loader.load();

        stage1 = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage1.setTitle("Landlord register menu");
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
    }

    public boolean emptyfieldsLandlord(){
        if ((landlordUsernameInput.getText().equals(""))||(landlordPasswordInput.getText().equals(""))){
            return true;
        }
        return false;
    }

    public boolean emptyfieldsStudent(){
        if ((studentUsernameInput.getText().equals(""))||(studentPasswordInput.getText().equals(""))){
            return true;
        }
        return false;
    }
}

