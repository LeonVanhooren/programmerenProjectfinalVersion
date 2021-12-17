package logic;

public class Room {
    private String roomID, buildingID, characteristics;
    private int roomNR;
    /*private ArrayList<String> characteristics;*/


    public Room(int roomNR, String roomID, String buildingID, String characteristics){
        this.roomID =roomID;
        this.roomNR = roomNR;
        this.buildingID = buildingID;
        this.characteristics = characteristics;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public int getRoomNR() {
        return roomNR;
    }

    public void setRoomNR(int roomNR) {
        this.roomNR = roomNR;
    }

    @Override
    public String toString() {
        return
                "roomID='" + roomID + '\'' +
                ", buildingID='" + buildingID + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", roomNR=" + roomNR + '\'';
    }
}

