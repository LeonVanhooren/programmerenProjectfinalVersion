package database;

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

                Room newRoom = new Room(characteristics, buildingID, roomNr, roomID);
                rooms.add(newRoom);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return rooms;
    }
}

