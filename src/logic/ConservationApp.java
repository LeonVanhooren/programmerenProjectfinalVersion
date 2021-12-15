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
    private ArrayList<MonthlyConsumption> monthlyConsumptions;
    private ArrayList<Registers> registers;
    private Contract currentContract;



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
        this.currentContract = null;
        this.registers = DBRegisters.databaseReadRegisters();
        this.monthlyConsumptions = DBMonthlyConsumption.databaseReadMonthlyConsumption();

    }

    public Contract getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(Contract currentContract) {
        this.currentContract = currentContract;
    }

    public ArrayList<MonthlyConsumption> getMonthlyConsumptions() {
        return monthlyConsumptions;
    }

    public void setMonthlyConsumptions(ArrayList<MonthlyConsumption> monthlyConsumptions) {
        this.monthlyConsumptions = monthlyConsumptions;
    }

    public ArrayList<Registers> getRegisters() {
        return registers;
    }

    public void setRegisters(ArrayList<Registers> registers) {
        this.registers = registers;
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

    public String[] getBuildingIDsLandlord(){
        ArrayList<String> output = new ArrayList<>();

        for(Ownership newOwnership: ownerships){

            if(newOwnership.getLandlordID().equals(currentLandlord.getLandlordID())){

                output.add(newOwnership.getBuildingID());
            }
        }



        String[] outputString = new String[output.size()];

        for(int i = 0; i< output.size(); i++){
            outputString[i]=output.get(i);
        }
        return outputString;
    }

    public String[] getRoomIDsLandlord(String[] buildingIDs){
       String[] output = buildingIDs;
       ArrayList<String> outputList = new ArrayList<>();

       for(int i=0; i<buildingIDs.length; i++) {
           for (BelongsTo newBelongsTo : belongsToArrayList) {
               if (buildingIDs[i].equals(newBelongsTo.getBuildingID())){
                   outputList.add(newBelongsTo.getRoomID());
               }
           }
       }

       String[] outputString = new String[outputList.size()];
        for(int j = 0; j< outputList.size();j++){
            outputString[j]=outputList.get(j);
        }

       return outputString;
    }

    public Appliance[] getAppliancesStudent(){
        ArrayList<Appliance> appliances = new ArrayList<>();


        String roomID = null;
        for(Lease newLease:leases){
            if(newLease.getStudentID().equals(currentStudent.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }
        System.out.println(roomID);

       ArrayList<String > appliancesString = new ArrayList<>();
        for(Contains newContains: containsArrayList){
            if(newContains.getRoomID().equals(roomID)){
                System.out.println(newContains.getApplianceID());
                appliancesString.add(newContains.getApplianceID());
            }
        }


        Appliance[] appliancesOutput = new Appliance[appliancesString.size()];

        for(int i = 0; i<appliancesString.size();i++){
            for(Appliance newAppliance: this.appliances){
                if(newAppliance.getApplianceID().equals(appliancesString.get(i))){
                    System.out.println(newAppliance);
                    appliancesOutput[i] = newAppliance;
                }
            }
        }


        return appliancesOutput;
    }

    public ArrayList<Registers> getRegistersLandlord(String date){
        ArrayList<Registers> registers = new ArrayList<>();
        String[] roomIDs =getRoomIDsLandlord(getBuildingIDsLandlord());
        for(Registers newRegisters : this.registers){
            for(int i = 0; i<roomIDs.length; i++){
                if(newRegisters.getRoomID().equals(roomIDs[i])&&toMonth(newRegisters.getDate()).equals(toMonth(date))
                &&toYear(newRegisters.getDate()).equals(toYear(date))){
                    registers.add(newRegisters);
                }
            }
        }
        return registers;
    }

    public String toMonth(String date){
        String[] outputArray = date.split("/");
        return outputArray[1];
    }
    public String toYear(String date){
        String[] outputArray = date.split("/");
        return outputArray[2];
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

    public ArrayList<Building> getCurrentLandlordBuildings(){
        ArrayList<Building> buildings = new ArrayList<>();
        for(Ownership newOwnership: this.ownerships){
            for(Building newBuilding:this.buildings){
                if(newOwnership.getLandlordID().equals(currentLandlord.getLandlordID())&&newBuilding.getBuildingID().equals(newOwnership.getBuildingID())){
                    buildings.add(newBuilding);
                }
            }
        }
        return buildings;
    }

    public ArrayList<Contract> getContractsLandlord(){
        ArrayList<Contract> contracts = new ArrayList<>();
        for(Contract newContract: this.contracts){
            if(newContract.getLandlordID().equals(currentLandlord.getLandlordID())){
                contracts.add(newContract);
            }
        }
        return contracts;
    }

    public ArrayList<Room> getCurrentLandlordRooms() {
        ArrayList<Building> currentLandlordBuildings = getCurrentLandlordBuildings();
        ArrayList<Room> rooms = new ArrayList<>();
        for(Building newBuilding:currentLandlordBuildings) {
            for (BelongsTo newBelongsTo : this.belongsToArrayList) {
                for (Room newRoom : this.rooms){
                    if(newBuilding.getBuildingID().equals(newBelongsTo.getBuildingID())&&
                    newBelongsTo.getRoomID().equals(newRoom.getRoomID())){
                        rooms.add(newRoom);
                    }
                }
            }
        }
        return rooms;
    }
}

