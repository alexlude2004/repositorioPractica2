
package modelo;

import java.util.Date;

/**
 *
 * @author alexg
 */
public class Venta {
    private Integer id;
    private Date fecha;
    private String nro_venta;
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
     * @return the nro_venta
     */
    public String getNro_venta() {
        return nro_venta;
    }

    /**
     * @param nro_venta the nro_venta to set
     */
    public void setNro_venta(String nro_venta) {
        this.nro_venta = nro_venta;
    }
        
}
