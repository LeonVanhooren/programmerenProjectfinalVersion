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

    public ArrayList<Student> databaseReadStudent(){
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
}
