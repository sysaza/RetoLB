package co.com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.HashMap;
import java.util.Map;

public class Eliminar implements Task {
    private String recurso;
    Map<String, ?> headers;

    public Eliminar usandoRecurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public Eliminar conHeader(HashMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(Delete.from(recurso)
                .with(
                        requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .headers(headers)
                )
        );
    }

    public static Eliminar eliminarEmpleado() {
        return new Eliminar();
    }

}
