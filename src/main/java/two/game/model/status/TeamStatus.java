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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamStatus other = (TeamStatus) obj;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		return true;
	}
}
