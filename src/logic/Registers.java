package logic;

import java.time.LocalDate;
import java.util.Date;

public class Registers {
    private String date;
    private String registrationID, roomID;

    public Registers(String date, String registrationID, String roomID) {
        this.date = date;
        this.registrationID = registrationID;
        this.roomID = roomID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return
                "registrationID: " + registrationID + ", roomID: " + roomID+", "+date ;
    }
}
