package database;

import logic.Ownership;

import java.sql.*;
import java.util.ArrayList;

public class DBOwnership {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public ArrayList<Ownership> databaseReadOwnership(){
        ArrayList<Ownership> ownerships = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from ownership");
            while (rs.next()) {

                String landlordID = rs.getString("landlordID");
                String buildingID = rs.getString("buildingID");

                Ownership newOwnership = new Ownership(landlordID, buildingID);
                ownerships.add(newOwnership);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return ownerships;
    }

}
