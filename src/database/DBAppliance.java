package database;

import logic.Appliance;
import logic.Student;

import java.sql.*;
import java.util.ArrayList;

public class DBAppliance {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Appliance> databaseReadAppliance(){
        ArrayList<Appliance> appliances = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from appliances");
            while (rs.next()) {

                String applianceID = rs.getString("applianceID");
                String consumption = rs.getString("consumption");
                String efficiency = rs.getString("efficiency");
                String QRCode = rs.getString("qrCode");
                String applianceName = rs.getString("applianceName");
                String applianceKind = rs.getString("applianceKind");
                Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode, applianceName, applianceKind);
                appliances.add(newAppliance);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return appliances;
    }

    public static void addApplianceToDatabase(Appliance appliance){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO appliances "+"VALUES('"+appliance.getApplianceID()+"', '"+appliance.getConsumption()+"', '"+appliance.getEfficiency()+"', '"+appliance.getQRCode()+"', '"+appliance.getApplianceName()+"', '"+appliance.getApplianceKind()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void changeApplianceFromDatabase(String column, String change, String primaryKey){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch (column){
                case "consumption":
                    String query1 = "UPDATE appliances SET consumption  = ? WHERE applianceID = ?";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, change);
                    pstmt1.setString(2, primaryKey);
                    pstmt1.executeUpdate();
                    break;
                case "efficiency":
                    String query2 = "UPDATE appliances SET efficiency = ? WHERE applianceID = ?";
                    PreparedStatement pstmt2 = connection.prepareStatement(query2);
                    pstmt2.setString(1, change);
                    pstmt2.setString(2, primaryKey);
                    pstmt2.executeUpdate();
                    break;
                case "qrCode":
                    String query3 = "UPDATE appliances SET qrCode = ? WHERE applianceID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
                case "applianceName":
                    String query4 = "UPDATE appliances SET applianceName = ? WHERE applianceID = ?";
                    PreparedStatement pstmt4 = connection.prepareStatement(query4);
                    pstmt4.setString(1, change);
                    pstmt4.setString(2,primaryKey);
                    pstmt4.executeUpdate();
                    break;
                case "applianceKind":
                    String query5 = "UPDATE appliances SET applianceKind = ? WHERE applianceID = ?";
                    PreparedStatement pstmt5 = connection.prepareStatement(query5);
                    pstmt5.setString(1, change);
                    pstmt5.setString(2,primaryKey);
                    pstmt5.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeApplianceFromDatabase(Appliance appliance){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM appliances WHERE applianceID="+appliance.getApplianceID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
