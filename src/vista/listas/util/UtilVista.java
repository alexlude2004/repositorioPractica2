
package vista.listas.util;

import controlador.CursoControllerListas;
import controlador.TDA.listas.exception.VacioException;
import javax.swing.JComboBox;
import modelo.Curso;

/**
 *
 * @author alexg
 */
public class UtilVista {
    
    public static void cargarCurso(JComboBox cbxCurso) throws VacioException {
        CursoControllerListas ac = new CursoControllerListas();
        cbxCurso.removeAllItems();
        for (int i = 0; i < ac.getCursos().getSize(); i++) {
            cbxCurso.addItem(ac.getCursos().get(i));
        }
    }  
    
    public static Curso getComboMarcas(JComboBox cbx) {
        return (Curso)cbx.getSelectedItem();
    }    
     
}
