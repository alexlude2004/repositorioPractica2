package modelo;

/**
 *
 * @author Asus
 */
public class Materia {
    private String nombre;
    private Boolean tieneCadena;

    public Materia() {
    }

    public Materia(String nombreAsignatura, Boolean tieneCadena) {
        this.nombre = nombreAsignatura;
        this.tieneCadena = tieneCadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getTieneCadena() {
        return tieneCadena;
    }

    public void setTieneCadena(Boolean tieneCadena) {
        this.tieneCadena = tieneCadena;
    }
    
    
}
