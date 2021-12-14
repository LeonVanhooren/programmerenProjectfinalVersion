package logic;

import java.util.Date;

public class Contract {
    private String studentID, landlordID, contractNr;
    private String startDate;
    private String status;
    private int contractDuration;

    public Contract(String studentID, String landlordID, String contractID, String startDate, int contractDuration, String status) {
        this.studentID = studentID;
        this.landlordID = landlordID;
        this.contractNr = contractID;
        this.startDate = startDate;
        this.contractDuration = contractDuration;
        this.status = status;
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

    public String getContractNr() {
        return contractNr;
    }

    public void setContractNr(String contractNr) {
        this.contractNr = contractNr;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "studentID='" + studentID + '\'' +
                ", landlordID='" + landlordID + '\'' +
                ", contractID='" + contractNr + '\'' +
                ", startDate=" + startDate + '\'' +
                ", contractDuration=" + contractDuration + '\'' +
                ", status=" + status + '\'' +
                '}';
    }
}
