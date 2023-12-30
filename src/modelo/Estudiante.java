package modelo;

/**
 *
 * @author Asus
 */
public class Estudiante extends Persona{
    private Boolean gratuidad;

    public Estudiante() {
    }

    public Estudiante(Boolean gratuidad, String nombres, String apellidos, String cedula, String nacionalidad, Integer edad, String telefono) {
        super(nombres, apellidos, cedula, nacionalidad, edad, telefono);
        this.gratuidad = gratuidad;
    }

    public Boolean getGratuidad() {
        return gratuidad;
    }

    public void setGratuidad(Boolean gratuidad) {
        this.gratuidad = gratuidad;
    }
    
    
}
