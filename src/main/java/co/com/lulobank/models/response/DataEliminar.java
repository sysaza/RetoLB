package co.com.lulobank.models.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataEliminar extends RespuestaServicio {
    private String data;
}
