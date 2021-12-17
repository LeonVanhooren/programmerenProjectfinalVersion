package database;

import logic.Building;
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
            e.printStackTrace();
        }
        return containsArrayList;
    }

    public static void addContainsToDatabase(Contains contains){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO contains "+"VALUES('"+contains.getRoomID()+"', '"+contains.getApplianceID()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeContainsFromDatabase(Contains contains){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM contains WHERE applianceID="+contains.getApplianceID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
