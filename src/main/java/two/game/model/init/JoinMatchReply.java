package two.game.model.init;

public class JoinMatchReply {
    private Boolean accepted;
    private String refuseReason;
    private MatchInfo info;

    public JoinMatchReply() {
    }

    public JoinMatchReply(Boolean accepted, String refuseReason, MatchInfo info) {

        this.accepted = accepted;
        this.refuseReason = refuseReason;
        this.info = info;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public MatchInfo getInfo() {
        return info;

    }

    public void setInfo(MatchInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("JoinMatchReply{accepted=%s, refuseReason='%s', info=%s}", accepted, refuseReason, info);
    }
}