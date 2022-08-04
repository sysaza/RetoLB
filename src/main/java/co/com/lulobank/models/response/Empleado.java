package co.com.lulobank.models.response;

import lombok.Data;

@Data
public class Empleado {
    private String id;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private String profile_image;
}
