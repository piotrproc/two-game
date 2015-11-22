package two.game.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcesServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(ResourcesServer.class);

    @Override
    public void start() throws Exception {

        vertx.createHttpServer().requestHandler(req -> {
            String file = req.path().equals("/") ? "index.html" : req.path();
            req.response().sendFile("./src/main/webapp/" + file);
        }).listen(8080);

        logger.debug("successfully started resources handler");
    }
}