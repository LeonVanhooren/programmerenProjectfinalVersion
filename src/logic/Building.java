package logic;

import java.util.ArrayList;

public class Building {

    private String buildingID, country, city, adress,zip;
    private ArrayList<Room> rooms;

    public Building(String buildingID, String country, String city, String adress,String zip) {
        this.buildingID = buildingID;
        this.country = country;
        this.city = city;
        this.adress = adress;
        this.zip = zip;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingID='" + buildingID + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", adress='" + adress + '\'' +", zip='" + zip + '\''+
                '}';
    }
}

