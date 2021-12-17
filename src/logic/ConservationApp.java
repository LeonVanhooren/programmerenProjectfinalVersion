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
    private String currentSite;
    private ArrayList<Action> actions;
    private ArrayList<SavesBy> savesByArrayList;



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
        this.currentSite = null;
        this.actions = DBActions.databaseReadActions();
        this.savesByArrayList = DBSavesBy.databaseReadSavesBy();

    }

    public ArrayList<SavesBy> getSavesByArrayList() {
        return savesByArrayList;
    }

    public void setSavesByArrayList(ArrayList<SavesBy> savesByArrayList) {
        this.savesByArrayList = savesByArrayList;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public String getCurrentSite() {
        return currentSite;
    }

    public void setCurrentSite(String currentSite) {
        this.currentSite = currentSite;
    }

    public ArrayList<Room> getRoomsFromBuilding(Building building){
        ArrayList<Room> output = new ArrayList<>();

            for(Room newRoom:this.rooms){
                if(newRoom.getBuildingID().equals(building.getBuildingID())){
                    output.add(newRoom);
                }
            }

        return output;
    }

    public ArrayList<Action> getElectricityActions(){
        ArrayList<Action> output = new ArrayList<>();
        for(Action newAction:this.actions){
            if(newAction.getApplianceKind().equals("Electricity")){
                output.add(newAction);
            }
        }
        return output;
    }
    public ArrayList<Action> getGasActions(){
        ArrayList<Action> output = new ArrayList<>();
        for(Action newAction:this.actions){
            if(newAction.getApplianceKind().equals("Gas")){
                output.add(newAction);
            }
        }
        return output;
    }
    public ArrayList<Action> getWaterActions(){
        ArrayList<Action> output = new ArrayList<>();
        for(Action newAction:this.actions){
            if(newAction.getApplianceKind().equals("Water")){
                output.add(newAction);
            }
        }
        return output;
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

    public ArrayList<Appliance> getAppliancesStudent(){
        ArrayList<Appliance> appliances = new ArrayList<>();

        String roomID = null;
        for(Lease newLease:leases){
            if(newLease.getStudentID().equals(currentStudent.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }

       ArrayList<String> appliancesString = new ArrayList<>();
        for(Contains newContains: containsArrayList) {
            if (newContains.getRoomID().equals(roomID)) {
                appliancesString.add(newContains.getApplianceID());
            }
        }

        for(int i =0; i<appliancesString.size();i++){
            for(Appliance newAppliance:this.appliances){
                if(appliancesString.get(i).equals(newAppliance.getApplianceID())){
                    appliances.add(newAppliance);
                }
            }
        }


        return appliances;
    }

    public ArrayList<Appliance> getAppliancesCurrentStudentElectricity(){
        ArrayList<Appliance> appliances = new ArrayList<>();


        String roomID = null;
        for(Lease newLease:leases){
            if(newLease.getStudentID().equals(currentStudent.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }

        ArrayList<String > appliancesString = new ArrayList<>();
        for(Contains newContains: containsArrayList){
            if(newContains.getRoomID().equals(roomID)){
                appliancesString.add(newContains.getApplianceID());
            }
        }
        for(String applianceID: appliancesString) {
            for (Appliance newAppliance : this.appliances) {
                if (newAppliance.getApplianceID().equals(applianceID)&&newAppliance.getApplianceKind().equals("Electricity")){
                    appliances.add(newAppliance);
                }
            }
        }
        return appliances;
    }

    public ArrayList<Appliance> getAppliancesCurrentStudentWater(){
        ArrayList<Appliance> appliances = new ArrayList<>();


        String roomID = null;
        for(Lease newLease:leases){
            if(newLease.getStudentID().equals(currentStudent.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }

        ArrayList<String > appliancesString = new ArrayList<>();
        for(Contains newContains: containsArrayList){
            if(newContains.getRoomID().equals(roomID)){
                appliancesString.add(newContains.getApplianceID());
            }
        }
        for(String applianceID: appliancesString) {
            for (Appliance newAppliance : this.appliances) {
                if (newAppliance.getApplianceID().equals(applianceID)&&newAppliance.getApplianceKind().equals("Water")){
                    appliances.add(newAppliance);
                }
            }
        }
        return appliances;
    }

    public ArrayList<Appliance> getAppliancesCurrentStudentGas(){
        ArrayList<Appliance> appliances = new ArrayList<>();


        String roomID = null;
        for(Lease newLease:leases){
            if(newLease.getStudentID().equals(currentStudent.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }

        ArrayList<String > appliancesString = new ArrayList<>();
        for(Contains newContains: containsArrayList){
            if(newContains.getRoomID().equals(roomID)){
                appliancesString.add(newContains.getApplianceID());
            }
        }
        for(String applianceID: appliancesString) {
            for (Appliance newAppliance : this.appliances) {
                if (newAppliance.getApplianceID().equals(applianceID)&&newAppliance.getApplianceKind().equals("Gas")){
                    appliances.add(newAppliance);
                }
            }
        }
        return appliances;
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

    public ArrayList<Appliance> getAppliancesFromRoom(Room room){
        ArrayList<Appliance> appliances = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        for(Contains newContains: this.containsArrayList){
            if(newContains.getRoomID().equals(room.getRoomID())){
                strings.add(newContains.getApplianceID());
            }
        }
       for(int i=0; i<strings.size(); i++)
        for(Appliance newAppliance:this.appliances){
            if(newAppliance.getApplianceID().equals(strings.get(i))){
                appliances.add(newAppliance);
            }
        }

        return appliances;
    }

    public ArrayList<Integer> getConsumptionOfAppliances(ArrayList<Appliance> appliances){
        ArrayList<Integer> output = new ArrayList<>();
        int water = 0;
        int gas = 0;
        int electricity = 0;

        for(Appliance newAppliance:appliances){
            if(newAppliance.getApplianceKind().equals("Electricity")){
                electricity+=Integer.parseInt(newAppliance.getConsumption());
            }
            if(newAppliance.getApplianceKind().equals("Gas")){
                gas+=Integer.parseInt(newAppliance.getConsumption());
            }
            if(newAppliance.getApplianceKind().equals("Water")){
                water+=Integer.parseInt(newAppliance.getConsumption());
            }
        }
        output.add(electricity);
        output.add(gas);
        output.add(water);

        return output;
    }

    public ArrayList<Action> getActionsFromRoom(String[] dateSplit, ArrayList<Appliance> appliances){
        ArrayList<Action> actionArrayList = new ArrayList<>();
        ArrayList<SavesBy> savesByArrayList = new ArrayList<>();

        for(Appliance newAppliance:appliances) {
            for (SavesBy newSavesBy : this.savesByArrayList) {
                String[] date = newSavesBy.getDate().split("/");
                if (newSavesBy.getApplianceID().equals(newAppliance.getApplianceID())
                        && date[1].equals(dateSplit[1]) && date[2].equals(dateSplit[2])) {
                    savesByArrayList.add(newSavesBy);
                }
            }
        }

        for (SavesBy newSavesBy:savesByArrayList){
            for (Action newAction:this.actions){
                if(newSavesBy.getActionID().equals(newAction.getActionID())){
                    actionArrayList.add(newAction);
                }
            }
        }

        return actionArrayList;
    }

    public ArrayList<Integer> getMonthlyConservation(ArrayList<Action> actions){
        ArrayList<Integer> output = new ArrayList<>();
        int electricity = 0;
        int gas = 0;
        int water = 0;

        for(Action newAction : actions){
            if(newAction.getApplianceKind().equals("Electricity")){
                electricity+=newAction.getSavedAmount();
            }
            if(newAction.getApplianceKind().equals("Gas")){
                gas+= newAction.getSavedAmount();
            }
            if(newAction.getApplianceKind().equals("Water")){
                water+= newAction.getSavedAmount();
            }
        }

        output.add(electricity);
        output.add(gas);
        output.add(water);

        return output;
    }

    public ArrayList<Integer> getMonthlyConsumptionFromRoom(String date, Room room){

        ArrayList<Appliance> appliances = getAppliancesFromRoom(room);
        ArrayList<Integer> monthlyConsumptionRoom = getConsumptionOfAppliances(appliances);
        String[] dateSplit = date.split("/");
        ArrayList<Action> actions = getActionsFromRoom(dateSplit,appliances);
        ArrayList<Integer> monthlyConservation = getMonthlyConservation(actions);

        ArrayList<Integer> monthlyConsumptionReducted = getMonthlyConsumptionReducted(monthlyConsumptionRoom, monthlyConservation);

        return monthlyConsumptionReducted;
    }

    public ArrayList<Integer> getMonthlyConsumptionReducted(ArrayList<Integer> monthlyConsumptionRoom, ArrayList<Integer> monthlyConservation) {
        ArrayList<Integer> output = new ArrayList<>();
        for(int i=0; i<monthlyConsumptionRoom.size(); i++){
            output.add( monthlyConsumptionRoom.get(i)-monthlyConservation.get(i));
        }
        return output;
    }

    public Room getRoomCurrentStudent(){
        Room output=null;
        String roomID=null;
        for(Lease newLease: this.leases){
            if(newLease.getStudentID().equals(currentStudent.getStudentID())){
                roomID = newLease.getRoomID();
            }
        }

        for(Room newRoom: this.rooms){
            if(newRoom.getRoomID().equals(roomID)){
                output = newRoom;
            }
        }

        return output;
    }

    public ArrayList<String> getSavesBysConservations(ArrayList<Action> outputActions) {
        ArrayList<String> output = new ArrayList<>();
        for(Action newAction:outputActions){
            switch(newAction.getApplianceKind()){
                case "Electricity":
                    output.add(newAction.getSavedAmount()+" kWh electricity");
                    break;
                case "Gas":
                    output.add(newAction.getSavedAmount()+" m³ gas");
                    break;
                case "Water":
                    output.add(newAction.getSavedAmount()+" m³ water");
            }
        }
        return output;
    }


    public ArrayList<String> getCurrentLandlordBuildingIDs() {
        ArrayList<String> output = new ArrayList<>();
        for(Building newBuilding:getCurrentLandlordBuildings()){
            output.add(newBuilding.getBuildingID());
        }
        return output;
    }
    public ArrayList<String> getCurrentLandlordRoomIDs(){
        ArrayList<String> output = new ArrayList<>();
        for(Room newRoom:getCurrentLandlordRooms()){
            output.add(newRoom.getRoomID());
        }
        return output;
    }

    public ArrayList<Registers> getCurrentLandlordRegisters(){
        ArrayList<Registers> output = new ArrayList<>();
        for(Registers newRegisters:this.registers) {
            for (Room newRoom : getCurrentLandlordRooms()) {
                if(newRoom.getRoomID().equals(newRegisters.getRoomID())){
                    output.add(newRegisters);
                }
            }
        }
        return output;
    }
}

