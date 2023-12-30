package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class PeriodoAcademico {
    private Integer id_malla;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer id_malla, String nombre, Date fechaInicio, Date fechaFin) {
        this.id_malla = id_malla;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getId_malla() {
        return id_malla;
    }

    public void setId_malla(Integer id_malla) {
        this.id_malla = id_malla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
}
