package two.game.model.init;

import two.game.model.Event;

public class JoinMatchRequest implements Event {
    private String userId;
    private String selectedTeamId;

    public JoinMatchRequest(String userId, String selectedTeamId) {
        this.userId = userId;
        this.selectedTeamId = selectedTeamId;
    }

    public JoinMatchRequest() {
    }

    public String getSelectedTeamId() {

        return selectedTeamId;
    }

    public void setSelectedTeamId(String selectedTeamId) {
        this.selectedTeamId = selectedTeamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JoinMatchRequest{" +
                "userId='" + userId + '\'' +
                ", selectedTeamId='" + selectedTeamId + '\'' +
                '}';
    }
}
