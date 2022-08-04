package co.com.lulobank.questions;

import co.com.lulobank.models.response.DataCrear;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class VerificacionCreacion implements Question {
    @Override
    public DataCrear answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(DataCrear.class);
    }
}
