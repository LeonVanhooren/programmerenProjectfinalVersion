package database;

import logic.Contract;
import logic.Student;

import java.sql.*;
import java.util.ArrayList;

public class DBContract {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Contract> databaseReadContract(){
        ArrayList<Contract> contracts = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from contract");
            while (rs.next()) {

                String landlordID = rs.getString("landlordID");
                String studentID = rs.getString("studentID");
                String contractNr = rs.getString("contractNr");
                String startDate = rs.getString("startDate");
                String status = rs.getString("status");
                int contractDuration = rs.getInt("contractDuration");
                String contractRoomID = rs.getString("contractRoomID");

                Contract newContract = new Contract(studentID, landlordID, contractNr, startDate, contractDuration, status, contractRoomID);
                contracts.add(newContract);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }
    public static void addContractToDatabase(Contract contract){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO contract "+"VALUES('"+contract.getStudentID()+"', '"+contract.getLandlordID()+"', '"+contract.getContractNr()+"', '"+contract.getStartDate()+"', '"+contract.getContractDuration()+"', '"+contract.getStatus()+"', '"+contract.getcontractRoomID()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void changeContractFromDatabase(String column, String change, String primaryKey){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch (column){
                case "studentID":
                    String query1 = "UPDATE contract SET studentID  = ? WHERE contractNr = ?";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, change);
                    pstmt1.setString(2, primaryKey);
                    pstmt1.executeUpdate();
                    break;
                case "landlordID":
                    String query2 = "UPDATE contract SET landlordID = ? WHERE contractNr = ?";
                    PreparedStatement pstmt2 = connection.prepareStatement(query2);
                    pstmt2.setString(1, change);
                    pstmt2.setString(2, primaryKey);
                    pstmt2.executeUpdate();
                    break;
                case "startDate":
                    String query3 = "UPDATE contract SET startDate = ? WHERE contractNr = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
                case "contractDuration":
                    String query4 = "UPDATE contract SET contractDuration = ? WHERE contractNr = ?";
                    PreparedStatement pstmt4 = connection.prepareStatement(query4);
                    pstmt4.setString(1, change);
                    pstmt4.setString(2,primaryKey);
                    pstmt4.executeUpdate();
                    break;
                case "status":
                    String query5 = "UPDATE contract SET status = ? WHERE contractNr = ?";
                    PreparedStatement pstmt5 = connection.prepareStatement(query5);
                    pstmt5.setString(1, change);
                    pstmt5.setString(2,primaryKey);
                    pstmt5.executeUpdate();
                    break;
                case "contractRoomID":
                    String query6 = "UPDATE contract SET contractRoomID = ? WHERE contractNr = ?";
                    PreparedStatement pstmt6 = connection.prepareStatement(query6);
                    pstmt6.setString(1, change);
                    pstmt6.setString(2,primaryKey);
                    pstmt6.executeUpdate();
                    break;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeContractFromDatabase(Contract contract){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM contract WHERE contractNr="+contract.getContractNr();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
