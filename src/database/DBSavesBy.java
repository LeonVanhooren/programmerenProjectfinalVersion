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
            ResultSet rs = stm.executeQuery("select * from saves_by");
            while (rs.next()) {

                String description = rs.getString("description");
                String applianceID = rs.getString("applianceID");
                SavesBy newSavesBy = new SavesBy(applianceID, description);
                savesByArrayList.add(newSavesBy);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();

        }
        return savesByArrayList;
    }

    public static void addSavesByToDatabase(SavesBy savesBy){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO saves_by "+"VALUES('"+savesBy.getApplianceID()+"', '"+savesBy.getDescription()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }
}
