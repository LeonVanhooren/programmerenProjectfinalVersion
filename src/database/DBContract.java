package database;

import logic.Contract;

import java.sql.*;
import java.util.ArrayList;

public class DBContract {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Contract> databaseReadContract(){
        ArrayList<Contract> contracts = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from contract");
            while (rs.next()) {

                String landlordID = rs.getString("landlordID");
                String studentID = rs.getString("studentID");
                String contractNr = rs.getString("contractNr");
                Date startDate = rs.getDate("startDate");
                int contractDuration = rs.getInt("contractDuration");

                Contract newContract = new Contract(studentID, landlordID, contractNr, startDate, contractDuration);
                contracts.add(newContract);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return contracts;
    }
}
