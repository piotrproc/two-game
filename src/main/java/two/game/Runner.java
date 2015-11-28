package two.game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import two.game.config.MainModule;

public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new MainModule()
        );
        Server server = injector.getInstance(Server.class);
        logger.debug("starting server");
        server.run();
    }

}