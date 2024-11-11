import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Paciente extends Persona{
    private Unidad unidadAsociada;
    private String idPaciente;

    public Paciente(String nombre, String apellido) {
        super(nombre, apellido);
        this.idPaciente = generarIDUnico();
    }

    public void asociaUnidad(Unidad unidad){
        this.unidadAsociada = unidad;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                '}';
    }

    public boolean esIgualA(Paciente otroPaciente) {
        return this.nombre.equals(otroPaciente.getNombre()) &&
                this.apellido.equals(otroPaciente.getApellido()) &&
                this.idPaciente.equals(otroPaciente.getIdPaciente());
    }

    public static boolean esIDValido(String idPaciente) {
        return idPaciente != null && idPaciente.matches("[a-zA-Z0-9]+") && idPaciente.length() == 4;
    }

    public static String generarIDUnico() {
        String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String numerosAleatorios = String.format("%03d", new Random().nextInt(1000));
        return fechaActual + numerosAleatorios;
    }

    public void actualizarInformacion(String nombre, String apellido, String idPaciente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idPaciente = idPaciente;
    }

    public String obtenerInformacionDetallada() {
        return "Paciente: " + nombre + " " + apellido + " (ID: " + idPaciente + ")";
    }

    public Unidad getUnidadAsociada() {
        return unidadAsociada;
    }

    public boolean esCitable() {
        String unidadStr = this.getUnidadAsociada().getNombreUnidad();
        
        if (unidadStr.equals("Hospitalización") 
                                                            || unidadStr.equals("Unidad Cardiovascular")
                                                            || unidadStr.equals("Unidad Diabetes")
                                                            || unidadStr.equals("Consultas Externas")
                                                            || unidadStr.equals("Pruebas Médicas")){
                                                                return true;
        }
        return false;
    }
}
