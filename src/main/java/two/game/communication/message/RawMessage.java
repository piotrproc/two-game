package two.game.communication.message;

import com.google.gson.JsonObject;

public class RawMessage {
    private MessageType type;
    private JsonObject actualMessage;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public JsonObject getActualMessage() {
        return actualMessage;
    }

    public void setActualMessage(JsonObject actualMessage) {
        this.actualMessage = actualMessage;
    }

    @Override
    public String toString() {
        return String.format("RawMessage{type=%s, actualMessage='%s'}", type, actualMessage);
    }
}
