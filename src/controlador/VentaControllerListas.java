
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Venta;

/**
 *
 * @author alexg
 */
public class VentaControllerListas extends DataAccessObject<Venta> {
    private LinkedList<Venta> ventas = new LinkedList<>();
    private Venta venta = new Venta();    
    private Integer index = - 1;
    
    public VentaControllerListas() {
        super(Venta.class);
    }
    
    /**
     * @return the ventas
     */
    public LinkedList<Venta> getVentas() {
        if (ventas.isEmpty())
            ventas = listAll();
        return ventas;
    }

    /**
     * @param ventas the llantas to set
     */
    public void setVentas(LinkedList<Venta> ventas) {
        this.ventas = ventas;
    }
        
    /**
     * @return the venta
     */
    public Venta getVenta() {
        if (venta == null) {
            venta = new Venta();
        }
        return venta;
    }

    /**
     * @param venta the llanta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public Boolean save() {
        this.venta.setId(generar_id());
        return save(venta);
    }
    
    public Boolean update(Integer index) {
        return update(venta, index);
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
    
    public LinkedList<Venta> ordenar(Integer type, String field, LinkedList<Venta> lista, String algoritmo) throws Exception {
        getVentas();  
        Integer n = lista.getSize();    
        Venta[] v = lista.toArray();   
        Field faux = Utilidades.getField(Venta.class, field);    
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

    private void mergeSort(Venta[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int m = 0;
        if (inicio < fin) {    
            m = (inicio + fin) / 2;     
            mergeSort(arreglo, inicio, m, type, field);      
            mergeSort(arreglo, m + 1, fin, type, field);     
            merge(arreglo, inicio, m, fin, type, field);       
        }
    }

    private void merge(Venta[] arreglo, int inicio, int m, int fin, Integer type, String field) throws Exception {
        int k = 0;
        int i = inicio;
        int j = m + 1;
        int n = fin - inicio + 1;
        Venta[] b = new Venta[n];
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

    public void quickSort(Venta[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Venta pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Venta aux = arreglo[i];
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
