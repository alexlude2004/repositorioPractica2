
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

    public static void main(String[] args) {
        VendedorControllerListas vcl = new VendedorControllerListas();
        vcl.getVendedor().setId(1);
        vcl.getVendedor().setDni("1102745690");
        vcl.getVendedor().setRuc("1102745690001");
        vcl.getVendedor().setApellidos("Rodríguez Pérez");
        vcl.getVendedor().setNombres("Ana María");
        vcl.getVendedor().setDireccion("Calle San Gerardo #123");
        vcl.getVendedor().setTelefono(" 555-1234");
        vcl.getVendedor().setCorreo("ana.rodriguez@email.com");
        vcl.save();

        vcl.getVendedor().setId(2);
        vcl.getVendedor().setDni("1108912281");
        vcl.getVendedor().setRuc("1108912281001");
        vcl.getVendedor().setApellidos("Medina López");
        vcl.getVendedor().setNombres("Javier Alejandro");
        vcl.getVendedor().setDireccion("Avenida del Sol 567, Pueblo Imaginario");
        vcl.getVendedor().setTelefono("(555) 987-6543");
        vcl.getVendedor().setCorreo("javier.rodriguez@emailfantasia.com");
        vcl.save();
    }
        
}
