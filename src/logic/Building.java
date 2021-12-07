package logic;

public class Building {

    private String buildingID, country, city, adress;

    public Building(String buildingID, String country, String city, String adress) {
        this.buildingID = buildingID;
        this.country = country;
        this.city = city;
        this.adress = adress;
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

    @Override
    public String toString() {
        return "Building{" +
                "buildingID='" + buildingID + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}

