
package vista.listas.tablas;

import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Curso;

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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = null;
        try {
            curso = getCursos().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (curso != null) ? curso.getId() : "";
                case 1:
                    return (curso != null) ? curso.getCiclo() : "";    
                case 2:
                    return (curso != null) ? curso.getParalelo(): "";
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
