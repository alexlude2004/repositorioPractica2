
package modelo;

import java.util.Date;

/**
 *
 * @author alexg
 */
public class Venta {
    private Integer id;
    private Date fecha;
    private String codigo_venta;
    private Integer id_auto;
    private Integer id_vendedor;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the id_auto
     */
    public Integer getId_auto() {
        return id_auto;
    }

    /**
     * @param id_auto the id_auto to set
     */
    public void setId_auto(Integer id_auto) {
        this.id_auto = id_auto;
    }

    /**
     * @return the id_vendedor
     */
    public Integer getId_vendedor() {
        return id_vendedor;
    }

    /**
     * @param id_vendedor the id_vendedor to set
     */
    public void setId_vendedor(Integer id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the codigo_venta
     */
    public String getCodigo_venta() {
        return codigo_venta;
    }

    /**
     * @param codigo_venta the codigo_venta to set
     */
    public void setCodigo_venta(String codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public Boolean comparar(Venta c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("fecha")) {
                    return  getFecha().after(c.getFecha());
                }
                else if (field.equalsIgnoreCase("codigo_venta")) {
                    return getCodigo_venta().compareTo(c.getCodigo_venta()) > 0;
                }
                else if (field.equalsIgnoreCase("id_auto")) {
                    return getId_auto().intValue() > c.getId_auto().intValue();
                }
                else if (field.equalsIgnoreCase("id_vendedor")) {
                    return getId_vendedor().intValue() > c.getId_vendedor().intValue();
                }           

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("fecha")) {
                    return  getFecha().before(c.getFecha());
                }
                else if (field.equalsIgnoreCase("codigo_venta")) {
                    return getCodigo_venta().compareTo(c.getCodigo_venta()) < 0;
                }
                else if (field.equalsIgnoreCase("id_auto")) {
                    return getId_auto().intValue() < c.getId_auto().intValue();
                }
                else if (field.equalsIgnoreCase("id_vendedor")) {
                    return getId_vendedor().intValue() < c.getId_vendedor().intValue();
                }     
            default:
                return null;
        }
    }  

}
