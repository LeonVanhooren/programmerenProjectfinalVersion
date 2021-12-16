package logic;

public class SavesBy {
    private String applianceID, actionID, date;

    public SavesBy(String actionID,String applianceID, String date) {
        this.applianceID = applianceID;
        this.actionID = actionID;
        this.date = date;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(String applianceID) {
        this.applianceID = applianceID;
    }

    public String getActionID() {
        return actionID;
    }


    @Override
    public String toString() {
        return "SavesBy{" +
                "applianceID='" + applianceID + '\'' +
                ", description='" + actionID + '\'' +
                '}';
    }
}
