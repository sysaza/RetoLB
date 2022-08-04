package co.com.lulobank.models.response;

import lombok.Data;

@Data
public class DataConsultarEmpleado extends RespuestaServicio {
    private Empleado data;
}
