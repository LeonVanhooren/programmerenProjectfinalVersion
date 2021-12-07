package database;

import logic.*;

import java.sql.*;
import java.util.ArrayList;

public class dbTest {
    private static Connection CON = null;
    private static String USERNAME = "db2021_23";
    private static String PASSWORD = "61928534c4248";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://pdbmbook.com:3306/db2021_23";


    public ArrayList<Student> databaseReadStudent(){
        ArrayList<Student> students = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from student");
            while (rs.next()) {

                String id = rs.getString("studentID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Student newStudent = new Student(firstname, lastname, email, id, password);
                students.add(newStudent);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return students;
    }

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

    public ArrayList<Room> databaseReadRoom(){
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

    public ArrayList<Lease> databaseReadLease(){
        ArrayList<Lease> leases = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from lease");
            while (rs.next()) {

                String studentID = rs.getString("studentID");
                String roomID = rs.getString("roomID");

                Lease newLease = new Lease(studentID, roomID);
                leases.add(newLease);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return leases;
    }

    public ArrayList<Ownership> databaseReadOwnership(){
        ArrayList<Ownership> ownerships = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from ownership");
            while (rs.next()) {

                String landlordID = rs.getString("landlordID");
                String buildingID = rs.getString("buildingID");

                Ownership newOwnership = new Ownership(landlordID, buildingID);
                ownerships.add(newOwnership);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return ownerships;
    }


    public ArrayList<Appliance> databaseReadAppliance(){
        ArrayList<Appliance> appliances = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from appliances");
            while (rs.next()) {

                String applianceID = rs.getString("applianceID");
                String consumption = rs.getString("consumption");
                String efficiency = rs.getString("efficiency");
                String QRCode = rs.getString("QR-code");
                Appliance newAppliance = new Appliance(applianceID, consumption, efficiency, QRCode);
                appliances.add(newAppliance);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return appliances;
    }
    public void addApplianceToDatabase(Appliance appliance){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO appliances "+"VALUES('"+appliance.getApplianceID()+"', '"+appliance.getConsumption()+"', '"+appliance.getEfficiency()+"', '"+appliance.getQRCode()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

    public void addStudentToDatabase(Student student){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO student "+"VALUES('"+student.getStudentID()+"', '"+student.getFirstName()+"', '"+student.getLastName()+"', '"+student.getEmail()+"', '"+student.getPassword()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

    public void addLandlordToDatabase(Landlord landlord){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            String query = "INSERT INTO landlord "+"VALUES('"+landlord.getLandlordID()+"', '"+landlord.getFirstName()+"', '"+landlord.getLastName()+"', '"+landlord.getEmail()+"', '"+landlord.getTelephoneNR()+"', '"+landlord.getPassWord()+"')";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
    }

    public ArrayList<BelongsTo> databaseReadBelongsTo(){
        ArrayList<BelongsTo> belongsToArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from belongs_to");
            while (rs.next()) {

                String roomID = rs.getString("roomID");
                String buildingID = rs.getString("buildingID");

                BelongsTo newBelongTo = new BelongsTo(buildingID, roomID);
                belongsToArrayList.add(newBelongTo);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return belongsToArrayList;
    }


    public ArrayList<Contains> databaseReadContains(){
        ArrayList<Contains> containsArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from contains");
            while (rs.next()) {

                String roomID = rs.getString("roomID");
                String applianceID = rs.getString("applianceID");

                Contains newContains= new Contains(roomID, applianceID);
                containsArrayList.add(newContains);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return containsArrayList;
    }


    public ArrayList<Contract> databaseReadContract(){
        ArrayList<Contract> contracts = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from contract");
            while (rs.next()) {

                String landlordID = rs.getString("landlordID");
                String studentID = rs.getString("studentID");
                String contractNr = rs.getString("contractNr");
                Date startDate = rs.getDate("startDate");
                int contractDuration = rs.getInt("contractDuration");

                Contract newContract = new Contract(studentID, landlordID, contractNr, startDate, contractDuration);
                contracts.add(newContract);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return contracts;
    }


    public ArrayList<Building> databaseReadBuilding(){
        ArrayList<Building> buildings = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from building");
            while (rs.next()) {


                String buildingID = rs.getString("buildingID");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String adress = rs.getString("adress");

                Building newBuilding = new Building(buildingID, country, city, adress);
                buildings.add(newBuilding);

            }

        } catch (SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return buildings;
    }


}
