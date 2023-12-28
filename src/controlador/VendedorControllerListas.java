
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Vendedor;

/**
 *
 * @author alexg
 */
public class VendedorControllerListas extends DataAccessObject<Vendedor> {
    private LinkedList<Vendedor> vendedores = new LinkedList<>();
    private Vendedor vendedor = new Vendedor();
    private Integer index = - 1;
    
    public VendedorControllerListas() {
        super(Vendedor.class);
    }

    /**
     * @return the vendedores
     */
    public LinkedList<Vendedor> getVendedores() {
        if (vendedores.isEmpty())
            vendedores = listAll();
        return vendedores;
    }

    /**
     * @param vendedores the llantas to set
     */
    public void setVendedores(LinkedList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
        
    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        if (vendedor == null) {
            vendedor = new Vendedor();
        }
        return vendedor;
    }

    /**
     * @param vendedor the llanta to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public Boolean save() {
        this.vendedor.setId(generar_id());
        return save(vendedor);
    }
    
    public Boolean update(Integer index) {
        return update(vendedor, index);
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
    
    public LinkedList<Vendedor> ordenar(Integer type, String field, LinkedList<Vendedor> lista, String algoritmo) throws Exception {
        getVendedores();  
        Integer n = lista.getSize();    
        Vendedor[] v = lista.toArray();   
        Field faux = Utilidades.getField(Vendedor.class, field);    
        if (faux != null) {    
            if (algoritmo.equalsIgnoreCase("quickSort")) {
                quickSort(v, 0, n - 1, type, field);    
            } else if (algoritmo.equalsIgnoreCase("mergeSort")) {
                mergeSort(v, 0, n - 1, type, field); 
            } else {
                throw new Exception("El algoritmo de ordenamiento especificado no es válido");      
            }
            lista = lista.toList(v);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }    

    // Metodo de Ordenamiento: MERGE SORT

    private void mergeSort(Vendedor[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int m = 0;
        if (inicio < fin) {    
            m = (inicio + fin) / 2;     
            mergeSort(arreglo, inicio, m, type, field);      
            mergeSort(arreglo, m + 1, fin, type, field);     
            merge(arreglo, inicio, m, fin, type, field);       
        }
    }

    private void merge(Vendedor[] arreglo, int inicio, int m, int fin, Integer type, String field) throws Exception {
        int k = 0;
        int i = inicio;
        int j = m + 1;
        int n = fin - inicio + 1;
        Vendedor[] b = new Vendedor[n];
        while (i <= m && j <= fin) {            
            if (arreglo[i].comparar(arreglo[j], field, type)) {
                b[k] = arreglo[i];
                i++;
                k++;
            } else {
                b[k] = arreglo[j];
                j++;
                k++;
            }
        }
        while (i <= m) {            
            b[k] = arreglo[i];
            i++;
            k++;
        }
        while (j <= fin) {            
            b[k] = arreglo[j];
            j++;
            k++;
        }
        for (k = 0; k < n; k++) {
            arreglo[inicio + k] = b[k];
        }
    }
    
    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Vendedor[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Vendedor pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Vendedor aux = arreglo[i];
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
    
}
