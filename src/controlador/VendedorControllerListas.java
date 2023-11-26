
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import modelo.Vendedor;

/**
 *
 * @author alexg
 */
public class VendedorControllerListas extends DataAccessObject<Vendedor> {
    private LinkedList<Vendedor> vendedores = new LinkedList<>();
    private Vendedor vendedor = new Vendedor();
    private Integer index = - 1;
    
    public VendedorControllerListas() {
        super(Vendedor.class);
    }

    /**
     * @return the vendedores
     */
    public LinkedList<Vendedor> getVendedores() {
        if (vendedores.isEmpty())
            vendedores = listAll();
        return vendedores;
    }

    /**
     * @param vendedores the llantas to set
     */
    public void setVendedores(LinkedList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
        
    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        if (vendedor == null) {
            vendedor = new Vendedor();
        }
        return vendedor;
    }

    /**
     * @param vendedor the llanta to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public Boolean save() {
        this.vendedor.setId(generar_id());
        return save(vendedor);
    }
    
    public Boolean update(Integer index) {
        return update(vendedor, index);
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
