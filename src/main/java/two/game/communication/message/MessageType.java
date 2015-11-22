package two.game.communication.message;

import two.game.model.init.JoinMatchReply;
import two.game.model.status.MatchStatus;
import two.game.model.update.UserUpdate;

public enum MessageType {
    JOIN_REQUEST(JoinMatchReply.class),
    MATCH_STATUS(MatchStatus.class),
    USER_UPDATE(UserUpdate.class);

    private final Class type;

    MessageType(Class type) {
        this.type = type;
    }

    public Class getMessageClass() {
        return type;
    }
}
