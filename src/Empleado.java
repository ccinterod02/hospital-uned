
abstract class Empleado extends Persona {
    private Oficio oficio;
    protected Consola consola;

    public Empleado(String nombre, String apellido, Oficio oficio) {
        super(nombre, apellido);
        this.oficio = oficio;
        consola = Consola.getInstancia();
    }
    public Oficio getOficio(){
        return oficio;
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                ", oficio='" + this.getOficio().toString() + '\'' + 
                '}';
    }
    public void agregarCita(Cita cita) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarCita'");
    }
}
