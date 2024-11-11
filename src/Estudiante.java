import java.util.Set;

public class Estudiante extends Persona {

    private Agenda agenda;
    private Consola consola;

    public Estudiante(String nombre, String apellido) {
        super(nombre, apellido);
        consola = Consola.getInstancia();
        this.agenda = new Agenda();
    }

    public void agregarCita(Cita cita) {
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

    public Agenda getAgenda() {
        return agenda;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                '}';
    }
}
