package logic;

public class Action {
    private String description, applianceKind, savedAmount;
    private int recommended;

    public Action(String description, String applianceKind, String savedAmount, int recommended) {
        this.description = description;
        this.applianceKind = applianceKind;
        this.savedAmount = savedAmount;
        this.recommended = recommended;
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

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
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
        return "Action{" +
                "description='" + description + '\'' +
                ", applianceKind='" + applianceKind + '\'' +
                ", savedAmount='" + savedAmount + '\'' +
                ", recommended=" + recommended +
                '}';
    }
}
