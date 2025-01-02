package lol.koblizek.javadoc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DocHandler implements Handler {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final JavaParser JAVA_PARSER = new JavaParser();
    
    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new JavadocSnippetSerializer());
        OBJECT_MAPPER.registerModule(module);
        OBJECT_MAPPER.registerModule(new Jdk8Module());
    }
    
    @Override
    public void handle(@NotNull Context context) {
        if (!context.uploadedFiles().isEmpty()) {
            JAVA_PARSER.parse(context.uploadedFiles().getFirst().content())
                    .getResult()
                    .map(CompilationUnit::getAllComments).ifPresentOrElse(comments -> {
                        context.json(comments.stream().filter(Comment::isJavadocComment).map(comment -> comment.asJavadocComment().parse()).toList());
                    }, () -> {
                        context.result("No files uploaded");
                    });
        } else context.result("No files uploaded");
    }
}
