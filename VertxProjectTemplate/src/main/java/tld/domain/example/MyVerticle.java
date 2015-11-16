package tld.domain.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * MyVerticle
 * This sample verticle implements a very simple HTTP server.
 */
public class MyVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        vertx
                .createHttpServer()
                .requestHandler(r -> {
                    r.response().end("<h1>Hello World</h1>");
                })
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }
}
