import java.time.LocalTime;
import java.util.Set;

public class Enfermero extends Empleado{
    
    private Agenda agenda;

    public Enfermero(String nombre, String apellido, Oficio oficio) {
        super(nombre, apellido, oficio);
        agenda = new Agenda();
    }

    
    @Override
    public void agregarCita(Cita cita) {
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horafin = LocalTime.of(14, 0);

        if (cita.getHora().isBefore(horaInicio) || cita.getHora().isAfter(horafin)) {
            consola.println("La cita debe estar entre las 8:00 y las 14:00.");
            return; 
        }

        for (Cita c : agenda.getCitas()) {
            if (c.getFecha().equals(cita.getFecha()) && c.getHora().equals(cita.getHora())) {
                consola.println("Ya hay una cita a esa hora.");
                return; 
            }
        }

        agenda.agregarCita(cita);
    }

    public void listarCitas() {
        Set<Cita> citas = agenda.getCitas();
        if (citas.isEmpty()) {
            consola.println("No hay citas programadas.");
        } else {
            for (Cita cita : citas) {
                consola.println(cita.toString());
            }
        }
    }
    
    public Agenda getAgenda(){
        return agenda;
    }
    
    @Override
    public String toString() {
        return "Enfermero{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                ", oficio='" + this.getOficio().toString() + '\'' +
                '}';
    }
}
