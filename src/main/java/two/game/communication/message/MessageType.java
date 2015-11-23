package two.game.communication.message;

import two.game.model.init.EndGame;
import two.game.model.init.JoinMatchRequest;
import two.game.model.init.LeftGame;
import two.game.model.init.StartGame;
import two.game.model.status.MatchStatus;
import two.game.model.update.UserUpdate;

public enum MessageType {
    // incoming
    JOIN_REQUEST(JoinMatchRequest.class),
    USER_UPDATE(UserUpdate.class),
    LEFT_GAME(LeftGame.class),

    // outgoing
    MATCH_STATUS(MatchStatus.class),
    START_GAME(StartGame.class),
    END_GAME(EndGame.class);

    private final Class type;

    MessageType(Class type) {
        this.type = type;
    }

    public Class getMessageClass() {
        return type;
    }
}
