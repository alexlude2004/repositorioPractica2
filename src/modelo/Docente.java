package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Docente extends Persona{
    private Integer anios_exp_docente;   
    private String titulo_tercerNivel;
    private String titulo_cuartoNivel;
    
    public Docente() {
    }

    public Docente(Integer anios_exp_docente, String titulo_tercerNivel, String titulo_cuartoNivel, Integer id, String nombres, String apellidos, String nacionalidad, Date fecha_nac, String cedula, String telefono) {
        super(id, nombres, apellidos, nacionalidad, fecha_nac, cedula, telefono);
        this.anios_exp_docente = anios_exp_docente;
        this.titulo_tercerNivel = titulo_tercerNivel;
        this.titulo_cuartoNivel = titulo_cuartoNivel;
    }

    public Integer getAnios_exp_docente() {
        return anios_exp_docente;
    }

    public void setAnios_exp_docente(Integer anios_exp_docente) {
        this.anios_exp_docente = anios_exp_docente;
    }

    public String getTitulo_tercerNivel() {
        return titulo_tercerNivel;
    }

    public void setTitulo_tercerNivel(String titulo_tercerNivel) {
        this.titulo_tercerNivel = titulo_tercerNivel;
    }

    public String getTitulo_cuartoNivel() {
        return titulo_cuartoNivel;
    }

    public void setTitulo_cuartoNivel(String titulo_cuartoNivel) {
        this.titulo_cuartoNivel = titulo_cuartoNivel;
    }

    

    

}
