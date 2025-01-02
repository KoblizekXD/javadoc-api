package lol.koblizek.javadoc.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.javaparser.javadoc.description.JavadocSnippet;

import java.io.IOException;

public final class JavadocSnippetSerializer extends JsonSerializer<JavadocSnippet> {
    @Override
    public void serialize(JavadocSnippet value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("content", value.toText());
        gen.writeEndObject();
    }

    @Override
    public Class<JavadocSnippet> handledType() {
        return JavadocSnippet.class;
    }
}