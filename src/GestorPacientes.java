import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorPacientes {

    Consola consola;
    private ArrayList<Unidad> unidades;
    private ArrayList<Empleado> empleados;
    private ArrayList<Paciente> pacientes;
    
    public GestorPacientes(ArrayList<Unidad> unidades, ArrayList<Empleado> empleados, ArrayList<Paciente> pacientes){
        consola = Consola.getInstancia();
        this.pacientes = pacientes;
        this.unidades = unidades;
        this.empleados = empleados;
    }

    public void gestionaPacientes() {
        
        int opcion = 0;

        do {
            opcion = consola.readInt("""
                            [GESTIÓN DE LOS PACIENTES]
                        Introduzca su siguiente opción:
                        1. Dar de alta un paciente.
                        2. Dar de baja un paciente.
                        3. Asignar cita a un paciente.
                        4. Listar pacientes.
                        0. Volver atrás.
                    """);

            switch (opcion) {
                case 1:
                    this.altaPaciente();
                    break;
                case 2:
                    this.bajaPaciente();
                    break;
                case 3:
                    this.asignarCita();
                    break;
                case 4:
                    this.listaPacientes();
                    break;
                case 0:
                    consola.println("Volviendo atrás....");
                    break;

                default:
                    consola.println("Introduzca un valor válido.");
                    break;
            }
        } while (opcion != 0);
    }

    private void asignarCita() {
        String nombre = readNombre();
        String apellido = readApellido();
        Paciente paciente = this.buscaPaciente(nombre, apellido);
        if (paciente == null){
            consola.println("No existe un paciente en el sistema al que poder asignar una cita.");
            return;
        }
        if (paciente.esCitable() == false){
            consola.println("No se puede dar una cita a ese paciente dado que no se encuentra en la unidad correcta (Consultas Externas, Unidad Especializada, Pruebas Médicas u Hospitalización).");
            return;
        }
        consola.println("Estos son los profesionales con los que puede concretar una cita en esta unidad [" + paciente.getUnidadAsociada().getNombreUnidad() + "]");
        LinkedList<Empleado> profesionales = paciente.getUnidadAsociada().getEmpleados();
        int i = 1;
        for (Empleado profesional : paciente.getUnidadAsociada().getEmpleados()){
            if (profesional.getOficio() == Oficio.MEDICO || profesional.getOficio() == Oficio.ENFERMERO){
                consola.println(i + ". " + profesional.getNombreApellido() + " [" + profesional.getOficio().toString() + "]");
            }
            i++;
        }
        int opcion = 0;
        do {
            opcion = consola.readInt("""
                            [ASIGNAR CITA]
                        Introduzca su siguiente opción:
                        1. Dar cita para una consulta externa (Cardiología, Dermatología, Oncología, etc...).
                        2. Dar cita para una consulta en Hospitalización.
                        3. Dar cita para Unidad Diabetes.
                        4. Dar cita para Unidad Cardiovascular.
                    """);
        } while (opcion < 0 && opcion > 4);
        consola.println("Introduzca el profesional con el que tendrá la cita:");
        String nombreProf = this.readNombre();
        String apellidoProf = this.readApellido();
        switch (opcion) {
            case 1:
                this.getUnidad("Consultas Externas").darCita(paciente, nombreProf, apellidoProf);
                break;
            case 2:
                this.getUnidad("Hospitalización").darCita(paciente, nombreProf, apellidoProf);
                break;
            case 3:
                this.getUnidad("Unidad Diabetes").darCita(paciente, nombreProf, apellidoProf);
                break;
            case 4:
                this.getUnidad("Unidad Cardiovascular").darCita(paciente, nombreProf, apellidoProf);
                break;
            default:
                break;
        }
    }

    private void listaPacientes() {
        consola.println("Encontrados " + pacientes.size() + " pacientes en el sistema:");
        for (int i = 0; i < pacientes.size(); i++){
            consola.println(i + ". " + pacientes.get(i).getNombreApellido() + " [" + pacientes.get(i).getUnidadAsociada().getNombreUnidad() +"]");
        }
        consola.println();
    }

    private void altaPaciente() {
        String nombre = readNombre();
        String apellido = readApellido();
        int opcion = 0;
        do {
            opcion = consola.readInt("""
                            [ALTA DE UN PACIENTE]
                        Introduzca su siguiente opción:
                        1. Dar de alta en Hospitalización.
                        2. Dar de alta en Consultas Extenas.
                        3. Dar de alta en UCI.
                        4. Dar de alta en Pruebas Médicas.
                        5. Dar de alta en Unidad Diabetes.
                        5. Dar de alta en Unidad Cardiovascular.
                        6. Dar de alta en Urgencias.
                        0. Volver atrás.
                    """);
        } while (opcion < 0 && opcion > 6);
        Paciente nuevoPaciente = new Paciente(nombre, apellido);
        Unidad unidad;
        switch(opcion){
            case 0:
                break;
            case 1:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("Hospitalización");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break;
            case 2:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("Consultas Externas");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break;
            case 3:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("UCI");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break;
            case 4:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("Pruebas Médicas");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break;
            case 5:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("Unidad Diabetes");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break;
            case 6:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("Unidad Cardiovascular");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break;
            case 7:
                pacientes.add(nuevoPaciente);
                unidad = this.getUnidad("Urgencias");
                nuevoPaciente.asociaUnidad(unidad);
                unidad.altaPaciente(nuevoPaciente);
                break; 
        }
    }

    private void bajaPaciente() {
        String nombre = this.readNombre();
        String apellido = this.readApellido();
        Paciente pacienteBuscado = this.buscaPaciente(nombre, apellido);
        if (pacienteBuscado == null){
            consola.println("No existe un paciente con ese nombre en el sistema.");
            return;
        }
        consola.println("Se ha borrado al paciente [" + pacienteBuscado.getNombreApellido() + "] de la unidad [" + pacienteBuscado.getUnidadAsociada().getNombreUnidad() + "]");
        pacienteBuscado.getUnidadAsociada().desasignaPaciente(pacienteBuscado);
        pacientes.remove(pacienteBuscado);
    }

    private Paciente buscaPaciente(String nombre, String apellido) {
        for (Paciente paciente : pacientes){
            if (paciente.getNombre().equals(nombre) && paciente.getApellido().equals(apellido)){
                return paciente;
            }
        }
        return null;
    }

    private String readNombre() {
        String nombre;
        String regex = "^[\\p{L}]+$";
        Pattern patronBusqueda = Pattern.compile(regex);
        Matcher matcher;

        do {
            nombre = consola.readString("Introduzca nombre:");
            matcher = patronBusqueda.matcher(nombre);
            if (!matcher.matches()) {
                consola.println("El nombre introducido no es válido");
            }
        } while (!matcher.matches());

        return nombre;
    }

    private String readApellido() {
        String apellido;
        String regex = "^[\\p{L}]+$";
        Pattern patronBusqueda = Pattern.compile(regex);
        Matcher matcher;

        do {
            apellido = consola.readString("Introduzca primer apellido:");
            matcher = patronBusqueda.matcher(apellido);
            if (!matcher.matches()) {
                consola.println("El apellido introducido no es válido");
            }
        } while (!matcher.matches());

        return apellido;
    }

    private Unidad getUnidad(String nombreUnidad) {
        for (int i = 0; i < unidades.size(); i++) {
            if (unidades.get(i).getNombreUnidad().equals(nombreUnidad)) {
                return unidades.get(i);
            }
        }
        return null;
    }

    public void annadePaciente(String nombreApellido, String unidadStr) {
        
        String[] partes = nombreApellido.split(" ");
        String nombre = partes[0];
        String apellido = partes[1];
        Unidad unidad = this.getUnidad(unidadStr);
        Paciente nuevoPaciente = new Paciente(nombre, apellido);
        pacientes.add(nuevoPaciente);
        unidad.altaPaciente(nuevoPaciente);
        nuevoPaciente.asociaUnidad(unidad);
        
    }
}
