
package vista.listas.tablas;

import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Vendedor;

/**
 *
 * @author alexg
 */
public class ModeloTablaVendedorListas extends AbstractTableModel {
    private LinkedList<Vendedor> vendedores = new LinkedList<>();

    @Override
    public int getRowCount() {
        return vendedores.getSize();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vendedor vendedor = null;
        try {
            vendedor = vendedores.get(rowIndex);
        } catch (Exception e) {
        }

        switch (columnIndex) {
            case 0:
                return (vendedor != null) ? vendedor.getId() : "";
            case 1:
                return (vendedor != null) ? vendedor.getDni() : "";
            case 2:
                return (vendedor != null) ? vendedor.getRuc() : "";            
            case 3:
                return (vendedor != null) ? vendedor.getNombres() : "";
            case 4:
                return (vendedor != null) ? vendedor.getApellidos() : "";
            case 5:
                return (vendedor != null) ? "+593 7 " + vendedor.getTelefono() : "";
            case 6:
                return (vendedor != null) ? vendedor.getCorreo() : "";
            case 7:
                return (vendedor != null) ? vendedor.getDireccion() : "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "DNI";
            case 2:
                return "RUC";                
            case 3:
                return "Nombres";
            case 4:
                return "Apellidos";
            case 5:
                return "Telefono";
            case 6:
                return "Correo";    
            case 7:
                return "Direccion";                   
            default:
                return null;
        }
    }

    /**
     * @return the vendedores
     */
    public LinkedList<Vendedor> getVendedores() {
        return vendedores;
    }

    /**
     * @param vendedores the llantas to set
     */
    public void setVendedores(LinkedList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    
}

