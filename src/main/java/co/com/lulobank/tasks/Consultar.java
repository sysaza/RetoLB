package co.com.lulobank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Map;

public class Consultar implements Task {

    private String recurso;
    Map<String, ?> headers;

    public Consultar usandoRecurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public Consultar conHeader(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(Get.resource(recurso)
                .with(
                        requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .headers(headers)
                )
        );
    }

    public static Consultar consultarEmpleado() {
        return new Consultar();
    }
}
