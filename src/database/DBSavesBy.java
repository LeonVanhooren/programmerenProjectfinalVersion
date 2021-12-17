package database;

import logic.Action;
import logic.SavesBy;

import java.sql.*;
import java.util.ArrayList;

public class DBSavesBy {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<SavesBy> databaseReadSavesBy(){
        ArrayList<SavesBy> savesByArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from savesby");
            while (rs.next()) {

                String actionID = rs.getString("actionID");
                String applianceID = rs.getString("applianceID");
                String date = rs.getString("date");
                SavesBy newSavesBy = new SavesBy(actionID, applianceID, date);
                savesByArrayList.add(newSavesBy);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return savesByArrayList;
    }

    public static void addSavesByToDatabase(SavesBy savesBy){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO savesby "+"VALUES('"+savesBy.getActionID()+"', '"+savesBy.getApplianceID()+"', '"+savesBy.getDate()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
