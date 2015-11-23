package two.game.communication.message;

import two.game.model.init.EndGame;
import two.game.model.init.JoinMatchRequest;
import two.game.model.init.LeftGame;
import two.game.model.init.StartGame;
import two.game.model.status.MatchStatus;
import two.game.model.update.UserUpdate;

public enum MessageType {
    JOIN_REQUEST(JoinMatchRequest.class),
    MATCH_STATUS(MatchStatus.class),
    START_GAME(StartGame.class),
    END_GAME(EndGame.class),
    LEFT_GAME(LeftGame.class),
    USER_UPDATE(UserUpdate.class);

    private final Class type;

    MessageType(Class type) {
        this.type = type;
    }

    public Class getMessageClass() {
        return type;
    }
}
