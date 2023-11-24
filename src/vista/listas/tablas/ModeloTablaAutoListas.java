
package vista.listas.tablas;

import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Auto;

/**
 *
 * @author alexg
 */
public class ModeloTablaAutoListas extends AbstractTableModel {
    private LinkedList<Auto> autos = new LinkedList<>();

    @Override
    public int getRowCount() {
        return autos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Auto auto = null;
        try {
            auto = autos.get(rowIndex);
        } catch (Exception e) {
        }

        switch (columnIndex) {
            case 0:
                return (auto != null) ? auto.getColor() : "";
            case 1:
                return (auto != null) ? "$ " + auto.getPrecio() : "";
            case 2:
                return (auto != null) ? auto.getAnio() : "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Color";
            case 1:
                return "Precio de Venta";
            case 2:
                return "Anio";
            default:
                return null;
        }
    }

    /**
     * @return the autos
     */
    public LinkedList<Auto> getAutos() {
        return autos;
    }

    /**
     * @param autos the llantas to set
     */
    public void setAutos(LinkedList<Auto> autos) {
        this.autos = autos;
    }
    
}

