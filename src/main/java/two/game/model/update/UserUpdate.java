package two.game.model.update;

import java.util.List;

public class UserUpdate {
    private Long matchId;
    private Long userSequenceId;
    private List<UnitUpdate> moves;

    public UserUpdate() {

    }

    public UserUpdate(Long matchId, Long userSequenceId, List<UnitUpdate> moves) {
        this.matchId = matchId;
        this.userSequenceId = userSequenceId;
        this.moves = moves;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getUserSequenceId() {
        return userSequenceId;
    }

    public void setUserSequenceId(Long userSequenceId) {
        this.userSequenceId = userSequenceId;
    }

    public List<UnitUpdate> getMoves() {
        return moves;
    }

    public void setMoves(List<UnitUpdate> moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return String.format("UserUpdate{matchId=%d, userSequenceId=%d, moves=%s}", matchId, userSequenceId, moves);
    }
}
