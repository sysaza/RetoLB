package co.com.lulobank.questions;

import co.com.lulobank.models.response.DataConsultarEmpleado;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class VerificacionConsultarEmpleado implements Question {
    @Override
    public DataConsultarEmpleado answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(DataConsultarEmpleado.class);
    }
}
