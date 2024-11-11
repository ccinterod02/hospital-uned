
public class Camarero extends Empleado{
    
   
    public Camarero(String nombre, String apellido, Oficio oficio) {
        super(nombre, apellido, oficio);
    }

    @Override
    public String toString() {
        return "Camarero{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                ", oficio='" + this.getOficio().toString() + '\'' +
                '}';
    }
}
