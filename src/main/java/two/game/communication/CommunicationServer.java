package two.game.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicationServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(CommunicationServer.class);

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions();
        //todo filter messages
        options.addInboundPermitted(new PermittedOptions().setAddressRegex(".*"));
        options.addOutboundPermitted(new PermittedOptions().setAddressRegex(".*"));
        sockJSHandler.bridge(options);

        router.route("/eventbus/*").handler(sockJSHandler);
        vertx.createHttpServer().requestHandler(router::accept).listen(8077);

        logger.debug("successfully started handler");
    }
}