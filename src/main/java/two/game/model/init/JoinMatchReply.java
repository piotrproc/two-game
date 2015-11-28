package two.game.model.init;

import two.game.model.Event;

public class JoinMatchReply implements Event {
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
        return "JoinMatchReply{" +
                "accepted=" + accepted +
                ", refuseReason='" + refuseReason + '\'' +
                ", info=" + info +
                '}';
    }
}
