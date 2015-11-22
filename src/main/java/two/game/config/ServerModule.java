package two.game.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import io.vertx.core.Verticle;
import two.game.Server;
import two.game.communication.CommunicationServer;
import two.game.communication.ResourcesServer;
import two.game.logic.JoinRequestConsumer;
import two.game.logic.UserUpdateConsumer;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Server.class);

        Multibinder<Verticle> binder = Multibinder.newSetBinder(binder(), Verticle.class);
        binder.addBinding().to(CommunicationServer.class);
        binder.addBinding().to(ResourcesServer.class);
        binder.addBinding().to(JoinRequestConsumer.class);
        binder.addBinding().to(UserUpdateConsumer.class);
    }
}
