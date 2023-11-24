
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import modelo.Venta;

/**
 *
 * @author alexg
 */
public class VentaControllerListas extends DataAccessObject<Venta> {
    private LinkedList<Venta> ventas = new LinkedList<>();
    private Venta venta = new Venta();    
    private Integer index = - 1;
    
    public VentaControllerListas() {
        super(Venta.class);
    }
    
    /**
     * @return the ventas
     */
    public LinkedList<Venta> getVentas() {
        if (ventas.isEmpty())
            ventas = listAll();
        return ventas;
    }

    /**
     * @param ventas the llantas to set
     */
    public void setVentas(LinkedList<Venta> ventas) {
        this.ventas = ventas;
    }
        
    /**
     * @return the venta
     */
    public Venta getVenta() {
        if (venta == null) {
            venta = new Venta();
        }
        return venta;
    }

    /**
     * @param venta the llanta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public Boolean save() {
        this.venta.setId(generar_id());
        return save(venta);
    }
    
    public Boolean update(Integer index) {
        return update(venta, index);
    }
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listAll().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
       
}
