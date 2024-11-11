import java.util.ArrayList;

public class PruebasMedicas extends Unidad{

    private ArrayList<Paciente> pacientes;
    
    public PruebasMedicas(String nombreUnidad) {
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
}
