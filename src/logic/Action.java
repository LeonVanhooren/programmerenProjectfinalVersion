package logic;

public class Action {
    private String description, applianceKind, actionID;
    private int recommended, savedAmount;

    public Action(String actionID, String applianceKind, int recommended, int savedAmount, String description) {
        this.description = description;
        this.applianceKind = applianceKind;
        this.savedAmount = savedAmount;
        this.recommended = recommended;
        this.actionID = actionID;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplianceKind() {
        return applianceKind;
    }

    public void setApplianceKind(String applianceKind) {
        this.applianceKind = applianceKind;
    }

    public int getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(int savedAmount) {
        this.savedAmount = savedAmount;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        String output = getActionID()+", "+getDescription()+" saves "+getSavedAmount();
        switch (getApplianceKind()){
            case "Electricity":
                output += " kWh electricity";
                break;
            case "Gas":
                output+=" m³ gas";
                break;
            case "Water":
                output+=" m³ water";
                break;
        }
        return output;
    }
}
