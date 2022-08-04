package co.com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class Crear implements Task {

    private String recurso;
    private Map<String, ?> headers;
    private String bodyRequest;

    public Crear usandoRecurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public Crear conHeader(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public Crear yBody(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(recurso)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest).log().body()
                        )
        );
    }

    public static Crear crearEmpleado() {
        return new Crear();
    }
}
