package logic;

import database.*;

import java.util.ArrayList;

public class ConservationApp {

    private static ConservationApp conservationApp = new ConservationApp();

    private ArrayList<Student> students;
    private ArrayList<Landlord> landlords;
    private ArrayList<Building> buildings;
    private ArrayList<Ownership> ownerships;
    private ArrayList<Contract> contracts;
    private ArrayList<Room> rooms;
    private ArrayList<Lease> leases;
    private ArrayList<Appliance> appliances;
    private ArrayList<BelongsTo> belongsToArrayList;
    private ArrayList<Contains> containsArrayList;
    private Student currentStudent;
    private Landlord currentLandlord;


    public ConservationApp() {
        this.students = DBStudent.databaseReadStudent();
        this.landlords = DBLandlord.databaseReadLandlord();
        this.buildings = DBBuilding.databaseReadBuilding();
        this.ownerships = DBOwnership.databaseReadOwnership();
        this.contracts = DBContract.databaseReadContract();
        this.rooms = DBRoom.databaseReadRoom();
        this.leases = DBLease.databaseReadLease();
        this.appliances = DBAppliance.databaseReadAppliance();
        this.belongsToArrayList = DBBelongsTo.databaseReadBelongsTo();
        this.containsArrayList = DBContains.databaseReadContains();
        this.currentStudent = null;
        this.currentLandlord = null;
    }

    public void setCurrentStudent(Student student){
        this.currentStudent = student;
    }

    public void setCurrentLandlord(Landlord landlord){
        this.currentLandlord = landlord;
    }

    public static ConservationApp getInstance() {
        return conservationApp;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Landlord> getLandlords() {
        return landlords;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Ownership> getOwnerships() {
        return ownerships;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Lease> getLeases() {
        return leases;
    }

    public ArrayList<Appliance> getAppliances() {
        return appliances;
    }

    public ArrayList<BelongsTo> getBelongsToArrayList() {
        return belongsToArrayList;
    }

    public ArrayList<Contains> getContainsArrayList() {
        return containsArrayList;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public Landlord getCurrentLandlord() {
        return currentLandlord;
    }
}
