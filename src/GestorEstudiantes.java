import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorEstudiantes {

    private ArrayList<Enfermero> enfermeros;
    private ArrayList<Medico> medicos;
    private ArrayList<Unidad> unidades;
    private ArrayList<Estudiante> estudiantes;
    private Consola consola;

    public GestorEstudiantes(ArrayList<Unidad> unidades, ArrayList<Medico> medicos, ArrayList<Enfermero> enfermeros) {
        this.unidades = unidades;
        this.medicos = medicos;
        this.estudiantes = new ArrayList<Estudiante>();
        this.enfermeros = enfermeros;
        consola = Consola.getInstancia();
    }
    public void gestionaEstudiantes() {
        int opcion = 0;
        do {
            opcion = consola.readInt("""
                            [GESTIÓN DE LOS ESTUDIANTES]
                        Introduzca su siguiente opción:
                        1. Dar de alta un estudiante.
                        2. Dar de baja un estudiante.
                        3. Asignar clase.
                        4. Asignar cita médica.
                        5. Listar estudiantes.
                        6. Listar citas de un estudiante.
                        0. Volver atrás.
                    """);
            switch (opcion) {
                case 1:
                    this.annadeEstudiante();
                    break;
                case 2:
                    this.borraEstudiante();
                    break;
                case 3:
                    consola.println("Funcionalidad no implementada");
                    // this.asignarClase();
                    break;
                case 4:
                    this.asignarCita();
                    break;
                case 5:
                    this.listarEstudiantes();
                    break;
                case 6:
                    this.listarCitasEstudiantes();
                    break;
                case 0:
                    consola.println("Volviendo atrás...");
                    break;

                default:
                    consola.println("Introduzca un valor válido.");
                    break;
            }
        } while (opcion != 0);

    }

    private void listarCitasEstudiantes() {
        
        consola.println("Escriba el nombre del estudiante:");
        String nombreEstu = this.readNombre();
        String apellidoEstu = this.readApellido();
        if (this.estudianteExiste(nombreEstu, apellidoEstu) == false) {
            consola.println("No existe ningún estudiante con ese nombre");
        }
        Estudiante estudiante = this.getEstudiante(nombreEstu, apellidoEstu);
        estudiante.getAgenda().listarCitas();
    }

    private void listarEstudiantes() {
        consola.println("Encontrados " + estudiantes.size() + " estudiantes en el sistema.");
        int i = 1;
        for (Estudiante estudiante : estudiantes){
            consola.println(i + ". " + estudiante.getNombreApellido());
            i++;
        }
        consola.println();
    }

    private void asignarCita() {
        consola.println("Escriba el nombre del estudiante:");
        String nombreEstu = this.readNombre();
        String apellidoEstu = this.readApellido();
        if (this.estudianteExiste(nombreEstu, apellidoEstu) == false){
            consola.println("No existe ningún estudiante con ese nombre");
        }
        Estudiante estudiante = this.getEstudiante(nombreEstu, apellidoEstu);
        consola.println("Escriba el nombre del profesional al que quiere asignar al estudiante:");
        String nombreProf = this.readNombre();
        String apellidoProf = this.readApellido();
        int opcion = 0;
        do {
            opcion = consola.readInt("""
                        ¿La asignación será con un médico o con un enfermero?
                        1. Médico.
                        2. Enfermero.
                        """);
        } while (opcion < 1 || opcion > 2);
        switch (opcion) {
            case 1:
                consola.println("A continuación se listarán las citas de este médico. Escoja una:");
                Medico medico = this.getMedico(nombreProf, apellidoProf);
                medico.getAgenda().listarCitas();
                estudiante.agregarCita(medico.getAgenda().buscarCitaPorFechaHora(this.getFechaHoraDeCita()));
                break;
            case 2:
                consola.println("A continuación se listarán las citas de este enfermero. Escoja una:");
                Enfermero enfermero = this.getEnfermero(nombreProf, apellidoProf);
                enfermero.getAgenda().listarCitas();
                estudiante.agregarCita(enfermero.getAgenda().buscarCitaPorFechaHora(this.getFechaHoraDeCita()));

            default:
                break;
        }
    }

    private LocalDateTime getFechaHoraDeCita() {
        LocalDate fecha = null;
        LocalTime hora = null;
        while (fecha == null) {
            try {
                String fechaStr = consola.readString("Introduce la fecha de la cita (yyyy-MM-dd):");
                fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                consola.println("Formato de fecha no válido. Por favor, inténtalo de nuevo.");
            }
        }

        while (hora == null) {
            try {
                String horaStr = consola.readString("Introduce la hora de la cita (HH:mm):");
                hora = LocalTime.parse(horaStr, DateTimeFormatter.ISO_LOCAL_TIME);
            } catch (DateTimeParseException e) {
                consola.println("Formato de hora no válido. Por favor, inténtalo de nuevo.");
            }
        }

        LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
        return fechaHora;
    }
    private Enfermero getEnfermero(String nombreProf, String apellidoProf) {
        for (Enfermero enfermero : enfermeros){
            if (enfermero.getNombre().equals(nombreProf) && enfermero.getApellido().equals(apellidoProf)){
                return enfermero;
            }
        }
        return null;
    }

    private Medico getMedico(String nombreProf, String apellidoProf) {
        for (Medico medico : medicos) {
            if (medico.getNombre().equals(nombreProf) && medico.getApellido().equals(apellidoProf)) {
                return medico;
            }
        }
        return null;
    }

    private void borraEstudiante() {
        
        String nombre = this.readNombre();
        String apellido = this.readApellido();
        if (this.estudianteExiste(nombre, apellido) == false) {
            consola.println("El estudiante no existe en el sistema");
            return;
        }
        
        estudiantes.remove(this.getEstudiante(nombre, apellido));
    }
    private Estudiante getEstudiante(String nombre, String apellido) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equals(nombre) && estudiante.getApellido().equals(apellido)) {
                return estudiante;
            }
        }
        return null;
    }

    private void annadeEstudiante() {
        
        String nombre = this.readNombre();
        String apellido = this.readApellido();
        if (this.estudianteExiste(nombre, apellido) == true) {
            consola.println("El estudiante ya existe en el sistema");
            return;
        }
        estudiantes.add(new Estudiante(nombre, apellido));
    }

    private boolean estudianteExiste(String nombre, String apellido) {
        for (Estudiante estudiante : estudiantes){
            if (estudiante.getNombre().equals(nombre) && estudiante.getApellido().equals(apellido)){
                return true;
            }
        }
        return false;
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
}
