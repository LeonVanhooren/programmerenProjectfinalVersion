package gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.ConservationApp;
import logic.Student;

public class LoginScreenController {

    private ConservationApp program = ConservationApp.getInstance();


    public boolean studentPresent(String studentID, String password){
        for(Student newStudent:program.getStudents()){
            if(newStudent.getStudentID().equals(studentID)&&newStudent.getPassword().equals(password)){
                program.setCurrentStudent(newStudent);
                return true;

            }
        }
        return false;
    }

    private Stage stage;
    private Parent root;
    private Scene scene;
}
