package logic;

import java.util.Date;

public class OpenContract {
    private String studentID, landlordID;
    private int duration;
    private Date startDate;
    public enum Status {ACCEPTED, NOTEVALUATED, DECLINED}
    private Status status;

    public OpenContract(String studentID, String landlordID, Status status, Date startDate, int duration){
        this.landlordID = landlordID;
        this.studentID = studentID;
        this.status = status;
        this.duration = duration;
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getLandlordID() {
        return landlordID;
    }

    public void setLandlordID(String landlordID) {
        this.landlordID = landlordID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OpenContract{" +
                "studentID='" + studentID + '\'' +
                ", landlordID='" + landlordID + '\'' +
                ", status=" + status +
                '}';
    }
}
