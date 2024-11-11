import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class UnidadDiabetes extends Unidad{

    private ArrayList<Paciente> pacientes;
    
    public UnidadDiabetes(String nombreUnidad) {
        super(nombreUnidad);
        pacientes = new ArrayList<Paciente>();
    }

    @Override
    protected void altaPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    protected void desasignaPaciente(Paciente paciente) {
        if (pacientes.contains(paciente) == false) {
            consola.println("No existe ese paciente en esta unidad.");
            return;
        }
        pacientes.remove(paciente);
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
