package modelo;

/**
 *
 * @author Asus
 */
public class Malla {
    private String nombre;
    private String fechaVigencia;

    public Malla() {
    }

    public Malla(String nombre, String fechaVigencia) {
        this.nombre = nombre;
        this.fechaVigencia = fechaVigencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    
    
}
