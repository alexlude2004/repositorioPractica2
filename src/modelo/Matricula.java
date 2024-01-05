package modelo;

/**
 *
 * @author Asus
 */
public class Matricula {
    private Integer id;
    private Integer id_estudiante;
    private Integer id_periodoAcademico;
    private String codigo;
    private Boolean gratuidad;

    public Matricula() {
    }

    public Matricula(Integer id, Integer id_estudiante, Integer id_periodoAcademico, String codigo, Boolean gratuidad) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_periodoAcademico = id_periodoAcademico;
        this.codigo = codigo;
        this.gratuidad = gratuidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_periodoAcademico() {
        return id_periodoAcademico;
    }

    public void setId_periodoAcademico(Integer id_periodoAcademico) {
        this.id_periodoAcademico = id_periodoAcademico;
    }

    public Boolean getGratuidad() {
        return gratuidad;
    }

    public void setGratuidad(Boolean gratuidad) {
        this.gratuidad = gratuidad;
    }

    

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }    
}
