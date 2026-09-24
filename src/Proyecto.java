

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    //Atributos
    private String codigoProyecto;
    private String fechaSolicitud;
    private String fechaInicio;
    private String fechaEntrega;
    private String estado; // Pendiente, Confirmado, En curso, Finalizado, Cancelado
    private String metodoPago; // Tarjeta de credito, Transferencia bancaria, Efectivo
    private int cantidadDiasDesarrollo;
    private double descuento; // Porcentaje de descuento para clientes frecuentes
    private double costoServiciosAdicionales; // Se acumulara cuando exista la clase ServicioAdicional
    private double valorTotal;

    private Cliente cliente;
    private List<Desarrollador> listaDesarrolladores;

    //Constructor
    public Proyecto(String codigoProyecto, String fechaSolicitud, String fechaInicio,
                    String fechaEntrega, String metodoPago, int cantidadDiasDesarrollo,
                    Cliente cliente) {
        this.codigoProyecto = codigoProyecto;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.metodoPago = metodoPago;
        this.cantidadDiasDesarrollo = cantidadDiasDesarrollo;
        this.cliente = cliente;

        this.estado = "Pendiente";
        this.descuento = 0;
        this.costoServiciosAdicionales = 0;
        this.valorTotal = 0;
        this.listaDesarrolladores = new ArrayList<>();
    }

    //Getters and Setters
    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getCantidadDiasDesarrollo() {
        return cantidadDiasDesarrollo;
    }

    public void setCantidadDiasDesarrollo(int cantidadDiasDesarrollo) {
        this.cantidadDiasDesarrollo = cantidadDiasDesarrollo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getCostoServiciosAdicionales() {
        return costoServiciosAdicionales;
    }

    public void setCostoServiciosAdicionales(double costoServiciosAdicionales) {
        this.costoServiciosAdicionales = costoServiciosAdicionales;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Desarrollador> getListaDesarrolladores() {
        return listaDesarrolladores;
    }

    public void setListaDesarrolladores(List<Desarrollador> listaDesarrolladores) {
        this.listaDesarrolladores = listaDesarrolladores;
    }

    //Cantidad de desarrolladores asignados actualmente al proyecto
    public int getCantidadDesarrolladoresAsignados() {
        return listaDesarrolladores.size();
    }

    //Metodos propios del negocio

    //Busca la posicion de un desarrollador dentro del proyecto a partir de su codigo
    public int encontrarIndexDesarrollador(String codigoDesarrollador) {
        for (int i = 0; i < listaDesarrolladores.size(); i++) {
            if (listaDesarrolladores.get(i).getCodigo().equals(codigoDesarrollador)) {
                return i;
            }
        }
        return -1;
    }

    //Agrega un desarrollador al proyecto validando que no este repetido y que tenga disponibilidad.
    //La disponibilidad del desarrollador solo se actualiza cuando el proyecto se confirma.
    public boolean asignarDesarrollador(Desarrollador nuevoDesarrollador) {

        if (!nuevoDesarrollador.estaDisponible()) {
            return false;
        }

        if (encontrarIndexDesarrollador(nuevoDesarrollador.getCodigo()) != -1) {
            return false;
        }

        listaDesarrolladores.add(nuevoDesarrollador);
        calcularValorTotal();
        return true;
    }

    //Quita un desarrollador del proyecto y libera su disponibilidad
    public boolean eliminarDesarrollador(String codigoDesarrollador) {
        int index = encontrarIndexDesarrollador(codigoDesarrollador);

        if (index != -1) {
            listaDesarrolladores.get(index).liberarProyecto();
            listaDesarrolladores.remove(index);
            calcularValorTotal();
            return true;
        }

        return false;
    }

    //Confirma el proyecto y actualiza la disponibilidad de los desarrolladores asignados
    public boolean confirmarProyecto() {
        if (!estado.equals("Pendiente")) {
            return false;
        }

        for (Desarrollador desarrollador : listaDesarrolladores) {
            desarrollador.asignarProyecto();
        }

        estado = "Confirmado";
        calcularValorTotal();
        return true;
    }

    //Cambia el estado del proyecto y libera a los desarrolladores cuando finaliza o se cancela
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;

        if (nuevoEstado.equals("Finalizado") || nuevoEstado.equals("Cancelado")) {
            for (Desarrollador desarrollador : listaDesarrolladores) {
                desarrollador.liberarProyecto();
            }
        }
    }

    //Suma el costo de un servicio adicional utilizado durante el proyecto
    //(placeholder hasta que exista la clase ServicioAdicional)
    public void agregarServicioAdicional(double precioServicio) {
        costoServiciosAdicionales += precioServicio;
        calcularValorTotal();
    }

    //Calcula el valor total del proyecto: tarifas de los desarrolladores por los dias
    //de desarrollo, mas servicios adicionales, menos el descuento por cliente frecuente
    public double calcularValorTotal() {
        double totalTarifas = 0;

        for (Desarrollador desarrollador : listaDesarrolladores) {
            totalTarifas += desarrollador.getTarifaPorDia();
        }

        double subtotal = (totalTarifas * cantidadDiasDesarrollo) + costoServiciosAdicionales;
        valorTotal = subtotal - (subtotal * descuento / 100);

        return valorTotal;
    }
}