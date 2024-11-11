import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Agenda {
    Consola consola;

    private Set<Cita> citas;

    public Agenda() {
        consola = Consola.getInstancia();
        this.citas = new HashSet<>();
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void listarCitas() {
        
        if (citas.isEmpty()) {
            consola.println("No hay citas programadas.");
        } else {
            for (Cita cita : citas) {
                consola.println(cita.toString());
            }
        }
    }
    
    public Cita buscarCitaPorFechaHora(LocalDateTime fechaHora) {
        return citas.stream()
                .filter(c -> c.getFechaHora().equals(fechaHora))
                .findFirst()
                .orElse(null);
    }

    public void eliminarCita(Cita cita) {
        citas.remove(cita);
    }
}
