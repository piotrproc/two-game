package two.game.model.status;

public class TeamStatus {
    private String teamId;
    private Double resourcesAmount;

    public TeamStatus() {
    }

    public TeamStatus(String teamId, Double resourcesAmount) {
        this.teamId = teamId;
        this.resourcesAmount = resourcesAmount;

    }

    @Override
    public String toString() {
        return "TeamStatus{" +
                "teamId='" + teamId + '\'' +
                ", resourcesAmount=" + resourcesAmount +
                '}';
    }

    public String getTeamId() {

        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Double getResourcesAmount() {
        return resourcesAmount;
    }

    public void setResourcesAmount(Double resourcesAmount) {
        this.resourcesAmount = resourcesAmount;
    }
}
