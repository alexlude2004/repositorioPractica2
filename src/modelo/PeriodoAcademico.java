package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class PeriodoAcademico {
    private Integer id;
    private String nombre;
    private Date fechaDesde;
    private Date fechaHasta;
    private Boolean estado;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer id, String nombre, Date fechaDesde, Date fechaHasta, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
}
