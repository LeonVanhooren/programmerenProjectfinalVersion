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

                Contract newContract = new Contract(studentID, landlordID, contractNr, startDate, contractDuration, status);
                contracts.add(newContract);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return contracts;
    }
    public static void addContractToDatabase(Contract contract){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO contract "+"VALUES('"+contract.getStudentID()+"', '"+contract.getLandlordID()+"', '"+contract.getContractNr()+"', '"+contract.getStartDate()+"', '"+contract.getContractDuration()+"', '"+contract.getStatus()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

    public static void removeContractFromDatabase(Contract contract){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM contract WHERE contractID="+contract.getStudentID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }
}
