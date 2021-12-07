package database;

import logic.Landlord;

import java.sql.*;
import java.util.ArrayList;

public class DBLandlord {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public ArrayList<Landlord> databaseReadLandlord(){
        ArrayList<Landlord> landlords = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from landlord");
            while (rs.next()) {

                String id = rs.getString("landlordID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String telephoneNr = rs.getString("telephoneNr");
                String password = rs.getString("password");
                Landlord newLandlord = new Landlord(id, firstname, lastname, email, telephoneNr, password);
                landlords.add(newLandlord);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return landlords;
    }
}
