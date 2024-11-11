
public class Auxiliar extends Empleado{

    public Auxiliar(String nombre, String apellido, Oficio oficio) {
        super(nombre, apellido, oficio);
    }

    @Override
    public String toString() {
        return "Auxiliar{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                ", oficio='" + this.getOficio().toString() + '\'' +
                '}';
    }
}
