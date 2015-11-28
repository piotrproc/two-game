package two.game.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import io.vertx.core.Verticle;
import two.game.Server;
import two.game.communication.CommunicationServer;
import two.game.communication.EventDispatcher;
import two.game.communication.ResourcesServer;
import two.game.logic.EventConsumer;
import two.game.logic.JoinRequestConsumer;
import two.game.logic.UserUpdateConsumer;
import two.game.model.init.JoinMatchRequest;
import two.game.model.update.UserUpdate;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Server.class);

        Multibinder<Verticle> vertices = Multibinder.newSetBinder(binder(), Verticle.class);
        vertices.addBinding().to(CommunicationServer.class);
        vertices.addBinding().to(ResourcesServer.class);
        vertices.addBinding().to(EventDispatcher.class);

        MapBinder<Class, EventConsumer> consumers = MapBinder.newMapBinder(binder(), Class.class, EventConsumer.class);
        consumers.addBinding(JoinMatchRequest.class).to(JoinRequestConsumer.class);
        consumers.addBinding(UserUpdate.class).to(UserUpdateConsumer.class);
    }
}
