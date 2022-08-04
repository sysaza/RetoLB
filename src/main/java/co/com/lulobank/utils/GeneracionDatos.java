package co.com.lulobank.utils;

import co.com.lulobank.models.request.CreacionEmpleado;
import com.github.javafaker.Faker;

public class GeneracionDatos {

    public static CreacionEmpleado generarDatosEmpleado() {
        Faker faker = new Faker();
        CreacionEmpleado empleadoModel = new CreacionEmpleado();
        empleadoModel.setName(faker.name().fullName());
        empleadoModel.setSalary(String.valueOf(faker.number().numberBetween(1000000, 5000000)));
        empleadoModel.setAge(String.valueOf(faker.number().numberBetween(18, 100)));
        return empleadoModel;
    }
}
