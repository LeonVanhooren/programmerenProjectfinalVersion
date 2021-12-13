package database;

import logic.MonthlyConsumption;
import logic.Registers;

import java.sql.*;
import java.util.ArrayList;

public class DBMonthlyConsumption {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<MonthlyConsumption> databaseReadMonthlyConsumption(){
        ArrayList<MonthlyConsumption> monthlyConsumptions = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from monthlyconsumption");
            while (rs.next()) {

                String registrationID = rs.getString("registrationID");
                String water = rs.getString("water");
                String electricity = rs.getString("electricity");
                String gas = rs.getString("gas");

                MonthlyConsumption monthlyConsumption = new MonthlyConsumption(registrationID, water, electricity, gas);
                monthlyConsumptions.add(monthlyConsumption);
            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return monthlyConsumptions;

    }

    public static void addMonthlyConsumptionToDatabase(MonthlyConsumption monthlyConsumption){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO monthlyconsumption "+"VALUES('"+ monthlyConsumption.getRegistrationID() +"', '"+ monthlyConsumption.getWater() +"', '"+ monthlyConsumption.getElectricity() +"', '"+ monthlyConsumption.getGas() +"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }
}
