package database;

import logic.BelongsTo;
import logic.Ownership;
import logic.Registers;
import logic.Student;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class DBRegisters {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Registers> databaseReadRegisters(){
        ArrayList<Registers> registers = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from registers");
            while (rs.next()) {

                String date = rs.getString("date");
                String registrationID = rs.getString("registrationID");
                String roomID = rs.getString("roomID");

                Registers newRegisters = new Registers(date, registrationID, roomID);
                registers.add(newRegisters);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registers;

    }

    public static void addRegistersToDatabase(Registers registers){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();


            String query = "INSERT INTO registers "+"VALUES('"+ registers.getDate()+"', '"+ registers.getRegistrationID() +"', '"+ registers.getRoomID() +"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeRegisterFromDatabase(Registers registers){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM registers WHERE registrationID="+registers.getRegistrationID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
