package database;

import logic.BelongsTo;

import java.sql.*;
import java.util.ArrayList;

public class DBBelongsTo {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public ArrayList<BelongsTo> databaseReadBelongsTo(){
        ArrayList<BelongsTo> belongsToArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from belongs_to");
            while (rs.next()) {

                String roomID = rs.getString("roomID");
                String buildingID = rs.getString("buildingID");

                BelongsTo newBelongTo = new BelongsTo(buildingID, roomID);
                belongsToArrayList.add(newBelongTo);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return belongsToArrayList;
    }
}
