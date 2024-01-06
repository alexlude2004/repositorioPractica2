package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Malla {
    private Integer id;
    private String nombre;
    private String cod_resolucion;
    private String modalidad;
    private Date fecha_Creacion;
    private Boolean estado;

    public Malla() {
    }

    public Malla(Integer id, String nombre, String cod_resolucion, String modalidad, Date fecha_Creacion, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.cod_resolucion = cod_resolucion;
        this.modalidad = modalidad;
        this.fecha_Creacion = fecha_Creacion;
        this.estado = estado;
    }
    
    public Boolean comparar(Malla c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) > 0;
                }
                else if (field.equalsIgnoreCase("cod_resolucion")) {
                    return getCod_resolucion().compareTo(c.getCod_resolucion()) > 0;
                }
                else if (field.equalsIgnoreCase("modalidad")) {
                    return getModalidad().compareTo(c.getModalidad()) > 0;
                }
                else if (field.equalsIgnoreCase("fecha_Creacion")) {
                    return getFecha_Creacion().after(c.getFecha_Creacion());
                }
                else if (field.equalsIgnoreCase("estado")) {
                    return getEstado().compareTo(c.getEstado()) > 0;
                }
                
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) < 0;
                }
                else if (field.equalsIgnoreCase("cod_resolucion")) {
                    return getCod_resolucion().compareTo(c.getCod_resolucion()) < 0;
                }
                else if (field.equalsIgnoreCase("modalidad")) {
                    return getModalidad().compareTo(c.getModalidad()) < 0;
                }
                else if (field.equalsIgnoreCase("fecha_Creacion")) {
                    return getFecha_Creacion().before(c.getFecha_Creacion());
                }
                else if (field.equalsIgnoreCase("estado")) {
                    return getEstado().compareTo(c.getEstado()) < 0;
                }             
            default:
                return null;
        }
    }    

    @Override
    public String toString() {
        return nombre;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCod_resolucion() {
        return cod_resolucion;
    }

    public void setCod_resolucion(String cod_resolucion) {
        this.cod_resolucion = cod_resolucion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Date getFecha_Creacion() {
        return fecha_Creacion;
    }

    public void setFecha_Creacion(Date fecha_Creacion) {
        this.fecha_Creacion = fecha_Creacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
