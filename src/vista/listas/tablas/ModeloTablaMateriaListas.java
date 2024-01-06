
package vista.listas.tablas;

import controlador.CursoControllerListas;
import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Curso;
import modelo.Materia;

/**
 *
 * @author alexg
 */
public class ModeloTablaMateriaListas extends AbstractTableModel{

    private LinkedList<Materia> materias = new LinkedList<>();
    
    @Override
    public int getRowCount() {
        return getMaterias().getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Materia materia = null;
        try {
            materia = materias.get(rowIndex);
            Curso curso = new CursoControllerListas().getCursos().get(materia.getId_curso() - 1);
            switch (columnIndex) {
                case 0:
                    return (materia != null) ? materia.getId() : "";
                case 1:
                    return (materia != null) ? materia.getNombre() : "";   
                case 2:
                    return (materia != null) ? "Ciclo: " + curso.getCiclo() + " --- Paralelo: " + curso.getParalelo() : "";
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
                return "Curso";
            default:
                return null;
        }
    }

    /**
     * @return the materias
     */
    public LinkedList<Materia> getMaterias() {
        return materias;
    }

    /**
     * @param materias the materias to set
     */
    public void setMaterias(LinkedList<Materia> materias) {
        this.materias = materias;
    }
    
}
