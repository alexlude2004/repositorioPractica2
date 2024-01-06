
package vista.listas.util;

import controlador.CursoControllerListas;
import controlador.MallaControllerListas;
import controlador.TDALista.exceptions.VacioException;
import javax.swing.JComboBox;
import modelo.Curso;
import modelo.Malla;

/**
 *
 * @author alexg
 */
public class UtilVista {
    
    public static void cargarMalla(JComboBox cbxMalla) throws VacioException {
        MallaControllerListas ac = new MallaControllerListas();
        cbxMalla.removeAllItems();
        for (int i = 0; i < ac.getMallas().getSize(); i++) {
            cbxMalla.addItem(ac.getMallas().get(i));
        }
    }  
    
    public static Malla getComboMallas(JComboBox cbx) {
        return (Malla)cbx.getSelectedItem();
    }    
     
    public static void cargarCurso(JComboBox cbxCurso) throws VacioException {
        CursoControllerListas ac = new CursoControllerListas();
        cbxCurso.removeAllItems();
        for (int i = 0; i < ac.getCursos().getSize(); i++) {
            cbxCurso.addItem(ac.getCursos().get(i));
        }
    }  
    
    public static Curso getComboCursos(JComboBox cbx) {
        return (Curso)cbx.getSelectedItem();
    }        
}
