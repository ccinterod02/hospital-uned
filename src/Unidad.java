import java.util.LinkedList;

abstract class Unidad {
    protected Consola consola;
    protected String nombreUnidad;
    protected LinkedList<Empleado> asignados;

    public Unidad(String nombreUnidad){
        this.nombreUnidad = nombreUnidad;
        asignados = new LinkedList<Empleado>();
        consola = Consola.getInstancia();
    }

    public Empleado buscaEmpleado(String nombre, String apellido){
        for (int i = 0; i < asignados.size(); i++){
            if (asignados.get(i).getNombre().equals(nombre) && asignados.get(i).getApellido().equals(apellido)){
                return asignados.get(i);
            }
        }
        return null;
    }

    public Empleado buscaEmpleado(Empleado empleado){
        for (int i = 0; i < asignados.size(); i++) {
            if (empleado == asignados.get(i)) {
                return asignados.get(i);
            }
        }
        return null;
    }

    public String getNombreUnidad(){
        return nombreUnidad;
    }

    public LinkedList<Empleado> getEmpleados(){
        return asignados;
    }


    public void asignaEmpleado(Empleado empleado){
        asignados.add(empleado);
    }

    public void desAsignaEmpleado(String nombre, String apellido){
        asignados.remove(this.buscaEmpleado(nombre, apellido));
    }

    public boolean estaAsignado(Empleado empleado){
        for (int i = 0; i < asignados.size(); i++){
            if (asignados.get(i) == empleado){
                return true;
            }
        }
        return false;
    }

    public String muestraNombre(){
        return this.capitalize(nombreUnidad);        
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    protected void altaPaciente(Paciente paciente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altaPaciente'");
    }

    protected void desasignaPaciente(Paciente paciente){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altaPaciente'");    
    }

    protected void darCita(Paciente paciente, String nombreProf, String apellidoProf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'darCita'");
    }


}
