package logic;

public class BelongsTo {
    private String buildingID, roomID;

    public BelongsTo(String buildingID, String roomID) {
        this.buildingID = buildingID;
        this.roomID = roomID;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "BelongsTo{" +
                "buildingID='" + buildingID + '\'' +
                ", roomID='" + roomID + '\'' +
                '}';
    }


}
