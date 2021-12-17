package database;

import logic.ConservationApp;
import logic.Room;

import java.sql.*;
import java.util.ArrayList;

public class DBRoom {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";

    public static ArrayList<Room> databaseReadRoom(){
        ArrayList<Room> rooms = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from room");
            while (rs.next()) {

                int roomNr = Integer.parseInt(rs.getString("roomNr"));
                String roomID = rs.getString("roomID");
                String buildingID = rs.getString("buildingID");
                String characteristics = rs.getString("characteristics");

                Room newRoom = new Room(roomNr, roomID, buildingID, characteristics);
                rooms.add(newRoom);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    public static void addRoomToDatabase(Room room){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO room "+"VALUES('"+room.getRoomNR()+"', '"+room.getRoomID()+"', '"+room.getBuildingID()+"', '"+room.getCharacteristics()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void changeRoomFromDatabase(String column, String change, String primarykey){
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            switch(column){
                case "roomNr":
                    String query1 = "UPDATE room SET roomNr = ? WHERE roomID = ?";
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, change);
                    pstmt1.setString(2, primarykey);
                    pstmt1.executeUpdate();
                    break;
                case "buildingID":
                    String query2 = "UPDATE room SET buildingID = ? WHERE roomID = ?";
                    PreparedStatement pstmt2 = connection.prepareStatement(query2);
                    pstmt2.setString(1, change);
                    pstmt2.setString(2, primarykey);
                    pstmt2.executeUpdate();
                    break;
                case "characteristics":
                    String query3 = "UPDATE room SET characteristics = ? WHERE roomID = ?";
                    PreparedStatement pstmt3 = connection.prepareStatement((query3));
                    pstmt3.setString(1, change);
                    pstmt3.setString(2, primarykey);
                    pstmt3.executeUpdate();
                    break;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeRoomFromDatabase(Room room){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "DELETE FROM room WHERE roomID="+room.getRoomID();
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

