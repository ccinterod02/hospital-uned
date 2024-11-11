import java.util.ArrayList;

public class UnidadFormacion extends Unidad{

    private ArrayList<Estudiante> estudiantes;

    public UnidadFormacion(String nombreUnidad) {
        super(nombreUnidad);
        
        estudiantes = new ArrayList<Estudiante>(); 
    }

}
