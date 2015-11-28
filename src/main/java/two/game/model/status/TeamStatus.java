package two.game.model.status;

import java.util.Set;

public class TeamStatus {
    private String teamId;
    private Double resourcesAmount;
    private Set<String> userIds;

    public TeamStatus() {

    }

    public TeamStatus(String teamId, Double resourcesAmount, Set<String> userIds) {

        this.teamId = teamId;
        this.resourcesAmount = resourcesAmount;
        this.userIds = userIds;
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

    public Set<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "TeamStatus{" +
                "teamId='" + teamId + '\'' +
                ", resourcesAmount=" + resourcesAmount +
                ", userIds=" + userIds +
                '}';
    }
}
