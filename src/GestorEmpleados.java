import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestorEmpleados {

    private Consola consola;
    private ArrayList<Unidad> unidades;
    private ArrayList<Empleado> empleados;
    private ArrayList<Medico> medicos;
    
    private ArrayList<Enfermero> enfermeros;
    
    private ArrayList<Auxiliar> auxiliares;
    private ArrayList<Camarero> camareros;
    private ArrayList<Administrativo> administrativos;
    private ArrayList<Parquimetrero> parquimetreros;
    private ArrayList<Tecnico> tecnicos;
    
    
    public GestorEmpleados(ArrayList<Unidad> unidades, ArrayList<Empleado> empleadosGeneral) {
        consola = Consola.getInstancia();
        
        this.unidades = unidades;
        this.empleados = empleadosGeneral;
        this.medicos = new ArrayList<Medico>();
        this.enfermeros = new ArrayList<Enfermero>();
        this.auxiliares = new ArrayList<Auxiliar>();
        this.camareros = new ArrayList<Camarero>();
        this.administrativos = new ArrayList<Administrativo>();
        this.parquimetreros = new ArrayList<Parquimetrero>();
        this.tecnicos = new ArrayList<Tecnico>();
        
    }
    public ArrayList<Medico> getMedicos() {
        return medicos;
    }
    public ArrayList<Enfermero> getEnfermeros() {
        return enfermeros;
    }

    public void gestionaEmpleados() {
        int opcion = 0;
        do {
            opcion = consola.readInt("""
                            [GESTIÓN DE LOS EMPLEADOS]
                        Introduzca su siguiente opción:
                        1. Añadir empleado.
                        2. Buscar un empleado.
                        3. Eliminar un empleado.
                        4. Listar empleados del hospital.
                        5. Listar citas de los empleados (médico o enfermero).
                        0. Volver atrás.
                    """);
            switch (opcion) {
                case 1:
                    this.annadeEmpleado();
                    break;
                case 2:
                    this.muestraInfoEmpleado();
                    break;
                case 3:
                    this.eliminaEmpleado();
                    break;
                case 4:
                    this.listaEmpleadosGeneral();
                    break;
                case 5:
                    this.listarCitasProfesionales();
                    break;
                case 0:
                    consola.println("Volviendo atrás...");
                    break;

                default:
                    consola.println("Introduzca un valor válido.");
                    break;
            }
        } while (opcion != 0);
    }

    private void listarCitasProfesionales() {
        consola.println("[ENFERMEROS]");
        int i = 1;
        for (Enfermero enfermero : enfermeros){
            consola.println(i + ":" + enfermero.getNombreApellido());
            enfermero.getAgenda().listarCitas();
            i++;
        }
        consola.println();
        consola.println("[MÉDICOS]");
        for (Medico medico : medicos){
            consola.println(i + ":" + medico.getNombreApellido());
            medico.getAgenda().listarCitas();
            i++;
        }
        consola.println();
        
    }

    private void listaEmpleadosGeneral() {
        consola.println("Encontrados " + empleados.size() + " empleados en este hospital:");
        for (int i = 0; i < empleados.size(); i++) {
            consola.println(i + ". " + empleados.get(i).getNombreApellido() + " [" + empleados.get(i).getOficio().toString() + "]");
        }
        consola.println();
    }

    public void muestraInfoEmpleado() {
        String nombre = this.readNombre();
        String apellido = this.readApellido();
        Empleado empleado = this.getEmpleadoGeneral(nombre, apellido);
        if (empleado == null) {
            consola.println("No existe ese empleado en el sistema");
            return;
        }
        consola.println("[" + empleado.getNombreApellido() + "] existe en el sistema y realiza labores de [" + empleado.getOficio().toString() + "] en la unidad [" + getUnidadAsociada(nombre, apellido).getNombreUnidad() + "]");
    }

    private String readUnidad() {
        String unidad;
        String regex = "^[\\p{L}]+$";
        Pattern patronBusqueda = Pattern.compile(regex);
        Matcher matcher;
        do {
            unidad = consola.readString("Introduzca la unidad:");
            matcher = patronBusqueda.matcher(unidad);
            if (matcher.matches() == false) {
                consola.println("El nombre de la unidad introducida no es válido.");
            }
        } while (matcher.matches() == false);

        return unidad;
    }

    public void annadeEmpleado() {
        String nombre = this.readNombre();
        String apellido = this.readApellido();
        if (this.empleadoExiste(nombre, apellido) == true) {
            consola.println("El empleado ya existe en el sistema");
            return;
        }

        String unidad = this.getTipoUnidad();
        Oficio opcion = this.getTipoEmpleado(unidad);
        switch (opcion) {
            case MEDICO:
                Medico nuevoMedico = new Medico(nombre, apellido, Oficio.MEDICO);
                empleados.add(nuevoMedico);
                this.getUnidad(unidad).asignaEmpleado(nuevoMedico);
                medicos.add(nuevoMedico);
                break;
            case ENFERMERO:
                Enfermero nuevoEnfermero = new Enfermero(nombre, apellido, Oficio.ENFERMERO);
                empleados.add(nuevoEnfermero);
                this.getUnidad(unidad).asignaEmpleado(nuevoEnfermero);
                enfermeros.add(nuevoEnfermero);
            case AUXILIAR:
                Auxiliar nuevoAuxiliar = new Auxiliar(nombre, apellido, Oficio.AUXILIAR);
                empleados.add(nuevoAuxiliar);
                this.getUnidad(unidad).asignaEmpleado(nuevoAuxiliar);
                auxiliares.add(nuevoAuxiliar);
                break;
            case ADMINISTRATIVO:
                Administrativo nuevoAdministrativo = new Administrativo(nombre, apellido, Oficio.ADMINISTRATIVO);
                empleados.add(nuevoAdministrativo);
                this.getUnidad(unidad).asignaEmpleado(nuevoAdministrativo);
                administrativos.add(nuevoAdministrativo);
                break;
            case CAMARERO:
                Camarero nuevoCamarero = new Camarero(nombre, apellido, Oficio.CAMARERO);
                empleados.add(nuevoCamarero);
                this.getUnidad(unidad).asignaEmpleado(nuevoCamarero);
                camareros.add(nuevoCamarero);
                break;
            case PARQUIMETRERO:
                Parquimetrero nuevoParquimetrero = new Parquimetrero(nombre, apellido, Oficio.PARQUIMETRERO);
                empleados.add(nuevoParquimetrero);
                this.getUnidad(unidad).asignaEmpleado(nuevoParquimetrero);
                parquimetreros.add(nuevoParquimetrero);
                break;
            case TECNICO:
                Tecnico nuevoTecnico = new Tecnico(nombre, apellido, Oficio.TECNICO);
                empleados.add(nuevoTecnico);
                this.getUnidad(unidad).asignaEmpleado(nuevoTecnico);
                tecnicos.add(nuevoTecnico);
                break;
            default:
                consola.println("Indeterminado");
                break;
        }
    }

    private String getTipoUnidad() {
        int opcion = 0;
        do {
            opcion = consola.readInt("""
                        ¿A qué unidad quieres asignar?:
                        1.  Administración.
                        2.  Urgencias.
                        3.  Consultas Externas.
                        4.  Unidad Formación.
                        5.  Unidad Diabetes.
                        6.  Unidad Cardiovascular.
                        7.  Hospitalización.
                        8.  UCI.
                        9.  Pruebas Médicas.
                        10. Cafetería.
                        11. Aparcamiento.
                    """);
        } while (opcion < 1 || opcion > 11);
        switch (opcion) {
            case 1:
                return "Administración" ;
            case 2:
                return "Urgencias" ;
            case 3:
                return "Consultas Externas" ;
            case 4:
                return "Unidad Formación" ;
            case 5:
                return "Unidad Diabetes" ;
            case 6:
                return "Unidad Cardiovascular" ;
            case 7:
                return "Hospitalización" ;
            case 8:
                return "UCI" ;
            case 9:
                return "Pruebas Médicas" ;
            case 10:
                return "Cafetería" ;
            case 11:
                return "Aparcamiento" ;
                
            default:
                break;
        }
        return "";
    }

    // para el metodo iniciar
    public void annadeEmpleado(String nombreApellido, String unidadStr, Oficio oficio){
        String[] partes = nombreApellido.split(" ");
        String nombre = partes[0];
        String apellido = partes[1];
        Unidad unidad = this.getUnidad(unidadStr);
        
        switch (oficio) {
            case MEDICO:
                Medico nuevoMedico = new Medico(nombre, apellido, Oficio.MEDICO);
                empleados.add(nuevoMedico);
                unidad.asignaEmpleado(nuevoMedico);
                medicos.add(nuevoMedico);
                break;
            case ENFERMERO:
                Enfermero nuevoEnfermero = new Enfermero(nombre, apellido, Oficio.ENFERMERO);
                empleados.add(nuevoEnfermero);
                unidad.asignaEmpleado(nuevoEnfermero);
                enfermeros.add(nuevoEnfermero);
                break;
            case AUXILIAR:
                Auxiliar nuevoAuxiliar = new Auxiliar(nombre, apellido, Oficio.AUXILIAR);
                empleados.add(nuevoAuxiliar);
                unidad.asignaEmpleado(nuevoAuxiliar);
                auxiliares.add(nuevoAuxiliar);
                break;
            case CAMARERO:
                Camarero nuevocaCamarero = new Camarero(nombre, apellido, Oficio.CAMARERO);
                empleados.add(nuevocaCamarero);
                unidad.asignaEmpleado(nuevocaCamarero);
                camareros.add(nuevocaCamarero);
                break;
            case ADMINISTRATIVO:
                Administrativo nuevoAdministrativo = new Administrativo(nombre, apellido, Oficio.ADMINISTRATIVO);
                empleados.add(nuevoAdministrativo);
                unidad.asignaEmpleado(nuevoAdministrativo);
                administrativos.add(nuevoAdministrativo);
                break;
            case PARQUIMETRERO:
                Parquimetrero nuevoParquimetrero = new Parquimetrero(nombre, apellido, Oficio.PARQUIMETRERO);
                empleados.add(nuevoParquimetrero);
                unidad.asignaEmpleado(nuevoParquimetrero);
                parquimetreros.add(nuevoParquimetrero);
                break;
            case TECNICO:
                Tecnico nuevoTecnico = new Tecnico(nombre, apellido, Oficio.TECNICO);
                empleados.add(nuevoTecnico);
                unidad.asignaEmpleado(nuevoTecnico);
                tecnicos.add(nuevoTecnico);
                break;
            default:
                consola.println("Indeterminado");
                break;
        }
    }

    private Oficio getTipoEmpleado(String unidad) {
        int opcion = 0;
        switch (unidad) {
            case "Urgencias":
            case "Consultas Externas":
            case "Unidad Diabetes":
            case "Unidad Cardiovascular":
            case "Hospitalización":
            case "UCI":
            case "Pruebas Médicas":
                do {
                    opcion = consola.readInt("""
                                ¿Qué tipo de empleado quieres añadir?:
                                1. Médico.
                                2. Enfermero.
                                3. Auxiliar.
                            """);
                } while (opcion < 1 || opcion > 3);
                switch (opcion) {
                    case 1:
                        return Oficio.MEDICO;
                    case 2:
                        return Oficio.ENFERMERO;
                    case 3:
                        return Oficio.AUXILIAR;
                    default:
                        break;
                }
                break;
            case "Administración":
                do {
                    opcion = consola.readInt("""
                                ¿Qué tipo de empleado quieres añadir?:
                                1. Administrativo.
                                2. Auxiliar.
                                3. Técnico.
                            """);
                } while (opcion < 1 || opcion > 3);
                switch (opcion) {
                    case 1:
                        return Oficio.ADMINISTRATIVO;
                    case 2:
                        return Oficio.AUXILIAR;
                    case 3:
                        return Oficio.TECNICO;
                    default:
                        break;
                }
                break;
            case "Cafetería":
                do {
                    opcion = consola.readInt("""
                                ¿Qué tipo de empleado quieres añadir?:
                                1. Camarero.
                            """);
                } while (opcion != 1);
                switch (opcion) {
                    case 1:
                        return Oficio.CAMARERO;
                    default:
                        break;
                }
                break;
            case "Aparcamiento":
                do {
                    opcion = consola.readInt("""
                                ¿Qué tipo de empleado quieres añadir?:
                                1. Parquimetrero.
                            """);
                } while (opcion != 1);
                switch (opcion) {
                    case 1:
                        return Oficio.PARQUIMETRERO;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return Oficio.POLIVALENTE;
        
    }

    private boolean empleadoExiste(String nombre, String apellido) {
        if (getEmpleadoGeneral(nombre, apellido) != null){
            return true;
        }
        return false;
    }

    private boolean unidadExiste(String unidad) {
        if (this.getUnidad(unidad) != null){
            return true;
        }
        return false;
    }

    public void eliminaEmpleado(){
        String nombre = this.readNombre();
        String apellido = this.readApellido();
        Empleado empleado = this.getEmpleadoGeneral(nombre, apellido);
        if (empleado == null){
            consola.println("No existe ese empleado en el sistema");
            return;
        }
        empleados.remove(empleados.indexOf(empleado));
        Unidad unidad = this.getUnidadAsociada(nombre, apellido);
        unidad.desAsignaEmpleado(nombre, apellido);
        consola.println("Eliminando a " + empleado.getNombreApellido() + " del sistema y de la unidad " + unidad.muestraNombre());
    }

    public Unidad getUnidadAsociada(String nombre, String apellido){
        for (Unidad unidad : unidades){
            if (unidad.buscaEmpleado(nombre, apellido) != null){
                return unidad;
            }
        }
        return null;
    }

    public Empleado getEmpleadoGeneral(String nombre, String apellido) {
        for (Empleado empleado : empleados){
            if (empleado.getNombre().equals(nombre) && empleado.getApellido().equals(apellido)){
                return empleado;
            }
        }
        return null;
    }

    private String readNombre() {
        String nombre;
        String regex = "^[\\p{L}]+$";
        Pattern patronBusqueda = Pattern.compile(regex);
        Matcher matcher;

        do {
            nombre = consola.readString("Introduzca nombre:");
            matcher = patronBusqueda.matcher(nombre);
            if (!matcher.matches()) {
                consola.println("El nombre introducido no es válido");
            }
        } while (!matcher.matches());

        return nombre;
    }

    private String readApellido() {
        String apellido;
        String regex = "^[\\p{L}]+$";
        Pattern patronBusqueda = Pattern.compile(regex);
        Matcher matcher;

        do {
            apellido = consola.readString("Introduzca primer apellido:");
            matcher = patronBusqueda.matcher(apellido);
            if (!matcher.matches()) {
                consola.println("El apellido introducido no es válido");
            }
        } while (!matcher.matches());

        return apellido;
    }

    private Unidad getUnidad(String nombreUnidad) {
        for (int i = 0; i < unidades.size(); i++) {
            if (unidades.get(i).getNombreUnidad().equals(nombreUnidad)) {
                return unidades.get(i);
            }
        }
        return null;
    }

    private void limpiaConsola(){
        for (int i = 0; i < 50; i++){
            consola.println();
        }
    }
}
