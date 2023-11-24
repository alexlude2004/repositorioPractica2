
package vista.listas.tablas;

import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Venta;

/**
 *
 * @author alexg
 */
public class ModeloTablaVentaListas extends AbstractTableModel {
    private LinkedList<Venta> ventas = new LinkedList<>();

    @Override
    public int getRowCount() {
        return ventas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = null;
        try {
            venta = ventas.get(rowIndex);
        } catch (Exception e) {
        }

        switch (columnIndex) {
            case 0:
                return (venta != null) ? venta.getNro_fact(): "";
            case 1:
                return (venta != null) ? venta.getFecha() : "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Numero de Venta";
            case 1:
                return "Fecha";
            default:
                return null;
        }
    }

    /**
     * @return the autos
     */
    public LinkedList<Venta> getVentas() {
        return ventas;
    }

    /**
     * @param ventas the llantas to set
     */
    public void setVentas(LinkedList<Venta> ventas) {
        this.ventas = ventas;
    }
    
}

