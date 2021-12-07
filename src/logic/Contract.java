package logic;

import java.util.Date;

public class Contract {
    private String studentID, landlordID, contractNr;
    private Date startDate;
    private int contractDuration;

    public Contract(String studentID, String landlordID, String contractID, Date startDate, int contractDuration) {
        this.studentID = studentID;
        this.landlordID = landlordID;
        this.contractNr = contractID;
        this.startDate = startDate;
        this.contractDuration = contractDuration;
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

    public String getContractID() {
        return contractNr;
    }

    public void setContractID(String contractNr) {
        this.contractNr = contractNr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "studentID='" + studentID + '\'' +
                ", landlordID='" + landlordID + '\'' +
                ", contractID='" + contractNr + '\'' +
                ", startDate=" + startDate +
                ", contractDuration=" + contractDuration +
                '}';
    }
}
