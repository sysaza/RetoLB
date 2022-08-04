package co.com.lulobank.stepdefinitions;

import co.com.lulobank.exceptions.EmpleadoException;
import co.com.lulobank.models.request.Empleado;
import co.com.lulobank.models.response.DataConsultarEmpleado;
import co.com.lulobank.models.response.DataConsultarEmpleados;
import co.com.lulobank.models.response.DataCrear;
import co.com.lulobank.models.response.DataEliminar;
import co.com.lulobank.questions.VerificacionConsultaEmpleados;
import co.com.lulobank.questions.VerificacionConsultarEmpleado;
import co.com.lulobank.questions.VerificacionCreacion;
import co.com.lulobank.questions.VerificacionEliminacion;
import com.google.gson.Gson;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;


import static co.com.lulobank.exceptions.EmpleadoException.NO_COINCIDENCIA;
import static co.com.lulobank.tasks.Consultar.consultarEmpleado;
import static co.com.lulobank.tasks.Crear.crearEmpleado;
import static co.com.lulobank.tasks.Eliminar.eliminarEmpleado;
import static co.com.lulobank.utils.DatosGenerales.*;
import static co.com.lulobank.utils.GenerarDatos.GenerarDatosEmpleado;
import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class GestionarEmpleadoStepDefinition {

    private Empleado empleadoModel;
    private final Gson gson = new Gson();
    private String idEmpleado;
    private final HashMap<String, Object> headers = new HashMap<>();
    private final Actor actor = Actor.named("Sindy");

    @Dado("que obtengo los datos del empleado")
    public void ObtengoDatosDelEmpleado() {
        empleadoModel = GenerarDatosEmpleado();
        headers.put("Content-Type", "application/json");
        actor.can(CallAnApi.at(URL_BASE));
    }

    @Dado("que requiero consultar todos lo empleados registrados")
    public void ConsultarEmpleadosRegistrados() {
        headers.put("Content-Type", "application/json");
        actor.can(CallAnApi.at(URL_BASE));
    }

    @Cuando("realizo el envío de la petición")
    public void EnviarPeticion() {
        String bodyRequest = gson.toJson(empleadoModel);
        actor.attemptsTo(
                crearEmpleado()
                        .usandoRecurso(RUTA_CREAR_EMPLEADO)
                        .conHeader(headers)
                        .yBody(bodyRequest));
    }

    @Cuando("consumo el servicio")
    public void ConsumirServicio() {
        actor.attemptsTo(
                consultarEmpleado()
                        .usandoRecurso(RUTA_CONSULTAR_EMPLEADOS)
                        .conHeader(headers));
    }

    @Entonces("el status de la respuesta es {string}")
    public void VerificarStatusRespuesta(String statusEsperado) {
        DataCrear respuesta = new VerificacionCreacion().answeredBy(actor);
        actor.should(
                seeThat("El estado de la respuesta no es el esperado",
                        act -> respuesta.getStatus(), equalTo(statusEsperado))
        );
    }

    @Entonces("el status de la respuesta del servicio eliminar es {string}")
    public void VerificarStatusRespuestaEliminar(String statusEsperado) {
        DataEliminar respuesta = new VerificacionEliminacion().answeredBy(actor);
        actor.should(
                seeThat("El estado de la respuesta no es el esperado",
                        act -> respuesta.getStatus(), equalTo(statusEsperado))
        );
    }

    @Entonces("es encuentran {string} registros")
    public void VerificarCantidadRegistros(String numeroRegistros) {
        DataConsultarEmpleados respuesta = new VerificacionConsultaEmpleados().answeredBy(actor);
        int numeroRegistrosEncontrados = respuesta.getData().size();
        actor.should(seeThat(act -> numeroRegistrosEncontrados, equalTo(parseInt(numeroRegistros)))
                .orComplainWith(EmpleadoException.class, String.format(NO_COINCIDENCIA, numeroRegistrosEncontrados)));
    }

    @Entonces("el status code es {string}")
    public void VerificarStatusCode(String statusCodeEsperado) {
        actor.should(
                seeThatResponse("El código no es el esperado",
                        response -> response.statusCode(parseInt(statusCodeEsperado)))
        );
    }

    @Dado("que requiero eliminar un empleado con ID {string}")
    @Dado("que requiero consultar un empleado con ID {string}")
    public void ConsultarEmpleadoConID(String idEmpleado) {
        this.idEmpleado = idEmpleado;
        headers.put("Content-Type", "application/json");
        actor.can(CallAnApi.at(URL_BASE));
    }

    @Cuando("consumo el servicio para realizar la consulta")
    public void ConsumirServicioConsultar() {
        actor.attemptsTo(
                consultarEmpleado()
                        .usandoRecurso(RUTA_CONSULTAR_EMPLEADO + idEmpleado)
                        .conHeader(headers));
    }

    @Cuando("consumo el servicio para eliminar el registro")
    public void ConsumirServicioParaEliminar() {
        actor.attemptsTo(
                eliminarEmpleado()
                        .usandoRecurso(RUTA_ELIMINAR + idEmpleado)
                        .conHeader(headers));
    }

    @Entonces("el nombre de empleado es {string}")
    public void VerificarNombreEmpleado(String nombreEmpleado) {
        DataConsultarEmpleado respuesta = new VerificacionConsultarEmpleado().answeredBy(actor);
        String nombreEmpleadoEncontrado = respuesta.getData().getEmployee_name();
        actor.should(seeThat(
                act -> nombreEmpleadoEncontrado, equalTo(nombreEmpleado))
                .orComplainWith(EmpleadoException.class, String.format(NO_COINCIDENCIA, nombreEmpleadoEncontrado)));
    }
}
