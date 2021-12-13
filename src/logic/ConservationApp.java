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
    private ArrayList<OpenContract> openContracts;
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
        this.openContracts = new ArrayList<>();
        this.currentStudent = null;
        this.currentLandlord = null;
    }

    public String[] getRoomIDs(){
        String[] output = new String[rooms.size()];
        for(int i=0; i<rooms.size();i++){
            output[i] = rooms.get(i).getRoomID();
        }

        return output;
    }

    public String[] getBuildingIDs(){
        String[] output = new String[buildings.size()];
        for(int i=0; i<buildings.size();i++){
            output[i] = buildings.get(i).getBuildingID();
        }
        return output;
    }

    public String[] getApplianceIDs(){
        String[] output = new String[appliances.size()];
        for(int i = 0; i<appliances.size();i++){
            output[i] = appliances.get(i).getApplianceID();
        }
        return output;
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

    public static void setConservationApp(ConservationApp conservationApp) {
        ConservationApp.conservationApp = conservationApp;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setLandlords(ArrayList<Landlord> landlords) {
        this.landlords = landlords;
    }

    public void setBuildings(ArrayList<Building> buildings){this.buildings = buildings;}

    public void setOwnerships(ArrayList<Ownership> ownerships) {
        this.ownerships = ownerships;
    }

    public void setContracts(ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setLeases(ArrayList<Lease> leases) {
        this.leases = leases;
    }

    public void setAppliances(ArrayList<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void setBelongsToArrayList(ArrayList<BelongsTo> belongsToArrayList) {
        this.belongsToArrayList = belongsToArrayList;
    }

    public void setContainsArrayList(ArrayList<Contains> containsArrayList) {
        this.containsArrayList = containsArrayList;
    }

    public void setOpenContracts(ArrayList<OpenContract> openContracts) {
        this.openContracts = openContracts;
    }
}
