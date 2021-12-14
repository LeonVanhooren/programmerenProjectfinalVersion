package logic;



import java.util.Objects;

public class Student {

    private String name, firstName, lastName, email, password, studentID, telephoneNr;


    public Student(String firstName, String lastName, String email, String telephoneNr, String studentID,String password){
        /*this.studentID = (int)Math.floor(Math.random()*(200000-100000+1)+100000);*/
        this.studentID = studentID;
        this.name = firstName + " " + lastName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telephoneNr = telephoneNr;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTelephoneNr(){
        return telephoneNr;
    }
    public void setTelephoneNr(String telephoneNr){
        this.telephoneNr = telephoneNr;
    }



    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", studentID='" + studentID + '\'' +
                ", telephoneNr='" + telephoneNr + '\'' +
                '}';
    }
}