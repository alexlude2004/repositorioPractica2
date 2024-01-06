
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.pis.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Materia;

/**
 *
 * @author alexg
 */
public class MateriaControllerListas extends DataAccessObject<Materia>{
    
    private LinkedList<Materia> materias = new LinkedList<>();
    private Materia materia = new Materia();
    private Integer index = -1;
    
    public MateriaControllerListas() {
        super(Materia.class);
    }

    /**
     * @return the materias
     */
    public LinkedList<Materia> getMaterias() {
        if (materias.isEmpty()) 
            materias = listAll();
            return materias;
    }

    /**
     * @param materias the materias to set
     */
    public void setMaterias(LinkedList<Materia> materias) {
        this.materias = materias;
    }

    /**
     * @return the materia
     */
    public Materia getMateria() {
        if (materia == null) {
            materia = new Materia();
        }
        return materia;
    }

    /**
     * @param materia the materia to set
     */
    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean save() {
        this.materia.setId(generar_id());
        return save(materia);
    }
    
    public Boolean update(Integer index) {
        return update(materia, index);
    }
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listAll().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }
    
    public LinkedList<Materia> quickSort(Integer type, String field, LinkedList<Materia> lista) throws Exception {
        getMateria();  
        Integer n = lista.getSize();    
        Materia[] m = lista.toArray();   
        Field faux = Utilidades.getField(Materia.class, field);    
        if (faux != null) {    
            quickSort(m, 0, n - 1, type, field);    
            lista = lista.toList(m);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }

    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Materia[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Materia pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Materia aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);

        if (inicio < j)
            quickSort(arreglo, inicio, j, type, field);
        if (i < fin)
            quickSort(arreglo, i, fin, type, field);
    }

    //Busqueda Lineal
    
    public LinkedList<Materia> buscarNombre(LinkedList<Materia> lista, String text, String clave) throws Exception {
        LinkedList<Materia> lo = this.quickSort(0, text, lista);
        Materia[] m = lo.toArray();
        LinkedList<Materia> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getNombre().toLowerCase().startsWith((String) clave)) {
                result.add(m[i]);
            }
        }
        return result;
    }
    
    public LinkedList<Materia> buscarCurso(LinkedList<Materia> lista, String text, Integer clave) throws Exception {
        LinkedList<Materia> lo = this.quickSort(0, text, lista);
        Materia[] m = lo.toArray();
        LinkedList<Materia> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId_curso().intValue() == clave ) {
                result.add(m[i]);
            }
        }
        return result;
    }    

}
