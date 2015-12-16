package two.game.model.status;

import two.game.config.ControlPointConfig;
import two.game.model.ControlPoint;

import java.util.Set;

public class TeamStatus {
    private String teamId;
    private Double resourcesAmount;
    private Set<String> userIds;
    private Set<ControlPoint> controlPoints;

    public TeamStatus() {

    }

    public TeamStatus(String teamId, Double resourcesAmount, Set<String> userIds, Set<ControlPoint> controlPoints) {
        this.teamId = teamId;
        this.resourcesAmount = resourcesAmount;
        this.userIds = userIds;
        this.controlPoints = controlPoints;
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

    public Set<ControlPoint> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(Set<ControlPoint> controlPoints) {
        this.controlPoints = controlPoints;
    }

    public void addResourcesByControlPoints(){
        int noOfControlPoints = controlPoints.size();
        resourcesAmount += noOfControlPoints * ControlPointConfig.resourcesPerInterval;
    }

    public void increaseResources(){
        resourcesAmount += 20;
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
