import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Hospitalizacion extends Unidad{

    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Paciente> pacientes;

    public Hospitalizacion(String nombreUnidad) {
        super(nombreUnidad);
        habitaciones = new ArrayList<Habitacion>();
        for (int i = 0; i < 90; i++){
            habitaciones.add(new Habitacion());
        }
        pacientes = new ArrayList<Paciente>();
    }

    @Override
    protected void altaPaciente(Paciente paciente) {
        Habitacion habitacionLibre = this.getHabitacionLibre();
        if (habitacionLibre == null){
            consola.println("No hay habitaciones libres, el paciente tendrá que irse a otro hospital o esperar.");
            return;
        }
        habitacionLibre.setOcupada(true);
        pacientes.add(paciente);
    }

    @Override
    protected void desasignaPaciente(Paciente paciente) {
        if (pacientes.contains(paciente) == false){
            consola.println("No existe ese paciente en esta unidad.");
            return;
        }
        pacientes.remove(paciente);
        this.liberaHabitacion();
    }

    private Habitacion getHabitacionLibre(){
        for (Habitacion habitacion : habitaciones){
            if (habitacion.isOcupada() == false){
                return habitacion;
            }
        }
        return null;
    }

    private void liberaHabitacion(){
        for (Habitacion habitacion : habitaciones){
            if (habitacion.isOcupada() == true){
                habitacion.setOcupada(false);
                return;
            }
        }
    }

    @Override
    protected void darCita(Paciente paciente, String nombreProf, String apellidoProf) {
        if (!pacientes.contains(paciente)) {
            consola.println("No existe ese paciente en esta unidad.");
            return;
        }

        Empleado profesional = this.buscaEmpleado(nombreProf, apellidoProf);
        if (profesional == null) {
            consola.println("No existe ningún médico ni enfermero en esta unidad con ese nombre.");
            return;
        }

        if (!(profesional instanceof Medico || profesional instanceof Enfermero)){
            consola.println("Solo se puede dar a cita a un médico o a un enfermero.");
            return;
        }
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
        Cita cita = new Cita(paciente, fechaHora);

        profesional.agregarCita(cita);
    }

}
