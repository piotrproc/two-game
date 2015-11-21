package two.game.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @Override
    public void start() throws Exception {

        vertx.createHttpServer().websocketHandler(ws -> ws.handler(data -> {
            logger.debug("got request {}", data.toString());
            vertx.eventBus().publish("test-address", new JsonObject(data.toString()));
        })).listen(8077);

        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                String file = req.path().equals("/") ? "index.html" : req.path();
                req.response().sendFile("./src/main/webapp/" + file);
            }
        }).listen(8080);

        logger.debug("successfully started handler");
    }
}