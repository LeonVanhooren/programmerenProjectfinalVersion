package logic;

public class Contains {
    private String roomID, applianceID;

    public Contains(String roomID, String applianceID) {
        this.roomID = roomID;
        this.applianceID = applianceID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(String applianceID) {
        this.applianceID = applianceID;
    }

    @Override
    public String toString() {
        return "Contains{" +
                "roomID='" + roomID + '\'' +
                ", applianceID='" + applianceID + '\'' +
                '}';
    }
}
