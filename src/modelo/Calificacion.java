package modelo;

/**
 *
 * @author Asus
 */
public class Calificacion {
    private Integer id_estudiante;
    private Integer id_docente;
    private Integer id_materia;
    private Integer nota;
    private String retroalimentacion;

    public Calificacion() {
    }

    public Calificacion(Integer id_estudiante, Integer id_docente, Integer id_materia, Integer nota, String retroalimentacion) {
        this.id_estudiante = id_estudiante;
        this.id_docente = id_docente;
        this.id_materia = id_materia;
        this.nota = nota;
        this.retroalimentacion = retroalimentacion;
        System.out.println("Cambio de Alexis a Calificacion");
        System.out.println("Cambio de Alexis a Calificacion en la rama Aux");
    }

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }
    
    
}
