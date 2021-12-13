package logic;


public class Appliance {

    private String applianceID, consumption, efficiency, QRCode, applianceName;

    public Appliance(String applianceID, String consumption, String efficiency, String QRCode, String applianceName) {
        this.applianceID = applianceID;
        this.consumption = consumption;
        this.efficiency = efficiency;
        this.QRCode = QRCode;
        this.applianceName = applianceName;

    }



    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public String getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(String applianceID) {
        this.applianceID = applianceID;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    @Override
    public String toString() {
        return "Appliance{" +
                "applianceID='" + applianceID + '\'' +
                ", consumption='" + consumption + '\'' +
                ", efficiency='" + efficiency + '\'' +
                ", QRCode='" + QRCode + '\'' +
                '}';
    }
}
