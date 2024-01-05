
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Marca;
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
    
    // Método principal para ordenar la lista de ventas
    
    public LinkedList<Venta> ordenar(Integer type, String field, LinkedList<Venta> lista, String algoritmo) throws Exception {
        getVentas();  // Obtener las ventas
        Integer n = lista.getSize();  // Obtener el tamaño de la lista
        Venta[] v = lista.toArray();  // Convertir la lista a un arreglo
        Field faux = Utilidades.getField(Venta.class, field);  // Obtener el campo por el cual se va a ordenar
        if (faux != null) {  // Si el campo existe
            if (algoritmo.equalsIgnoreCase("quickSort")) {  // Si el algoritmo es QuickSort
                quickSort(v, 0, n - 1, type, field);  // Llamar al método QuickSort
            } else if (algoritmo.equalsIgnoreCase("mergeSort")) {  // Si el algoritmo es MergeSort
                mergeSort(v, 0, n - 1, type, field);  // Llamar al método MergeSort
            } else {
                throw new Exception("El algoritmo de ordenamiento especificado no es válido");  // Si el algoritmo no es válido, lanzar una excepción
            }
            lista = lista.toList(v);  // Convertir el arreglo ordenado de nuevo a una lista
        } else {
            throw new Exception("No existe ese criterio de busqueda");  // Si el campo no existe, lanzar una excepción
        }
        return lista;  // Devolver la lista ordenada
    }      

    // Método de Ordenamiento: MERGE SORT

    // Método principal de MergeSort
    private void mergeSort(Venta[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int m = 0;
        if (inicio < fin) {  // Si el inicio es menor que el fin
            m = (inicio + fin) / 2;  // Calcular el punto medio
            mergeSort(arreglo, inicio, m, type, field);  // Llamar a MergeSort para la primera mitad
            mergeSort(arreglo, m + 1, fin, type, field);  // Llamar a MergeSort para la segunda mitad
            merge(arreglo, inicio, m, fin, type, field);  // Combinar ambas mitades
        }
    }

    // Método para combinar dos mitades en MergeSort
    private void merge(Venta[] arreglo, int inicio, int m, int fin, Integer type, String field) throws Exception {
        int k = 0;
        int i = inicio;
        int j = m + 1;
        int n = fin - inicio + 1;
        Venta[] b = new Venta[n];
        while (i <= m && j <= fin) {  // Mientras haya elementos en ambas mitades
            if (arreglo[i].comparar(arreglo[j], field, type)) {  // Si el elemento de la primera mitad es menor
                b[k] = arreglo[i];  // Agregarlo al arreglo temporal
                i++;
                k++;
            } else {  // Si el elemento de la segunda mitad es menor
                b[k] = arreglo[j];  // Agregarlo al arreglo temporal
                j++;
                k++;
            }
        }
        while (i <= m) {  // Si quedan elementos en la primera mitad
            b[k] = arreglo[i];  // Agregarlos al arreglo temporal
            i++;
            k++;
        }
        while (j <= fin) {  // Si quedan elementos en la segunda mitad
            b[k] = arreglo[j];  // Agregarlos al arreglo temporal
            j++;
            k++;
        }
        for (k = 0; k < n; k++) {  // Copiar los elementos del arreglo temporal al arreglo original
            arreglo[inicio + k] = b[k];
        }
    }

    // Método de Ordenamiento: QUICK SORT

    // Método principal de QuickSort
    public void quickSort(Venta[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Venta pivote = arreglo[(inicio + fin) / 2];  // Calcular el pivote
        do {
            while (arreglo[i].comparar(pivote, field, type)) {  // Mientras el elemento sea menor que el pivote
                i++;  // Avanzar al siguiente elemento
            }
            while (pivote.comparar(arreglo[j], field, type)) {  // Mientras el pivote sea menor que el elemento
                j--;  // Retroceder al elemento anterior
            }
            if (i <= j) {  // Si los índices no se han cruzado
                Venta aux = arreglo[i];  // Intercambiar los elementos
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;  // Avanzar al siguiente elemento
                j--;  // Retroceder al elemento anterior
            }
        } while (i <= j);  // Mientras los índices no se hayan cruzado

        if (inicio < j)  // Si quedan elementos a la izquierda del pivote
            quickSort(arreglo, inicio, j, type, field);  // Llamar a QuickSort para esa parte
        if (i < fin)  // Si quedan elementos a la derecha del pivote
            quickSort(arreglo, i, fin, type, field);  // Llamar a QuickSort para esa parte
    }

    //Metodo de Busqueda: BINARIA    
    
    public LinkedList<Venta> busquedaBinaria(LinkedList<Venta> lista, String text, Object clave, String tipo) throws Exception {
        LinkedList<Venta> lo = this.ordenar(0, text, lista, "quicksort");
        Venta[] v = lo.toArray();
        LinkedList<Venta> result = new LinkedList<>();
        int inicio = 0;
        int fin = lo.getSize() - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Object valorActual;
            switch (tipo) {
                case "fecha":
                    valorActual = v[medio].getFecha();
                    break;
                case "codigo_venta":
                    valorActual = v[medio].getCodigo_venta();
                    break;
                case "auto":
                    valorActual = v[medio].getId_auto();
                    break;
                case "vendedor":
                    valorActual = v[medio].getId_vendedor();
                    break;                
                default:
                    throw new Exception("Tipo de búsqueda no válido");
            }
            if (valorActual.equals(clave)) {
                result.add(v[medio]);
                break;
            }
            if (valorActual instanceof Integer) {
                if ((Integer) valorActual < (Integer) clave) {
                    inicio = medio + 1;
                } else {
                    fin = medio - 1;
                }
            } else if (valorActual instanceof String) {
                if (((String) valorActual).compareTo((String) clave) < 0) {
                    inicio = medio + 1;
                } else {
                    fin = medio - 1;
                }
            } else if (valorActual instanceof Date) {
                if (((Date) valorActual).compareTo((Date) clave) < 0) {
                    inicio = medio + 1;
                } else {
                    fin = medio - 1;
                }
            } else {
                throw new Exception("Tipo de clave no soportado");
            }
        }
        return result;
    }
    
     //Metodo de Busqueda: LINEAL BINARIA
    
    public LinkedList<Venta> busquedaLinealBinaria(LinkedList<Venta> lista, String text, Object clave, String tipo) throws Exception {
        LinkedList<Venta> lo = this.ordenar(0, text, lista, "quicksort");
        Venta[] v = lo.toArray();
        LinkedList<Venta> result = new LinkedList<>();
        int inicio = 0;
        int fin = lo.getSize() - 1;
        int medio = -1;

        for (int i = 0; i < v.length; i++) {
            Object valorActual = getValor(v[i], tipo);
            if (valorActual.equals(clave)) {
                medio = i;
                result.add(v[i]);
                break;
            }
        }

        if (medio != -1) {
            int temp = medio - 1;
            while (temp >= 0 && getValor(v[temp], tipo).equals(clave)) {
                result.add(v[temp]);
                temp--;
            }

            temp = medio + 1;
            while (temp < v.length && getValor(v[temp], tipo).equals(clave)) {
                result.add(v[temp]);
                temp++;
            }
        }

        return result;
    }  

    private Object getValor(Venta venta, String tipo) {
        switch (tipo) {
            case "fecha":
                return venta.getFecha();
            case "codigo_venta":
                return venta.getCodigo_venta();
            case "auto":
                return venta.getId_auto();
            case "vendedor":
                return venta.getId_vendedor();            
            default:
                return null;
        }
    }    
        
}
