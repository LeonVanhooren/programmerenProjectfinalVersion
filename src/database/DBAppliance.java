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
                Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode);
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


    public static void changeApplianceFromDatabase(Appliance newAppliance, Appliance oldAppliance){
        try {
            removeApplianceFromDatabase(oldAppliance);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO appliances "+"VALUES('"+newAppliance.getApplianceID()+"', '"+newAppliance.getConsumption()+"', '"+newAppliance.getEfficiency()+"', '"+newAppliance.getQRCode()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

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
