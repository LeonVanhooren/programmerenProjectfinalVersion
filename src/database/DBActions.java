package database;

import logic.Action;
import logic.Appliance;

import java.sql.*;
import java.util.ArrayList;

public class DBActions {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Action> databaseReadActions(){
        ArrayList<Action> actions = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from actions");
            while (rs.next()) {

                String description = rs.getString("description");
                String applianceKind = rs.getString("applianceKind");
                int recommended = rs.getInt("recommended");
                int savedAmount = rs.getInt("savedAmount");
                String actionID = rs.getString("actionID");
                Action newAction = new Action(actionID, applianceKind, recommended, savedAmount, description);
                actions.add(newAction);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return actions;
    }

    public static void addActionsToDatabase(Action action){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO actions "+"VALUES('"+action.getActionID()+"', '"+action.getApplianceKind()+"', '"+action.getRecommended()+"', '"+action.getSavedAmount()+"', '"+action.getDescription()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeActionFromDatabase(Action action){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM actions WHERE actionID="+action.getActionID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeActionFromDatabase(String column, int change, String primaryKey){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch (column){
                case "recommended":
                    String query3 = "UPDATE actions SET recommended = ? WHERE actionID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setInt(1, change);
                    pstmt3.setString(2, primaryKey);
                    pstmt3.executeUpdate();
                    break;
                case "savedAmount":
                    String query4 = "UPDATE actions SET savedAmount = ? WHERE actionID = ?";
                    PreparedStatement pstmt4 = connection.prepareStatement(query4);
                    pstmt4.setInt(1, change);
                    pstmt4.setString(2,primaryKey);
                    pstmt4.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
