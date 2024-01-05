package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Revision {
    private Integer id;
    private Integer id_materia;
    private Integer id_tarea;  
    private String titulo;
    private String descripcion;
    private Byte archivo;
    private Date fechaAsignacion;
    private Date fechaDate;
    private Double calificacion;

    public Revision(Integer id, Integer id_materia, Integer id_tarea, String titulo, String descripcion, Byte archivo, Date fechaAsignacion, Date fechaDate, Double calificacion) {
        this.id = id;
        this.id_materia = id_materia;
        this.id_tarea = id_tarea;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaDate = fechaDate;
        this.calificacion = calificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }

    public Integer getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Integer id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getArchivo() {
        return archivo;
    }

    public void setArchivo(Byte archivo) {
        this.archivo = archivo;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    
    
}
