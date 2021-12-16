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
            System.out.println("FAIL");
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
            System.out.println("FAIL");
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
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }
}
