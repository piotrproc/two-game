package two.game.model.status;

import java.util.List;

public class MatchStatus {
    private Long sequenceId;
    private List<MissleStatus> missleStatuses;
    private List<AttackEvent> attackEvents;
    private List<TeamStatus> teamStatuses;
    private List<UnitStatus> unitStatuses;
}
