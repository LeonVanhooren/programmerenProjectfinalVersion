package logic;

public class MonthlyConsumption {
    private String registrationID, water, electricity, gas;

    public MonthlyConsumption(String registrationID, String water, String electricity, String gas) {
        this.registrationID = registrationID;
        this.water = water;
        this.electricity = electricity;
        this.gas = gas;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }


    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    @Override
    public String toString() {
        return "MonthlyConsumption{" +
                "registrationID='" + registrationID + '\'' +
                ", water='" + water + '\'' +
                ", electricity='" + electricity + '\'' +
                ", gas='" + gas + '\'' +
                '}';
    }
}
