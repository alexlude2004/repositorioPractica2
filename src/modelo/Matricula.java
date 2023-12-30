package modelo;

/**
 *
 * @author Asus
 */
public class Matricula {
    private Integer id_estudiante;
    private String codigo;
    private String descripcion;

    public Matricula() {
    }

    public Matricula(Integer id_estudiante, String codigo, String descripcion) {
        this.id_estudiante = id_estudiante;
        this.codigo = codigo;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
