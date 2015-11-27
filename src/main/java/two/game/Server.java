package two.game;

import com.google.inject.Inject;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Set;

public class Server implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    @Inject
    private Set<Verticle> components;


    public void run() {
        Vertx vertx = Vertx.vertx();

        logger.debug("starting verticles");
        components.forEach(vertx::deployVerticle);
        logger.debug("verticles started");
    }
}