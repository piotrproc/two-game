package two.game.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import io.vertx.core.Verticle;
import two.game.Server;
import two.game.communication.WebSocketServer;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Server.class);

        bind(WebSocketServer.class);
        Multibinder<Verticle> binder = Multibinder.newSetBinder(binder(), Verticle.class);
        binder.addBinding().to(WebSocketServer.class);
    }
}
