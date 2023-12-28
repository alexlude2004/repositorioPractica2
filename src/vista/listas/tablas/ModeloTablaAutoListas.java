
package vista.listas.tablas;

import controlador.MarcaControllerListas;
import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Auto;
import modelo.Marca;

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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Auto auto = null;
            auto = autos.get(rowIndex);
            Marca marca = new MarcaControllerListas().getMarcas().get(auto.getId_marca() - 1);
            switch (columnIndex) {
                case 0:
                    return (auto != null) ? auto.getId() : "";
                case 1:
                    return (auto != null) ? auto.getModelo() : "";
                case 2:
                    return (auto != null) ? marca.getNombre() : "";                    
                case 3:
                    return (auto != null) ? auto.getAnio() : "";
                case 4:
                    return (auto != null) ? auto.getColor() : "";
                case 5:
                    return (auto != null) ? "$ " + auto.getPrecio() : "";
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Modelo";
            case 2:
                return "Marca";
            case 3:
                return "Anio";
            case 4:
                return "Color";
            case 5:
                return "Precio de venta";                
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

