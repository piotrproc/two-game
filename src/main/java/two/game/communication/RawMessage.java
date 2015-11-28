package two.game.communication;

import com.google.gson.JsonObject;

public class RawMessage {
    private String type;
    private JsonObject actualMessage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
