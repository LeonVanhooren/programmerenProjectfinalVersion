package database;

import logic.Contains;

import java.sql.*;
import java.util.ArrayList;

public class DBContains {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Contains> databaseReadContains(){
        ArrayList<Contains> containsArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from contains");
            while (rs.next()) {

                String roomID = rs.getString("roomID");
                String applianceID = rs.getString("applianceID");

                Contains newContains= new Contains(roomID, applianceID);
                containsArrayList.add(newContains);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return containsArrayList;
    }
}
