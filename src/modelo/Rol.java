package modelo;

/**
 *
 * @author Asus
 */
public class Rol {
    private String nombre;
    private Boolean estado;

    public Rol() {
    }

    public Rol(String nombre, Boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
