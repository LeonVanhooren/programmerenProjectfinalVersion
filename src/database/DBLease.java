package database;

import logic.Lease;

import java.sql.*;
import java.util.ArrayList;

public class DBLease {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public ArrayList<Lease> databaseReadLease(){
        ArrayList<Lease> leases = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from lease");
            while (rs.next()) {

                String studentID = rs.getString("studentID");
                String roomID = rs.getString("roomID");

                Lease newLease = new Lease(studentID, roomID);
                leases.add(newLease);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return leases;
    }
}
