package co.com.lulobank.utils;

import co.com.lulobank.models.request.Empleado;
import com.github.javafaker.Faker;

public class GenerarDatos {

    public static Empleado GenerarDatosEmpleado() {
        Faker faker = new Faker();
        Empleado empleadoModel = new Empleado();
        empleadoModel.setName(faker.name().fullName());
        empleadoModel.setSalary(String.valueOf(faker.number().numberBetween(1000000, 5000000)));
        empleadoModel.setAge(String.valueOf(faker.number().numberBetween(18, 100)));
        return empleadoModel;
    }
}
