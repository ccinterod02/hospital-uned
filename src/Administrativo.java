
public class Administrativo extends Empleado{

    public Administrativo(String nombre, String apellido, Oficio oficio) {
        super(nombre, apellido, oficio);
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                ", oficio='" + this.getOficio().toString() + '\'' +
                '}';
    }
}
