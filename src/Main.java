

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {

    static Empresa empresa;

    private static String pedirFecha(String mensaje) {
        JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
        spinnerFecha.setEditor(editor);
        spinnerFecha.setValue(new Date());

        int opcion = JOptionPane.showConfirmDialog(null, spinnerFecha, mensaje,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }

        Date fechaSeleccionada = (Date) spinnerFecha.getValue();
        LocalDate fechaLocal = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return fechaLocal.toString();
    }

    public static void main(String[] args) {
        empresa = new Empresa("DevPlus", "900123456-7", "Armenia, Quindio", "6067441000", "www.devplus.com");

        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Sistema DevPlus:" +
                    "\nSeleccione una opcion:" +
                    "\n1. Clientes" +
                    "\n2. Desarrolladores" +
                    "\n3. Servicios adicionales" +
                    "\n4. Proyectos" +
                    "\n5. Consultar numero perfecto por telefono de cliente" +
                    "\n6. Consultar ingresos por fecha" +
                    "\n0. Salir del sistema"));

            switch (opcion) {

                case 1:
                    menuClientes();
                    break;

                case 2:
                    menuDesarrolladores();
                    break;

                case 3:
                    menuServicios();
                    break;

                case 4:
                    menuProyectos();
                    break;

                case 5:
                    consultarNumeroPerfecto();
                    break;

                case 6:
                    consultarIngresosPorFecha();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "El programa finalizo");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuClientes() {
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Clientes:" +
                    "\n1. Registrar cliente" +
                    "\n2. Mostrar clientes" +
                    "\n3. Actualizar cliente" +
                    "\n4. Eliminar cliente" +
                    "\n0. Volver"));

            switch (opcion) {

                case 1:
                    registrarCliente();
                    break;

                case 2:
                    mostrarClientes();
                    break;

                case 3:
                    actualizarCliente();
                    break;

                case 4:
                    eliminarCliente();
                    break;

                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (opcion != 0);
    }

    private static void registrarCliente() {
        String documento = JOptionPane.showInputDialog("Ingrese el documento o NIT del cliente:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo o razon social:");
        String telefono = JOptionPane.showInputDialog("Ingrese el telefono del cliente:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo electronico:");
        String pais = JOptionPane.showInputDialog("Ingrese el pais de procedencia:");

        Cliente nuevoCliente = new Cliente(documento, nombre, telefono, correo, pais);

        boolean resultado = empresa.registrarCliente(nuevoCliente);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro");
        }
    }

    private static void mostrarClientes() {
        JOptionPane.showMessageDialog(null, empresa.mostrarClientes());
    }

    private static void actualizarCliente() {
        String documento = JOptionPane.showInputDialog("Ingrese el documento del cliente a actualizar:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo o razon social:");
        String telefono = JOptionPane.showInputDialog("Ingrese el telefono del cliente:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo electronico:");
        String pais = JOptionPane.showInputDialog("Ingrese el pais de procedencia:");

        boolean resultado = empresa.actualizarCliente(documento, nombre, telefono, correo, pais);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se actualizo el cliente");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el cliente");
        }
    }

    private static void eliminarCliente() {
        String documento = JOptionPane.showInputDialog("Ingrese el documento del cliente a eliminar:");

        boolean resultado = empresa.eliminarCliente(documento);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se elimino el cliente");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el cliente");
        }
    }

    private static void menuDesarrolladores() {
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Desarrolladores:" +
                    "\n1. Registrar desarrollador" +
                    "\n2. Mostrar desarrolladores" +
                    "\n3. Actualizar desarrollador" +
                    "\n4. Eliminar desarrollador" +
                    "\n0. Volver"));

            switch (opcion) {

                case 1:
                    registrarDesarrollador();
                    break;

                case 2:
                    mostrarDesarrolladores();
                    break;

                case 3:
                    actualizarDesarrollador();
                    break;

                case 4:
                    eliminarDesarrollador();
                    break;

                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (opcion != 0);
    }

    private static void registrarDesarrollador() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del desarrollador:");
        String equipoTrabajo = JOptionPane.showInputDialog("Ingrese el equipo de trabajo:");
        String nivel = JOptionPane.showInputDialog("Ingrese el nivel (Junior, Semisenior, Senior):");
        int cantidadMaxima = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad maxima de proyectos simultaneos:"));
        double tarifaPorDia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tarifa por dia:"));

        Desarrollador nuevoDesarrollador = new Desarrollador(codigo, equipoTrabajo, nivel, cantidadMaxima, tarifaPorDia);

        boolean resultado = empresa.registrarDesarrollador(nuevoDesarrollador);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro");
        }
    }

    private static void mostrarDesarrolladores() {
        JOptionPane.showMessageDialog(null, empresa.mostrarDesarrolladores());
    }

    private static void actualizarDesarrollador() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del desarrollador a actualizar:");
        String equipoTrabajo = JOptionPane.showInputDialog("Ingrese el equipo de trabajo:");
        String nivel = JOptionPane.showInputDialog("Ingrese el nivel (Junior, Semisenior, Senior):");
        int cantidadMaxima = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad maxima de proyectos simultaneos:"));
        double tarifaPorDia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tarifa por dia:"));

        boolean resultado = empresa.actualizarDesarrollador(codigo, equipoTrabajo, nivel, cantidadMaxima, tarifaPorDia);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se actualizo el desarrollador");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el desarrollador");
        }
    }

    private static void eliminarDesarrollador() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del desarrollador a eliminar:");

        boolean resultado = empresa.eliminarDesarrollador(codigo);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se elimino el desarrollador");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el desarrollador");
        }
    }

    private static void menuServicios() {
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Servicios adicionales:" +
                    "\n1. Registrar servicio" +
                    "\n2. Mostrar servicios" +
                    "\n3. Actualizar servicio" +
                    "\n4. Eliminar servicio" +
                    "\n0. Volver"));

            switch (opcion) {

                case 1:
                    registrarServicio();
                    break;

                case 2:
                    mostrarServicios();
                    break;

                case 3:
                    actualizarServicio();
                    break;

                case 4:
                    eliminarServicio();
                    break;

                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (opcion != 0);
    }

    private static void registrarServicio() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del servicio:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del servicio:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion del servicio:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del servicio:"));
        int opcionDisponible = Integer.parseInt(JOptionPane.showInputDialog("El servicio esta disponible:" +
                "\n1. Si" +
                "\n2. No"));
        boolean disponible = opcionDisponible == 1;

        ServicioAdicional nuevoServicio = new ServicioAdicional(codigo, nombre, descripcion, precio, disponible);

        boolean resultado = empresa.registrarServicio(nuevoServicio);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro");
        }
    }

    private static void mostrarServicios() {
        JOptionPane.showMessageDialog(null, empresa.mostrarServicios());
    }

    private static void actualizarServicio() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del servicio a actualizar:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del servicio:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion del servicio:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del servicio:"));
        int opcionDisponible = Integer.parseInt(JOptionPane.showInputDialog("El servicio esta disponible:" +
                "\n1. Si" +
                "\n2. No"));
        boolean disponible = opcionDisponible == 1;

        boolean resultado = empresa.actualizarServicio(codigo, nombre, descripcion, precio, disponible);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se actualizo el servicio");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el servicio");
        }
    }

    private static void eliminarServicio() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del servicio a eliminar:");

        boolean resultado = empresa.eliminarServicio(codigo);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se elimino el servicio");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el servicio");
        }
    }

    private static void menuProyectos() {
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Proyectos:" +
                    "\n1. Registrar proyecto" +
                    "\n2. Mostrar proyectos" +
                    "\n3. Asignar desarrollador a proyecto" +
                    "\n4. Agregar servicio a proyecto" +
                    "\n5. Confirmar proyecto" +
                    "\n6. Cambiar estado de proyecto" +
                    "\n7. Eliminar proyecto" +
                    "\n0. Volver"));

            switch (opcion) {

                case 1:
                    registrarProyecto();
                    break;

                case 2:
                    mostrarProyectos();
                    break;

                case 3:
                    asignarDesarrolladorAProyecto();
                    break;

                case 4:
                    agregarServicioAProyecto();
                    break;

                case 5:
                    confirmarProyecto();
                    break;

                case 6:
                    cambiarEstadoProyecto();
                    break;

                case 7:
                    eliminarProyecto();
                    break;

                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (opcion != 0);
    }

    private static void registrarProyecto() {
        String documentoCliente = JOptionPane.showInputDialog("Ingrese el documento del cliente que contrata el proyecto:");
        int indexCliente = empresa.buscarCliente(documentoCliente);

        if (indexCliente == -1) {
            JOptionPane.showMessageDialog(null, "El cliente no existe");
            return;
        }

        Cliente cliente = empresa.getListaClientes().get(indexCliente);

        String codigoProyecto = JOptionPane.showInputDialog("Ingrese el codigo del proyecto:");
        String fechaSolicitud = pedirFecha("Seleccione la fecha de solicitud:");
        if (fechaSolicitud == null) {
            JOptionPane.showMessageDialog(null, "Registro cancelado");
            return;
        }

        String fechaInicio = pedirFecha("Seleccione la fecha de inicio:");
        if (fechaInicio == null) {
            JOptionPane.showMessageDialog(null, "Registro cancelado");
            return;
        }

        String fechaEntrega = pedirFecha("Seleccione la fecha de entrega:");
        if (fechaEntrega == null) {
            JOptionPane.showMessageDialog(null, "Registro cancelado");
            return;
        }

        String metodoPago = JOptionPane.showInputDialog("Ingrese el metodo de pago (Tarjeta de credito, Transferencia bancaria, Efectivo):");
        int cantidadDias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de dias de desarrollo:"));

        Proyecto nuevoProyecto = new Proyecto(codigoProyecto, fechaSolicitud, fechaInicio, fechaEntrega,
                metodoPago, cantidadDias, cliente);

        boolean resultado = empresa.registrarProyecto(nuevoProyecto);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro");
        }
    }

    private static void mostrarProyectos() {
        JOptionPane.showMessageDialog(null, empresa.mostrarProyectos());
    }

    private static void asignarDesarrolladorAProyecto() {
        String codigoProyecto = JOptionPane.showInputDialog("Ingrese el codigo del proyecto:");
        int indexProyecto = empresa.buscarProyecto(codigoProyecto);

        String codigoDesarrollador = JOptionPane.showInputDialog("Ingrese el codigo del desarrollador:");
        int indexDesarrollador = empresa.buscarDesarrollador(codigoDesarrollador);

        if (indexProyecto == -1 || indexDesarrollador == -1) {
            JOptionPane.showMessageDialog(null, "El proyecto o el desarrollador no existen");
            return;
        }

        Proyecto proyecto = empresa.getListaProyectos().get(indexProyecto);
        Desarrollador desarrollador = empresa.getListaDesarrolladores().get(indexDesarrollador);

        boolean resultado = proyecto.asignarDesarrollador(desarrollador);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Desarrollador asignado al proyecto");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo asignar el desarrollador");
        }
    }

    private static void agregarServicioAProyecto() {
        String codigoProyecto = JOptionPane.showInputDialog("Ingrese el codigo del proyecto:");
        String codigoServicio = JOptionPane.showInputDialog("Ingrese el codigo del servicio:");

        boolean resultado = empresa.agregarServicioAProyecto(codigoProyecto, codigoServicio);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Servicio agregado al proyecto");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el servicio");
        }
    }

    private static void confirmarProyecto() {
        String codigoProyecto = JOptionPane.showInputDialog("Ingrese el codigo del proyecto a confirmar:");
        int index = empresa.buscarProyecto(codigoProyecto);

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "El proyecto no existe");
            return;
        }

        Proyecto proyecto = empresa.getListaProyectos().get(index);
        boolean resultado = proyecto.confirmarProyecto();

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Proyecto confirmado. Valor total: " + proyecto.getValorTotal());
        } else {
            JOptionPane.showMessageDialog(null, "El proyecto no se pudo confirmar");
        }
    }

    private static void cambiarEstadoProyecto() {
        String codigoProyecto = JOptionPane.showInputDialog("Ingrese el codigo del proyecto:");
        int index = empresa.buscarProyecto(codigoProyecto);

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "El proyecto no existe");
            return;
        }

        String nuevoEstado = JOptionPane.showInputDialog("Ingrese el nuevo estado" +
                "\n(Pendiente, Confirmado, En curso, Finalizado, Cancelado):");

        empresa.getListaProyectos().get(index).cambiarEstado(nuevoEstado);

        JOptionPane.showMessageDialog(null, "Estado actualizado");
    }

    private static void eliminarProyecto() {
        String codigoProyecto = JOptionPane.showInputDialog("Ingrese el codigo del proyecto a eliminar:");

        boolean resultado = empresa.eliminarProyecto(codigoProyecto);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Se elimino el proyecto");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el proyecto");
        }
    }

    private static void consultarNumeroPerfecto() {
        String telefono = JOptionPane.showInputDialog("Ingrese el telefono del cliente a consultar:");

        Cliente cliente = empresa.buscarClientePorTelefono(telefono);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese telefono");
            return;
        }

        boolean esPerfecto = empresa.esNumeroPerfecto(telefono);

        String mensaje = "Cliente encontrado: " + cliente.getNombre() +
                "\nEl telefono " + telefono + (esPerfecto ? " SI es un numero perfecto" : " NO es un numero perfecto");

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void consultarIngresosPorFecha() {
        String fecha = pedirFecha("Seleccione la fecha a consultar:");
        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "Consulta cancelada");
            return;
        }

        double ingresos = empresa.calcularIngresosPorFecha(fecha);

        JOptionPane.showMessageDialog(null, "Los ingresos por proyectos contratados en " + fecha + " son: " + ingresos);
    }
}
