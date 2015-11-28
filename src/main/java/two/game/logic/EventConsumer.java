package two.game.logic;

public interface EventConsumer<EventType> {
    void process(EventType event);
}
