
package vista.listas.tablas;

import controlador.MallaControllerListas;
import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Curso;
import modelo.Malla;

/**
 *
 * @author alexg
 */
public class ModeloTablaCursoListas extends AbstractTableModel{

    private LinkedList<Curso> cursos = new LinkedList<>();
    
    @Override
    public int getRowCount() {
        return getCursos().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = null;
        try {
            curso = cursos.get(rowIndex);
            Malla malla = new MallaControllerListas().getMallas().get(curso.getId_malla() - 1);
            switch (columnIndex) {
                case 0:
                    return (curso != null) ? curso.getId() : "";
                case 1:
                    return (curso != null) ? curso.getCiclo() : "";    
                case 2:
                    return (curso != null) ? curso.getParalelo() : "";
                case 3:
                    return (curso != null) ? malla.toString() + " --- " + malla.getModalidad() : "";
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
                return "Ciclo";   
            case 2:
                return "Paralelo";
            case 3:
                return "Malla";
            default:
                return null;
        }
    }
    

    /**
     * @return the cursos
     */
    public LinkedList<Curso> getCursos() {
        return cursos;
    }

    /**
     * @param cursos the materias to set
     */
    public void setCursos(LinkedList<Curso> cursos) {
        this.cursos = cursos;
    }
    
}
