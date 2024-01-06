
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.listas.DAO.DataAccesObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import java.util.Date;
import modelo.Malla;

/**
 *
 * @author alexg
 */
public class MallaControllerListas extends DataAccesObject<Malla> {
    
    private LinkedList<Malla> mallas = new LinkedList<>();
    private Malla malla = new Malla();
    private Integer index = -1;
    
    public MallaControllerListas() {
        super(Malla.class);
    }

    /**
     * @return the mallas
     */
    public LinkedList<Malla> getMallas() {
        if (mallas.isEmpty()) 
            mallas = listAll();
            return mallas;
    }

    /**
     * @param mallas the mallas to set
     */
    public void setMallas(LinkedList<Malla> mallas) {
        this.mallas = mallas;
    }

    /**
     * @return the malla
     */
    public Malla getMalla() {
        if (malla == null) {
            malla = new Malla();
        }
        return malla;
    }

    /**
     * @param malla the malla to set
     */
    public void setMalla(Malla malla) {
        this.malla = malla;
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
        this.malla.setId(generated_id());
        return save(malla);
    } 
    
    public Boolean update(Integer index) {
        return update(malla, index);
    }    
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listAll().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (3 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }
    
    public LinkedList<Malla> quickSort(Integer type, String field, LinkedList<Malla> lista) throws Exception {
        getMalla();  
        Integer n = lista.getSize();    
        Malla[] m = lista.toArray();   
        Field faux = Utilidades.getField(Malla.class, field);    
        if (faux != null) {    
            quickSort(m, 0, n - 1, type, field);    
            lista = lista.toList(m);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }

    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Malla[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Malla pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Malla aux = arreglo[i];
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
    
    public LinkedList<Malla> buscarNombre(LinkedList<Malla> lista, String text, String clave) throws Exception {
        LinkedList<Malla> lo = this.quickSort(0, text, lista);
        Malla[] m = lo.toArray();
        LinkedList<Malla> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getNombre().toLowerCase().startsWith( clave.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }
        
    public LinkedList<Malla> buscarModalidad(LinkedList<Malla> lista, String text, String clave) throws Exception {
        LinkedList<Malla> lo = this.quickSort(0, text, lista);
        Malla[] m = lo.toArray();
        LinkedList<Malla> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getModalidad().toLowerCase().startsWith( clave.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }    
    
    public LinkedList<Malla> buscarFecha(LinkedList<Malla> lista, String text, Date clave) throws Exception {
        LinkedList<Malla> lo = this.quickSort(0, text, lista);
        Malla[] m = lo.toArray();
        LinkedList<Malla> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getFecha_Creacion().equals(clave)) {
                result.add(m[i]);
            }
        }
        return result;
    }        
}
