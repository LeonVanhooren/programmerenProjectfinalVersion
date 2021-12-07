package logic;

public class Landlord {

    private String name, firstName, lastName, email,telephoneNR, passWord, landlordID;



    public Landlord(String landlordID,String firstName, String lastName, String email, String telephoneNR, String passWord){
        this.name = firstName+" "+lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNR = telephoneNR;
        this.passWord = passWord;
        this.landlordID = landlordID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNR() {
        return telephoneNR;
    }

    public void setTelephoneNR(String telephoneNR) {
        this.telephoneNR = telephoneNR;
    }

    public String getLandlordID() {
        return landlordID;
    }

    public void setLandlordID(String landlordID) {
        this.landlordID = landlordID;
    }

    @Override
    public String toString() {
        return "Landlord{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNR='" + telephoneNR + '\'' +
                ", passWord='" + passWord + '\'' +
                ", landlordID='" + landlordID + '\'' +
                '}';
    }
}
