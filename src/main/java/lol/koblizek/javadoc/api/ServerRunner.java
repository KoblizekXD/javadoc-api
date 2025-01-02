package lol.koblizek.javadoc.api;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public class ServerRunner {
    public static void main(String[] args) {
        Javalin.create(config -> config.jsonMapper(new JavalinJackson(DocHandler.OBJECT_MAPPER, true)))
                .post("/api/doc", new DocHandler())
                .start(8080);
    }
}
