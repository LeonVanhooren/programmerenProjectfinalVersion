package database;

import logic.Student;

import java.sql.*;
import java.util.ArrayList;

public class DBStudent {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Student> databaseReadStudent(){
        ArrayList<Student> students = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from student");
            while (rs.next()) {

                String id = rs.getString("studentID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Student newStudent = new Student(firstname, lastname, email, id, password);
                students.add(newStudent);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return students;
    }

    public static void addStudentToDatabase(Student student){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO student "+"VALUES('"+student.getStudentID()+"', '"+student.getFirstName()+"', '"+student.getLastName()+"', '"+student.getEmail()+"', '"+student.getPassword()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

    public static void changeStudentFromDatabase(Student newStudent, Student oldStudent){
        try {
            removeStudentFromDatabase(oldStudent);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO student "+"VALUES('"+newStudent.getStudentID()+"', '"+newStudent.getFirstName()+"', '"+newStudent.getLastName()+"', '"+newStudent.getEmail()+"', '"+newStudent.getPassword()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }



    public static void removeStudentFromDatabase(Student student){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM student WHERE studentID="+student.getStudentID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

}
