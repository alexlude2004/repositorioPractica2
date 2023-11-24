
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
        return 5;
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
                return (vendedor != null) ? vendedor.getDni() : "";
            case 1:
                return (vendedor != null) ? vendedor.getNombres() : "";
            case 2:
                return (vendedor != null) ? vendedor.getApellidos() : "";
            case 3:
                return (vendedor != null) ? vendedor.getTelefono() : "";
            case 4:
                return (vendedor != null) ? vendedor.getCorreo() : "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "DNI";
            case 1:
                return "Nombres";
            case 2:
                return "Apellidos";
            case 3:
                return "Telefono";
            case 4:
                return "Correo";                
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

