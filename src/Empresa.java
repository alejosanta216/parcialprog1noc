

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String paginaWeb;
    private List<Cliente> listaClientes;
    private List<Desarrollador> listaDesarrolladores;
    private List<Proyecto> listaProyectos;
    private List<ServicioAdicional> listaServicios;

    public Empresa(String nombreComercial, String nit, String direccion, String telefono, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        this.listaClientes = new ArrayList<>();
        this.listaDesarrolladores = new ArrayList<>();
        this.listaProyectos = new ArrayList<>();
        this.listaServicios = new ArrayList<>();
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Desarrollador> getListaDesarrolladores() {
        return listaDesarrolladores;
    }

    public void setListaDesarrolladores(List<Desarrollador> listaDesarrolladores) {
        this.listaDesarrolladores = listaDesarrolladores;
    }

    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public List<ServicioAdicional> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioAdicional> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public int buscarCliente(String documento) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getDocumento().equals(documento)) {
                return i;
            }
        }
        return -1;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumento()) == -1) {
            listaClientes.add(cliente);
            return true;
        }
        return false;
    }

    public boolean actualizarCliente(String documento, String nombre, String telefono, String correo, String pais) {
        int index = buscarCliente(documento);
        if (index != -1) {
            Cliente cliente = listaClientes.get(index);
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliente.setPais(pais);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(String documento) {
        int index = buscarCliente(documento);
        if (index != -1) {
            listaClientes.remove(index);
            return true;
        }
        return false;
    }

    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    public String mostrarClientes() {
        String mensaje = "";
        for (Cliente cliente : listaClientes) {
            mensaje += "\nDocumento: " + cliente.getDocumento() +
                    "\nNombre: " + cliente.getNombre() +
                    "\nTelefono: " + cliente.getTelefono() +
                    "\nCorreo: " + cliente.getCorreo() +
                    "\nPais: " + cliente.getPais() +
                    "\nProyectos contratados: " + cliente.getListaProyectos().size() + "\n";
        }
        return mensaje;
    }

    public int buscarDesarrollador(String codigo) {
        for (int i = 0; i < listaDesarrolladores.size(); i++) {
            if (listaDesarrolladores.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public boolean registrarDesarrollador(Desarrollador desarrollador) {
        if (buscarDesarrollador(desarrollador.getCodigo()) == -1) {
            listaDesarrolladores.add(desarrollador);
            return true;
        }
        return false;
    }

    public boolean eliminarDesarrollador(String codigo) {
        int index = buscarDesarrollador(codigo);
        if (index != -1) {
            listaDesarrolladores.remove(index);
            return true;
        }
        return false;
    }

    public boolean actualizarDesarrollador(String codigo, String equipoTrabajo, String nivel,
                                           int cantidadMaximaProyectosSimultaneos, double tarifaPorDia) {
        int index = buscarDesarrollador(codigo);
        if (index != -1) {
            Desarrollador desarrollador = listaDesarrolladores.get(index);
            desarrollador.setEquipoTrabajo(equipoTrabajo);
            desarrollador.setNivel(nivel);
            desarrollador.setCantidadMaximaProyectosSimultaneos(cantidadMaximaProyectosSimultaneos);
            desarrollador.setTarifaPorDia(tarifaPorDia);
            return true;
        }
        return false;
    }

    public String mostrarDesarrolladores() {
        String mensaje = "";
        for (Desarrollador desarrollador : listaDesarrolladores) {
            mensaje += "\nCodigo: " + desarrollador.getCodigo() +
                    "\nEquipo: " + desarrollador.getEquipoTrabajo() +
                    "\nNivel: " + desarrollador.getNivel() +
                    "\nTarifa por dia: " + desarrollador.getTarifaPorDia() +
                    "\nEstado: " + desarrollador.getEstado() +
                    "\nProyectos actuales: " + desarrollador.getCantidadProyectosActuales() + "\n";
        }
        return mensaje;
    }

    public int buscarProyecto(String codigo) {
        for (int i = 0; i < listaProyectos.size(); i++) {
            if (listaProyectos.get(i).getCodigoProyecto().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public boolean registrarProyecto(Proyecto proyecto) {
        if (buscarProyecto(proyecto.getCodigoProyecto()) == -1) {
            listaProyectos.add(proyecto);
            proyecto.getCliente().agregarProyecto(proyecto);
            return true;
        }
        return false;
    }

    public boolean eliminarProyecto(String codigo) {
        int index = buscarProyecto(codigo);
        if (index != -1) {
            listaProyectos.remove(index);
            return true;
        }
        return false;
    }

    public String mostrarProyectos() {
        String mensaje = "";
        for (Proyecto proyecto : listaProyectos) {
            mensaje += "\nCodigo: " + proyecto.getCodigoProyecto() +
                    "\nCliente: " + proyecto.getCliente().getNombre() +
                    "\nEstado: " + proyecto.getEstado() +
                    "\nFecha solicitud: " + proyecto.getFechaSolicitud() +
                    "\nMetodo de pago: " + proyecto.getMetodoPago() +
                    "\nDesarrolladores asignados: " + proyecto.getCantidadDesarrolladoresAsignados() +
                    "\nValor total: " + proyecto.getValorTotal() + "\n";
        }
        return mensaje;
    }

    public int buscarServicio(String codigo) {
        for (int i = 0; i < listaServicios.size(); i++) {
            if (listaServicios.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public boolean registrarServicio(ServicioAdicional servicio) {
        if (buscarServicio(servicio.getCodigo()) == -1) {
            listaServicios.add(servicio);
            return true;
        }
        return false;
    }

    public boolean eliminarServicio(String codigo) {
        int index = buscarServicio(codigo);
        if (index != -1) {
            listaServicios.remove(index);
            return true;
        }
        return false;
    }

    public boolean actualizarServicio(String codigo, String nombre, String descripcion, double precio, boolean disponible) {
        int index = buscarServicio(codigo);
        if (index != -1) {
            ServicioAdicional servicio = listaServicios.get(index);
            servicio.setNombre(nombre);
            servicio.setDescripcion(descripcion);
            servicio.setPrecio(precio);
            servicio.setDisponible(disponible);
            return true;
        }
        return false;
    }

    public String mostrarServicios() {
        String mensaje = "";
        for (ServicioAdicional servicio : listaServicios) {
            mensaje += "\nCodigo: " + servicio.getCodigo() +
                    "\nNombre: " + servicio.getNombre() +
                    "\nDescripcion: " + servicio.getDescripcion() +
                    "\nPrecio: " + servicio.getPrecio() +
                    "\nDisponible: " + servicio.isDisponible() + "\n";
        }
        return mensaje;
    }

    public boolean agregarServicioAProyecto(String codigoProyecto, String codigoServicio) {
        int indexProyecto = buscarProyecto(codigoProyecto);
        int indexServicio = buscarServicio(codigoServicio);
        if (indexProyecto != -1 && indexServicio != -1) {
            ServicioAdicional servicio = listaServicios.get(indexServicio);
            if (servicio.isDisponible()) {
                listaProyectos.get(indexProyecto).agregarServicioAdicional(servicio.getPrecio());
                return true;
            }
        }
        return false;
    }


    public boolean esNumeroPerfecto(String telefono) {
        String soloDigitos = telefono.replaceAll("[^0-9]", "");
        if (soloDigitos.isEmpty() || soloDigitos.length() > 18) {
            return false;
        }
        long numero = Long.parseLong(soloDigitos);
        if (numero <= 1) {
            return false;
        }
        long suma = 1; // el 1 siempre es divisor propio
        for (long i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                suma += i;
                long pareja = numero / i;
                if (pareja != i) {
                    suma += pareja;
                }
            }
        }
        return suma == numero;
    }

    public double calcularIngresosPorFecha(String fecha) {
        double total = 0;
        for (Proyecto proyecto : listaProyectos) {
            if (proyecto.getFechaSolicitud().equals(fecha)) {
                total += proyecto.calcularValorTotal();
            }
        }
        return total;
    }
}