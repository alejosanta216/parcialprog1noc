

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    //Atributos
    private String documento; // Documento de identidad o NIT
    private static String nombre;    // Nombre completo o razon social
    private String telefono;
    private String correo;
    private String pais;

    private List<Proyecto> listaProyectos;

    //Constructor
    public Cliente(String documento, String nombre, String telefono, String correo, String pais) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.pais = pais;
        this.listaProyectos = new ArrayList<>();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public static String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }




    public void agregarProyecto(Proyecto proyecto) {
        listaProyectos.add(proyecto);
    }
}

