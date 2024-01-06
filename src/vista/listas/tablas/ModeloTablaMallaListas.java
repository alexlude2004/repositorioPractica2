
package vista.listas.tablas;

import controlador.TDA.listas.LinkedList;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import modelo.Malla;

/**
 *
 * @author alexg
 */
public class ModeloTablaMallaListas extends AbstractTableModel {
    
    private LinkedList<Malla> mallas = new LinkedList<>();    

    @Override
    public int getRowCount() {
        return getMallas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Malla malla = null;
        try {
            malla = getMallas().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (malla != null) ? malla.getId() : "";
                case 1:
                    return (malla != null) ? malla.getNombre() : "";    
                case 2:
                    return (malla != null) ? malla.getCod_resolucion() : "";
                case 3:
                    return (malla != null) ? malla.getModalidad() : "";
                case 4:
                    return (malla != null) ? new SimpleDateFormat("dd / MM / yy").format(malla.getFecha_Creacion()) : "";
                case 5:
                    return (malla != null) ? (malla.getEstado() ? "Vigente" : "Deshabilitado") : "";           
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
                return "Nombre";   
            case 2:
                return "Codigo de Resolucion";
            case 3:
                return "Modalidad";
            case 4:
                return "Fecha de Creacion";
            case 5:
                return "Estado";                
            default:
                return null;
        }
    }    

    /**
     * @return the mallas
     */
    public LinkedList<Malla> getMallas() {
        return mallas;
    }

    /**
     * @param mallas the mallas to set
     */
    public void setMallas(LinkedList<Malla> mallas) {
        this.mallas = mallas;
    }
    
}
