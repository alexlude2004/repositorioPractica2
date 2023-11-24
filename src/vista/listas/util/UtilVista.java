
package vista.listas.util;

import controlador.MarcaControllerListas;
import controlador.TDA.listas.exception.VacioException;
import controlador.VendedorControllerListas;
import javax.swing.JComboBox;
import modelo.Marca;
import modelo.Vendedor;

/**
 *
 * @author alexg
 */
public class UtilVista {
    
    public static void cargarMarca(JComboBox cbxMarca) throws VacioException {
        MarcaControllerListas mc = new MarcaControllerListas();
        cbxMarca.removeAllItems();
        for (int i = 0; i < mc.getMarcas().getSize(); i++) {
            cbxMarca.addItem(mc.getMarcas().get(i));
        }
    }
    
    public static Marca getComboMarcas(JComboBox cbx) {
        return (Marca)cbx.getSelectedItem();
    }    
    
    public static void cargarVendedor(JComboBox cbxVendedor) throws VacioException {
        VendedorControllerListas vc = new VendedorControllerListas();
        cbxVendedor.removeAllItems();
        for (int i = 0; i < vc.getVendedores().getSize(); i++) {
            cbxVendedor.addItem(vc.getVendedores().get(i));
        }
    }
    
    public static Vendedor getComboVendedores(JComboBox cbx) {
        return (Vendedor)cbx.getSelectedItem();
    }    
    
}