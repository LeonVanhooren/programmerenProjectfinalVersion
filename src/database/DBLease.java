package database;

import logic.Contract;
import logic.Landlord;
import logic.Lease;

import java.sql.*;
import java.util.ArrayList;

public class DBLease {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Lease> databaseReadLease(){
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
            e.printStackTrace();
        }
        return leases;
    }

    public static void addLeaseToDatabase(Lease lease){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO lease "+"VALUES('"+lease.getStudentID()+"', '"+lease.getRoomID()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeLeaseFromDatabase(Lease lease){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM lease WHERE roomID="+lease.getRoomID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
