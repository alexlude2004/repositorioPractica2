package modelo;

/**
 *
 * @author Asus
 */
public class Persona {
    private String nombres;
    private String apellidos;
    private String cedula;
    private String nacionalidad;
    private Integer edad;
    private String telefono;
    

    public Persona() {
    }

    public Persona(String nombres, String apellidos, String cedula, String nacionalidad, Integer edad, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
