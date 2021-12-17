package gui;

import database.DBLandlord;
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
import java.io.IOException;
import java.util.ArrayList;

public class RegisterLandlordController {

    private ConservationApp program = ConservationApp.getInstance();

    @FXML
    private TextField firstNameLandlord;
    @FXML
    private TextField lastNameLandlord;
    @FXML
    private TextField emailLandlord;
    @FXML
    private TextField telephoneNrLandlord;
    @FXML
    private PasswordField passwordLandlord1;
    @FXML
    private PasswordField passWordLandlord2;

    @FXML
    private Label registerInfoLandlord;
    @FXML
    private Label landlordIDLabel;
    @FXML
    private Label passwordMatchingLandlord;

    private Parent root;
    private Stage stage;
    private Scene scene;


    public void landlordRegisterButton(){
        String firstName, lastName, email,telephoneNr, password1, password2;
        firstName = firstNameLandlord.getText();
        lastName = lastNameLandlord.getText();
        email = emailLandlord.getText();
        password1 = passwordLandlord1.getText();
        password2 = passWordLandlord2.getText();
        telephoneNr = telephoneNrLandlord.getText();
        if (!emptyfields()){
        if(landlordPresentRegister(firstName, lastName, email, telephoneNr)==false){

            if(!password1.equals(password2)){
                passwordLandlord1.setText("");
                passWordLandlord2.setText("");
            }
            else{


                int landlordID = (int)Math.floor(Math.random()*(999-100+1)+999);
                String landlordIDstring = "1"+landlordID;

                Landlord newLandlord = new Landlord(landlordIDstring, firstName, lastName, email, telephoneNr, password1);

                DBLandlord.addLandlordToDatabase(newLandlord);

                ArrayList<Landlord> newArrayList = program.getLandlords();
                newArrayList.add(newLandlord);
                program.setLandlords(newArrayList);

                landlordIDLabel.setText("Your landlord ID is "+landlordIDstring+" remember this well!");
                registerInfoLandlord.setText("The landlord is successfully registered!");
                registerInfoLandlord.setStyle("-fx-text-fill: green;");
                firstNameLandlord.setText("");
                lastNameLandlord.setText("");
                emailLandlord.setText("");
                passwordLandlord1.setText("");
                passWordLandlord2.setText("");
                passwordMatchingLandlord.setText("");
                telephoneNrLandlord.setText("");
            }

        }
        else{
            registerInfoLandlord.setText("The database already contains this landlord!");
            registerInfoLandlord.setStyle("-fx-text-fill: red;");
        }

        if(password1.equals(password2)){
            passwordMatchingLandlord.setText("The passwords match!");
            passwordMatchingLandlord.setStyle("-fx-text-fill: green;");
        }
        else{
            passwordMatchingLandlord.setText("The passwords do not match!");
            passwordMatchingLandlord.setStyle("-fx-text-fill: red;");
        }

    }
        else {registerInfoLandlord.setText("Please fill in every field!");}}

    public void clearLandlordInput(){
        firstNameLandlord.setText("");
        lastNameLandlord.setText("");
        emailLandlord.setText("");
        passwordLandlord1.setText("");
        passWordLandlord2.setText("");
        passwordMatchingLandlord.setText("");
        telephoneNrLandlord.setText("");
        registerInfoLandlord.setText("The database already contains this landlord!");
        registerInfoLandlord.setStyle("-fx-text-fill: red;");

    }

    public boolean landlordPresentRegister(String firstname, String lastName, String email, String telephoneNr){
        for(Landlord newLandlord:program.getLandlords()){
            if(newLandlord.getFirstName().equals(firstname)&&newLandlord.getLastName().equals(lastName)&&newLandlord.getEmail().equals(email)&&newLandlord.getTelephoneNR().equals(telephoneNr)){
                return true;
            }
        }
        return false;
    }

    public void backToSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign in menu");
        scene = new Scene(root);
        stage.setScene(scene);

    }
    public boolean emptyfields(){
        if ((firstNameLandlord.getText().equals(""))||(lastNameLandlord.getText().equals(""))||(emailLandlord.getText().equals(""))||(telephoneNrLandlord.getText().equals(""))||(passwordLandlord1.getText().equals(""))||(passWordLandlord2.getText().equals(""))){
            return true;
        }
        return false;
    }
    }

