#language: es

Característica: Gestionar los empleados
  Yo como automatizadora de la página Dummy Rest Api Example
  Quiero gestionar los empleados
  Para verificar el correcto funcionamiento de los servicios

  Escenario: Registrar empleado
    Dado que obtengo los datos del empleado
    Cuando realizo el envío de la petición
    Entonces el status code es '200'
    Y el status de la respuesta es 'success'

  Escenario: Consultar empleados registrados
    Dado que requiero consultar todos lo empleados registrados
    Cuando consumo el servicio
    Entonces el status code es '200'
    Y es encuentran '24' registros

  Escenario: Consultar un empleado registrado
    Dado que requiero consultar un empleado con ID '1'
    Cuando consumo el servicio para realizar la consulta
    Entonces el status code es '200'
    Y el nombre de empleado es 'Tiger Nixon'

  Escenario: Eliminar registro empleado
    Dado que requiero eliminar un empleado con ID '2'
    Cuando consumo el servicio para eliminar el registro
    Entonces el status code es '200'
    Y el status de la respuesta del servicio eliminar es 'success'