package co.com.lulobank.models.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataConsultarEmpleado extends RespuestaServicio {
    private Empleado data;
}
