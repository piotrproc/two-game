package two.game.communication.message;

public class RawMessage {
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getActualMessage() {
        return actualMessage;
    }

    public void setActualMessage(String actualMessage) {
        this.actualMessage = actualMessage;
    }

    private MessageType type;
    private String actualMessage;

    @Override
    public String toString() {
        return String.format("RawMessage{type=%s, actualMessage='%s'}", type, actualMessage);
    }
}
