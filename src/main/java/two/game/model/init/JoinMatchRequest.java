package two.game.model.init;

public class JoinMatchRequest {
    private Long matchId;
    private String userId;

    public JoinMatchRequest() {
    }

    public JoinMatchRequest(Long matchId, String userId) {

        this.matchId = matchId;
        this.userId = userId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("JoinMatchRequest{matchId=%d, userId='%s'}", matchId, userId);
    }

}
