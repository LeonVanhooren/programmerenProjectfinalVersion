package database;

import logic.Appliance;
import logic.Landlord;

import java.sql.*;
import java.util.ArrayList;

public class DBLandlord {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Landlord> databaseReadLandlord(){
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
            e.printStackTrace();
        }
        return landlords;
    }

    public static void addLandlordToDatabase(Landlord landlord){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO landlord "+"VALUES('"+landlord.getLandlordID()+"', '"+landlord.getFirstName()+"', '"+landlord.getLastName()+"', '"+landlord.getEmail()+"', '"+landlord.getTelephoneNR()+"', '"+landlord.getPassWord()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeLandlordFromDatabase(String column, String change, String primaryKey){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch (column){
                case "firstname":
                    String query1 = "UPDATE landlord SET firstname = ? WHERE landlordID = ?";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, change);
                    pstmt1.setString(2, primaryKey);
                    pstmt1.executeUpdate();
                    break;

                case "lastname":
                    String query2 = "UPDATE landlord SET lastname = ? WHERE landlordID = ?";
                    PreparedStatement pstmt2 = connection.prepareStatement(query2);
                    pstmt2.setString(1, change);
                    pstmt2.setString(2, primaryKey);
                    pstmt2.executeUpdate();
                    break;
                case "email":
                    String query3 = "UPDATE landlord SET email = ? WHERE landlordID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
                case "telephoneNr":
                    String query4 = "UPDATE landlord SET telephoneNr = ? WHERE landlordID = ?";
                    PreparedStatement pstmt4 = connection.prepareStatement(query4);
                    pstmt4.setString(1, change);
                    pstmt4.setString(2, primaryKey);
                    pstmt4.executeUpdate();
                    break;
                case "password":
                    String query5 = "UPDATE landlord SET password = ? WHERE landlordID = ?";
                    PreparedStatement pstmt5 = connection.prepareStatement(query5);
                    pstmt5.setString(1, change);
                    pstmt5.setString(2, primaryKey);
                    pstmt5.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void removeLandlordFromDatabase(Landlord landlord){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM landlord WHERE landlordID="+landlord.getLandlordID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
