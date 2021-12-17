package logic;

public class Building {

    private String buildingID, country, city, address,zip;

    public Building(String buildingID, String country, String city, String address,String zip) {
        this.buildingID = buildingID;
        this.country = country;
        this.city = city;
        this.address = address;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return
                "buildingID='" + buildingID + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +", zip='" + zip + '\'';
    }
}

