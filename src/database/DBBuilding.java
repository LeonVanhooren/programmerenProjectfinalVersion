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
                String address = rs.getString("address");
                String zip = rs.getString("zip");

                Building newBuilding = new Building(buildingID, country, city, address,zip);
                buildings.add(newBuilding);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buildings;
    }

    public static void addBuildingToDatabase(Building building){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO building "+"VALUES('"+building.getBuildingID()+"', '"+building.getCountry()+"', '"+building.getCity()+"', '"+building.getAddress()+"' , '"+building.getZip() +"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeBuildingFromDatabase(String column, String change, String primaryKey){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch (column){
                case "country":
                    String query1 = "UPDATE building SET country = ? WHERE buildingID = ?";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, change);
                    pstmt1.setString(2, primaryKey);
                    pstmt1.executeUpdate();
                    break;
                case "city":
                    String query2 = "UPDATE building SET city = ? WHERE buildingID = ?";
                    PreparedStatement pstmt2 = connection.prepareStatement(query2);
                    pstmt2.setString(1, change);
                    pstmt2.setString(2, primaryKey);
                    pstmt2.executeUpdate();
                    break;
                case "address":
                    String query3 = "UPDATE building SET address = ? WHERE buildingID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
                case "zip":
                    String query4 = "UPDATE building SET zip = ? WHERE buildingID = ?";
                    PreparedStatement pstmt4 = connection.prepareStatement(query4);
                    pstmt4.setString(1, change);
                    pstmt4.setString(2, primaryKey);
                    pstmt4.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void removeBuildingFromDatabase(Building building){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM building WHERE buildingID="+building.getBuildingID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
