public enum Oficio {
    MEDICO("Médico"),
    ENFERMERO("Enfermero"),
    AUXILIAR("Auxiliar"),
    ADMINISTRATIVO("Administrativo"),
    CAMARERO("Camarero"),
    TECNICO("Técnico"),
    PARQUIMETRERO("Parquimetrero"),
    POLIVALENTE("Polivalente");

    private String oficioStr;

    private Oficio(String oficioStr){
        this.oficioStr = oficioStr;
    }

    public String getOficioStr(){
        return oficioStr;
    }

    @Override
    public String toString(){
        return oficioStr;
    }

}
