package gui;

import database.DBStudent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.ConservationApp;
import logic.Student;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterStudentController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    private ConservationApp program = ConservationApp.getInstance();
    @FXML
    private TextField firstNameStudent;
    @FXML
    private TextField lastNameStudent;
    @FXML
    private TextField emailStudent;
    @FXML
    private TextField telephoneStudent;
    @FXML
    private TextField passwordStudent1;
    @FXML
    private TextField passwordStudent2;
    @FXML
    private Label registerInfoStudent;
    @FXML
    private Label studentNumber;
    @FXML
    private Label passwordMatching;

    public void studentRegisterButton(){
        String firstName, lastName, email, telephoneNr, password1, password2;
        firstName = firstNameStudent.getText();
        lastName = lastNameStudent.getText();
        email = emailStudent.getText();
        telephoneNr = telephoneStudent.getText();
        password1 = passwordStudent1.getText();
        password2 = passwordStudent2.getText();
        if (!emptyFields()){
        if(studentPresentRegister(firstName, lastName, email, telephoneNr)==false){

            if(!password1.equals(password2)){
                passwordStudent1.setText("");
                passwordStudent2.setText("");
            }
            else{


                int studentNR = (int)Math.floor(Math.random()*(999999-910000+1)+910000);
                String studentNRstring = "01"+studentNR;

                Student newStudent = new Student(firstName, lastName, email, telephoneNr, studentNRstring, password1);

                DBStudent.addStudentToDatabase(newStudent);

                ArrayList<Student> newArrayList = program.getStudents();
                newArrayList.add(newStudent);
                program.setStudents(newArrayList);

                studentNumber.setText("Your studentnr. is "+studentNRstring+" remember this well!");
                registerInfoStudent.setText("The student is successfully registered!");
                registerInfoStudent.setStyle("-fx-text-fill: green;");
                firstNameStudent.setText("");
                lastNameStudent.setText("");
                emailStudent.setText("");
                telephoneStudent.setText("");
                passwordStudent2.setText("");
                passwordStudent1.setText("");
                passwordMatching.setText("");

            }

        }
        else{
            registerInfoStudent.setText("The database already contains this student!");
            registerInfoStudent.setStyle("-fx-text-fill: red;");
        }

        if(password1.equals(password2)){
            passwordMatching.setText("The passwords match!");
            passwordMatching.setStyle("-fx-text-fill: green;");
        }
        else{
            passwordMatching.setText("The passwords do not match!");
            passwordMatching.setStyle("-fx-text-fill: red;");
        }
    }
        else { registerInfoStudent.setText("Please fill in every field!");}}

    public void clearStudentInput(){
        firstNameStudent.setText("");
        lastNameStudent.setText("");
        emailStudent.setText("");
        passwordStudent2.setText("");
        passwordStudent1.setText("");
        telephoneStudent.setText("");
        passwordMatching.setText("");
    }

    public boolean studentPresentRegister(String firstname, String lastName, String email, String telephoneNr){
        for(Student newStudent:program.getStudents()){
            if(newStudent.getFirstName().equals(firstname)&&newStudent.getLastName().equals(lastName)&&newStudent.getEmail().equals(email)&&newStudent.getTelephoneNr().equals(telephoneNr)){
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
    public boolean emptyFields(){
        if ((firstNameStudent.getText().equals(""))||(lastNameStudent.getText().equals(""))||(emailStudent.getText().equals(""))||(telephoneStudent.getText().equals(""))||(passwordStudent1.getText().equals(""))||(passwordStudent2.getText().equals(""))){
            return true;
        }
        return false;
    }
    }


