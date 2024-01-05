
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.pis.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Curso;

/**
 *
 * @author alexg
 */
public class CursoControllerListas extends DataAccessObject<Curso>{
    
    private LinkedList<Curso> cursos = new LinkedList<>();
    private Curso curso = new Curso();
    private Integer index = -1;
    
    public CursoControllerListas() {
        super(Curso.class);
    }

    /**
     * @return the cursos
     */
    public LinkedList<Curso> getCursos() {
        if (cursos.isEmpty()) 
            cursos = listAll();
            return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(LinkedList<Curso> cursos) {
        this.cursos = cursos;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        if (curso == null) {
            curso = new Curso();
        }
        return curso;
    }

    /**
     * @param curso the materia to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
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
        this.curso.setId(generar_id());
        return save(curso);
    }
    
    public Boolean update(Integer index) {
        return update(curso, index);
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
    
    public LinkedList<Curso> quickSort(Integer type, String field, LinkedList<Curso> lista) throws Exception {
        getCurso();  
        Integer n = lista.getSize();    
        Curso[] c = lista.toArray();   
        Field faux = Utilidades.getField(Curso.class, field);    
        if (faux != null) {    
            quickSort(c, 0, n - 1, type, field);    
            lista = lista.toList(c);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }

    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Curso[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Curso pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Curso aux = arreglo[i];
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
    
    public LinkedList<Curso> buscarCiclo(LinkedList<Curso> lista, String text, Integer ciclo) throws Exception {
        LinkedList<Curso> lo = this.quickSort(0, text, lista);
        Curso[] c = lo.toArray();
        LinkedList<Curso> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getCiclo().intValue() == ciclo.intValue()) {
                result.add(c[i]);
            }
        }
        return result;
    }

    public LinkedList<Curso> buscarParalelo(LinkedList<Curso> lista, String text, String paralelo) throws Exception {
        LinkedList<Curso> lo = this.quickSort(0, text, lista);
        Curso[] c = lo.toArray();
        LinkedList<Curso> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getParalelo().equals(paralelo)) {
                result.add(c[i]);
            }
        }
        return result;
    }
}
