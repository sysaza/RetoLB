package co.com.lulobank.models.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataConsultarEmpleados extends RespuestaServicio {
    private List<Empleado> data;
}
