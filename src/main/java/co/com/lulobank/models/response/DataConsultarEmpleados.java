package co.com.lulobank.models.response;

import lombok.Data;
import java.util.List;

@Data
public class DataConsultarEmpleados extends RespuestaServicio {
    private List<Empleado> data;
}
