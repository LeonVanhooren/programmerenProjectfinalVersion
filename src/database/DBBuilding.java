package database;

import logic.Appliance;
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
                String zip = rs.getString("zip");

                Building newBuilding = new Building(buildingID, country, city, adress,zip);
                buildings.add(newBuilding);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return buildings;
    }

    public static void addBuildingToDatabase(Building building){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO building "+"VALUES('"+building.getBuildingID()+"', '"+building.getCountry()+"', '"+building.getCity()+"', '"+building.getAdress()+"' , '"+building.getZip() +"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

    public static void changeBuildingFromDatabase(Building newBuilding, Building oldBuilding){
        try {
            removeBuildingFromDatabase(oldBuilding.getBuildingID());
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO building "+"VALUES('"+newBuilding.getBuildingID()+"', '"+newBuilding.getCountry()+"', '"+newBuilding.getCity()+"', '"+newBuilding.getAdress()+"', '"+newBuilding.getZip()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }



    public static void removeBuildingFromDatabase(String buildingID){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM building WHERE buildingID="+buildingID;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

}
