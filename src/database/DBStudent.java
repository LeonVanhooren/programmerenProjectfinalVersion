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
                String telephoneNr = rs.getString("telephoneNr");
                String password = rs.getString("password");
                Student newStudent = new Student(firstname, lastname, email, telephoneNr, id, password);
                students.add(newStudent);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static
    void addStudentToDatabase(Student student){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO student "+"VALUES('"+student.getStudentID()+"', '"+student.getFirstName()+"', '"+student.getLastName()+"', '"+student.getEmail()+"', '"+student.getPassword()+"', '"+student.getTelephoneNr()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeStudentFromDatabase(String column, String change, String primaryKey){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch (column){
                case "firstname":
                    String query1 = "UPDATE student SET firstname = ? WHERE studentID = ?";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, change);
                    pstmt1.setString(2, primaryKey);
                    pstmt1.executeUpdate();
                    break;
                case "lastname":
                    String query2 = "UPDATE student SET lastname = ? WHERE studentID = ?";
                    PreparedStatement pstmt2 = connection.prepareStatement(query2);
                    pstmt2.setString(1, change);
                    pstmt2.setString(2, primaryKey);
                    pstmt2.executeUpdate();
                    break;
                case "email":
                    String query3 = "UPDATE student SET email = ? WHERE studentID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
                case "telephoneNr":
                    String query4 = "UPDATE student SET telephoneNr = ? WHERE studentID = ?";
                    PreparedStatement pstmt4 = connection.prepareStatement(query4);
                    pstmt4.setString(1, change);
                    pstmt4.setString(2, primaryKey);
                    pstmt4.executeUpdate();
                    break;
                case "password":
                    String query5 = "UPDATE student SET password = ? WHERE studentID = ?";
                    PreparedStatement pstmt5 = connection.prepareStatement(query5);
                    pstmt5.setString(1, change);
                    pstmt5.setString(2, primaryKey);
                    pstmt5.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
    }

}
