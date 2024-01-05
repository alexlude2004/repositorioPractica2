package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Estudiante extends Persona{
    private String titulo_bachiller;
    private String ciudad_Procedencia;

    public Estudiante() {
    }

    public Estudiante(String titulo_bachiller, String ciudad_Procedencia, Integer id, String nombres, String apellidos, String nacionalidad, Date fecha_nac, String cedula, String telefono) {
        super(id, nombres, apellidos, nacionalidad, fecha_nac, cedula, telefono);
        this.titulo_bachiller = titulo_bachiller;
        this.ciudad_Procedencia = ciudad_Procedencia;
    }

    public String getTitulo_bachiller() {
        return titulo_bachiller;
    }

    public void setTitulo_bachiller(String titulo_bachiller) {
        this.titulo_bachiller = titulo_bachiller;
    }

    public String getCiudad_Procedencia() {
        return ciudad_Procedencia;
    }

    public void setCiudad_Procedencia(String ciudad_Procedencia) {
        this.ciudad_Procedencia = ciudad_Procedencia;
    }

    
}
