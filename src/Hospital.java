import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hospital {

    private Consola consola;

    private ArrayList<Unidad> unidades;
    private ArrayList<Empleado> empleadosGeneral;
    private ArrayList<Paciente> pacientesGeneral;
    private GestorPacientes gestorPacientes;
    private GestorEmpleados gestorEmpleados;
    private GestorEstudiantes gestorEstudiantes;

    public static void main(String[] args) throws Exception {
        new Hospital().gestiona();
    }
 
    public Hospital(){
        // singleton
        consola = Consola.getInstancia();
        
        unidades = new ArrayList<Unidad>();
        empleadosGeneral = new ArrayList<Empleado>();
        pacientesGeneral = new ArrayList<Paciente>();

        unidades.add(new Administracion("Administración"));
        unidades.add(new Urgencias("Urgencias"));
        unidades.add(new ConsultasExternas("Consultas Externas"));
        unidades.add(new Cafeteria("Cafetería"));
        unidades.add(new UnidadFormacion("Unidad Formación"));
        unidades.add(new UnidadDiabetes("Unidad Diabetes"));
        unidades.add(new UnidadCardiovascular("Unidad Cardiovascular"));
        unidades.add(new Hospitalizacion("Hospitalización"));
        unidades.add(new UCI("UCI"));
        unidades.add(new PruebasMedicas("Pruebas Médicas"));
        unidades.add(new Aparcamiento("Aparcamiento"));

        gestorPacientes = new GestorPacientes(unidades, empleadosGeneral, pacientesGeneral);
        gestorEmpleados = new GestorEmpleados(unidades, empleadosGeneral);
        gestorEstudiantes = new GestorEstudiantes(unidades, gestorEmpleados.getMedicos(), gestorEmpleados.getEnfermeros());
        
        this.iniciar();
    }

    private void iniciar() {
        gestorEmpleados.annadeEmpleado("María García", "Cafetería", Oficio.MEDICO);
        gestorEmpleados.annadeEmpleado("Lucía Torres", "Urgencias", Oficio.ENFERMERO);
        gestorEmpleados.annadeEmpleado("Carlos Díaz", "Unidad Diabetes", Oficio.AUXILIAR);
        gestorEmpleados.annadeEmpleado("Javier Martínez", "Unidad Formación", Oficio.ENFERMERO);
        gestorEmpleados.annadeEmpleado("Ana Gómez", "UCI", Oficio.MEDICO);
        gestorEmpleados.annadeEmpleado("Manuel Sánchez", "Consultas Externas", Oficio.MEDICO);
        gestorEmpleados.annadeEmpleado("Sofía Fernández", "Pruebas Médicas", Oficio.AUXILIAR);
        gestorEmpleados.annadeEmpleado("Elena Pérez", "Unidad Cardiovascular", Oficio.ENFERMERO);
        gestorEmpleados.annadeEmpleado("Fernando Rodríguez", "Administración", Oficio.ADMINISTRATIVO);
        gestorEmpleados.annadeEmpleado("Alejandro López", "Aparcamiento", Oficio.PARQUIMETRERO);
        gestorEmpleados.annadeEmpleado("María Díaz", "Unidad Diabetes", Oficio.ENFERMERO);
        gestorEmpleados.annadeEmpleado("Carlos García", "Pruebas Médicas", Oficio.AUXILIAR);
        gestorEmpleados.annadeEmpleado("Lucía Martínez", "Cafetería", Oficio.CAMARERO);
        gestorEmpleados.annadeEmpleado("Javier Torres", "Hospitalización", Oficio.MEDICO);
        gestorEmpleados.annadeEmpleado("Ana Fernández", "Unidad Formación", Oficio.MEDICO);
        gestorEmpleados.annadeEmpleado("Manuel Pérez", "Hospitalización", Oficio.AUXILIAR);
        gestorEmpleados.annadeEmpleado("Sofía Gómez", "Administración", Oficio.ADMINISTRATIVO);
        gestorEmpleados.annadeEmpleado("Elena Sánchez", "Unidad Cardiovascular", Oficio.MEDICO);
        gestorEmpleados.annadeEmpleado("Fernando García", "Aparcamiento", Oficio.PARQUIMETRERO);
        gestorEmpleados.annadeEmpleado("Alejandro Rodríguez", "UCI", Oficio.AUXILIAR);

        gestorPacientes.annadePaciente("Luis Fernández", "Urgencias");
        gestorPacientes.annadePaciente("Julia Martínez", "UCI");
        gestorPacientes.annadePaciente("Pedro Gómez", "Hospitalización");
        gestorPacientes.annadePaciente("Laura López", "Pruebas Médicas");
        gestorPacientes.annadePaciente("Pablo Rodríguez", "Unidad Cardiovascular");
        gestorPacientes.annadePaciente("Ana Pérez", "Unidad Diabetes");
        gestorPacientes.annadePaciente("Diego Torres", "Urgencias");
        gestorPacientes.annadePaciente("Andrés Fernández", "UCI");
        gestorPacientes.annadePaciente("Natalia García", "Hospitalización");
        gestorPacientes.annadePaciente("Jorge Martínez", "Pruebas Médicas");
        gestorPacientes.annadePaciente("María Jiménez", "Consultas Externas");
        gestorPacientes.annadePaciente("Lucas Ramírez", "Unidad Diabetes");
        gestorPacientes.annadePaciente("Sara Ortiz", "Urgencias");
        gestorPacientes.annadePaciente("Raúl Santos", "UCI");
        gestorPacientes.annadePaciente("Isabel Ruiz", "Hospitalización");
        gestorPacientes.annadePaciente("Óscar Morales", "Pruebas Médicas");
    }

    private void gestiona() {
        
        int opcion = 0;

        do {
            opcion = consola.readInt( """     
                                                        [MENÚ PRINCIPAL]
                                                    Introduzca su siguiente opción:
                                                    1. Gestionar empleados.
                                                    2. Gestionar estudiantes.
                                                    3. Gestionar pacientes.
                                                    0. Salir de la aplicación.
                                                """);
            switch (opcion) {
                case 1:
                    gestorEmpleados.gestionaEmpleados();
                    break;
                case 2:
                    gestorEstudiantes.gestionaEstudiantes();
                    break;
                case 3:
                    gestorPacientes.gestionaPacientes();
                    break;
                case 0:
                    consola.println("Saliendo de la aplicación....");
                    break;

                default:
                    consola.println("Introduzca un valor válido.");
                    break;
            }
        } while (opcion != 0);
    }

}

