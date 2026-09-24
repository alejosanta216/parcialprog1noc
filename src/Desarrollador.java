public class Desarrollador {
    private String codigo;
    private String equipoTrabajo;
    private String nivel; // Junior, Semisenior, Senior
    private int cantidadMaximaProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado; // Disponible, Asignado, Ocupado, En capacitacion
    private int cantidadProyectosActuales;

    public Desarrollador(String codigo, String equipoTrabajo, String nivel,int cantidadMaximaProyectosSimultaneos, double tarifaPorDia) {

        this.codigo = codigo;
        this.equipoTrabajo = equipoTrabajo;
        this.nivel = nivel;
        this.cantidadMaximaProyectosSimultaneos = cantidadMaximaProyectosSimultaneos;
        this.tarifaPorDia = tarifaPorDia;
        this.estado = "Disponible";
        this.cantidadProyectosActuales = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(String equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCantidadMaximaProyectosSimultaneos() {
        return cantidadMaximaProyectosSimultaneos;
    }

    public void setCantidadMaximaProyectosSimultaneos(int cantidadMaximaProyectosSimultaneos) {
        this.cantidadMaximaProyectosSimultaneos = cantidadMaximaProyectosSimultaneos;
    }

    public double getTarifaPorDia() {
        return tarifaPorDia;
    }

    public void setTarifaPorDia(double tarifaPorDia) {
        this.tarifaPorDia = tarifaPorDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidadProyectosActuales() {
        return cantidadProyectosActuales;
    }

    public void setCantidadProyectosActuales(int cantidadProyectosActuales) {
        this.cantidadProyectosActuales = cantidadProyectosActuales;
    }

    public boolean estaDisponible() {
        return cantidadProyectosActuales < cantidadMaximaProyectosSimultaneos;
    }

    public boolean asignarProyecto() {
        if (estaDisponible()) {
            cantidadProyectosActuales++;

            if (cantidadProyectosActuales >= cantidadMaximaProyectosSimultaneos) {
                estado = "Ocupado";
            } else {
                estado = "Asignado";
            }
            return true;
        }
        return false;
    }

    public void liberarProyecto() {
        if (cantidadProyectosActuales > 0) {
            cantidadProyectosActuales--;
        }

        if (cantidadProyectosActuales == 0) {
            estado = "Disponible";
        } else {
            estado = "Asignado";
        }
    }
}
