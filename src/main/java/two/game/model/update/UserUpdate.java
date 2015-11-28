package two.game.model.update;

import two.game.model.Event;

import java.util.List;

public class UserUpdate implements Event {
    private String userId;
    private Long userSequenceId;
    private List<UnitUpdate> unitUpdates;
    private SupportRequest request;

    public UserUpdate(String userId, Long userSequenceId, List<UnitUpdate> unitUpdates, SupportRequest request) {
        this.userId = userId;
        this.userSequenceId = userSequenceId;
        this.unitUpdates = unitUpdates;
        this.request = request;
    }

    public UserUpdate() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getUserSequenceId() {
        return userSequenceId;
    }

    public void setUserSequenceId(Long userSequenceId) {
        this.userSequenceId = userSequenceId;
    }

    public List<UnitUpdate> getUnitUpdates() {
        return unitUpdates;
    }

    public void setUnitUpdates(List<UnitUpdate> unitUpdates) {
        this.unitUpdates = unitUpdates;
    }

    public SupportRequest getRequest() {
        return request;
    }

    public void setRequest(SupportRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "UserUpdate{" +
                "userId='" + userId + '\'' +
                ", userSequenceId=" + userSequenceId +
                ", unitUpdates=" + unitUpdates +
                ", request=" + request +
                '}';
    }
}
