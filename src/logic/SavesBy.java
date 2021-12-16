package logic;

public class SavesBy {
    private String applianceID, description;

    public SavesBy(String applianceID, String description) {
        this.applianceID = applianceID;
        this.description = description;
    }

    public String getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(String applianceID) {
        this.applianceID = applianceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SavesBy{" +
                "applianceID='" + applianceID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
