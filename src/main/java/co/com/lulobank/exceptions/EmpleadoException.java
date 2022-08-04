package co.com.lulobank.exceptions;

public class EmpleadoException extends AssertionError{
    public static final String NO_COINCIDENCIA = "La información encontrada: %s, no es la esperada";

    public EmpleadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
