package co.com.lulobank.questions;

import co.com.lulobank.models.response.DataEliminar;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class VerificacionEliminacion implements Question {
    @Override
    public DataEliminar answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(DataEliminar.class);
    }
}
