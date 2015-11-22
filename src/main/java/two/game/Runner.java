package two.game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import two.game.communication.WebSocketServer;
import two.game.config.ServerModule;
import two.game.logic.TestConsumer;

public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new ServerModule()
        );
        Server server = injector.getInstance(Server.class);
        logger.debug("starting server");
        server.run();
    }

}