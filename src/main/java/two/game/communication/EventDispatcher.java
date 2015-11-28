package two.game.communication;

import com.google.gson.Gson;
import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.EventConsumer;
import two.game.model.Event;

import java.util.Map;

public class EventDispatcher extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(EventDispatcher.class);
    private final Map<Class, EventConsumer> consumers;

    @Inject
    public EventDispatcher(Map<Class, EventConsumer> consumers) {
        this.consumers = consumers;
    }

    @Override
    public void start() throws Exception {
        consumers.forEach(this::pipeToConsumer);
    }

    private <T extends Event> void pipeToConsumer(Class<T> type, EventConsumer<T> consumer) {
        Gson gson = new Gson();
        vertx.eventBus().consumer(type.getSimpleName(), message -> {
            logger.debug("got {}", message.body());
            T object = gson.fromJson(message.body().toString(), type);
            logger.debug("parsed {}", object);
            consumer.process(object);
        });
    }
}
