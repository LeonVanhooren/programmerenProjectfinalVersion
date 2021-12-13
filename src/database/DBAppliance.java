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
                String QRCode = rs.getString("QR-code");
                String applianceName = rs.getString("applianceName");
                Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode, applianceName);
                appliances.add(newAppliance);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return appliances;
    }

    public static void addApplianceToDatabase(Appliance appliance){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO appliances "+"VALUES('"+appliance.getApplianceID()+"', '"+appliance.getConsumption()+"', '"+appliance.getEfficiency()+"', '"+appliance.getQRCode()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
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
                case "QR-code":
                    String query3 = "UPDATE appliances SET QR-code = ? WHERE applianceID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            System.out.println("FAIL");
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
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }
}
