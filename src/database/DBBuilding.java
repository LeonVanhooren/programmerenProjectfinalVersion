package database;

import logic.Building;

import java.sql.*;
import java.util.ArrayList;

public class DBBuilding {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Building> databaseReadBuilding(){
        ArrayList<Building> buildings = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from building");
            while (rs.next()) {


                String buildingID = rs.getString("buildingID");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String adress = rs.getString("adress");

                Building newBuilding = new Building(buildingID, country, city, adress);
                buildings.add(newBuilding);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return buildings;
    }

}
