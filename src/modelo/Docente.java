package modelo;

/**
 *
 * @author Asus
 */
public class Docente extends Persona{
    private Integer exp_docente;   
    private String titulo;

    public Docente() {
    }

    public Docente(Integer exp_docente, String titulo, String nombres, String apellidos, String cedula, String nacionalidad, Integer edad, String telefono) {
        super(nombres, apellidos, cedula, nacionalidad, edad, telefono);
        this.exp_docente = exp_docente;
        this.titulo = titulo;
    }

    public Integer getExp_docente() {
        return exp_docente;
    }

    public void setExp_docente(Integer exp_docente) {
        this.exp_docente = exp_docente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    
}
