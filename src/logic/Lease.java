package logic;

public class Lease {
    private String studentID, roomID;

    public Lease(String studentID, String roomID) {
        this.studentID = studentID;
        this.roomID = roomID;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "studentID='" + studentID + '\'' +
                ", roomID='" + roomID + '\'' +
                '}';
    }
}

