package co.com.lulobank.questions;

import co.com.lulobank.models.response.DataConsultarEmpleados;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class VerificacionConsultaEmpleados implements Question {
    @Override
    public DataConsultarEmpleados answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(DataConsultarEmpleados.class);
    }
}
