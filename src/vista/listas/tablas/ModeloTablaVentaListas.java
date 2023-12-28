
package vista.listas.tablas;

import controlador.AutoControllerListas;
import controlador.MarcaControllerListas;
import controlador.TDA.listas.LinkedList;
import controlador.VendedorControllerListas;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import modelo.Auto;
import modelo.Marca;
import modelo.Vendedor;
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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Venta venta = null;
            venta = ventas.get(rowIndex);
            Auto auto = new AutoControllerListas().getAutos().get(venta.getId_auto() - 1);
            Marca marca = new MarcaControllerListas().getMarcas().get(auto.getId_marca() - 1);
            Vendedor vendedor = new VendedorControllerListas().getVendedores().get(venta.getId_vendedor() - 1);
            
            switch (columnIndex) {
                case 0:
                    return (venta != null) ? venta.getCodigo_venta() : "";
                case 1:
                    return (venta != null) ? new SimpleDateFormat("dd / MM / yy").format(venta.getFecha()) : ""; 
                case 2:
                    return (venta != null) ? vendedor.toString() : "";                
                case 3:
                    return (venta != null) ? auto.getModelo() : "";
                case 4:
                    return (venta != null) ? marca.getNombre() : "";
                case 5:
                    return (venta != null) ? "$ " + auto.getPrecio() : "";
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
                return "Codigo de Venta";
            case 1:
                return "Fecha";
            case 2:
                return "Agente Vendedor";                
            case 3:
                return "Auto Vendido";
            case 4:
                return "Marca del Auto";
            case 5:
                return "Precio Vendido";
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

