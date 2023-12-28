
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Auto;

/**
 *
 * @author alexg
 */
public class AutoControllerListas extends DataAccessObject<Auto> {
    private LinkedList<Auto> autos = new LinkedList<>();
    private Auto auto = new Auto();    
    private Integer index = - 1;
    
    public AutoControllerListas() {
        super(Auto.class);
    }
    
    /**
     * @return the autos
     */
    public LinkedList<Auto> getAutos() {
        if (autos.isEmpty())
            autos = listAll();
        return autos;
    }

    /**
     * @param autos the llantas to set
     */
    public void setAutos(LinkedList<Auto> autos) {
        this.autos = autos;
    }
        
    /**
     * @return the auto
     */
    public Auto getAuto() {
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    /**
     * @param auto the llanta to set
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }
    
    public Boolean save() {
        this.auto.setId(generar_id());
        return save(auto);
    }
    
    public Boolean update(Integer index) {
        return update(auto, index);
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
    
    public LinkedList<Auto> ordenar(Integer type, String field, LinkedList<Auto> lista, String algoritmo) throws Exception {
        getAutos();  
        Integer n = lista.getSize();    
        Auto[] a = lista.toArray();   
        Field faux = Utilidades.getField(Auto.class, field);    
        if (faux != null) {    
            if (algoritmo.equalsIgnoreCase("quickSort")) {
                quickSort(a, 0, n - 1, type, field);    
            } else if (algoritmo.equalsIgnoreCase("mergeSort")) {
                mergeSort(a, 0, n - 1, type, field); 
            } else {
                throw new Exception("El algoritmo de ordenamiento especificado no es válido");      
            }
            lista = lista.toList(a);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }
    
    // Metodo de Ordenamiento: MERGE SORT

    private void mergeSort(Auto[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int m = 0;
        if (inicio < fin) {    
            m = (inicio + fin) / 2;     
            mergeSort(arreglo, inicio, m, type, field);      
            mergeSort(arreglo, m + 1, fin, type, field);     
            merge(arreglo, inicio, m, fin, type, field);       
        }
    }

    private void merge(Auto[] arreglo, int inicio, int m, int fin, Integer type, String field) throws Exception {
        int k = 0;
        int i = inicio;
        int j = m + 1;
        int n = fin - inicio + 1;
        Auto[] b = new Auto[n];
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

    public void quickSort(Auto[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Auto pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Auto aux = arreglo[i];
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

    
    //Metodo de Busqueda: BINARIA
    
    public int busquedaBinaria(Auto[] arreglo, String field, int inicio, int fin) throws Exception {
        int medio;
        if (inicio > fin) return -1;
        else {
            medio = (inicio + fin) / 2;
            Auto pivote = arreglo[medio];
            if (pivote.comparar(pivote, field, 0)) {
                return busquedaBinaria(arreglo, field, medio + 1, fin);
            } else if (!pivote.comparar(pivote, field, 0)) {
                return busquedaBinaria(arreglo, field, inicio, medio - 1);
            } else {
                return medio;
            }
        }
    }    

//    public int busquedaBin(Auto[] a, Auto clave, String atributo) throws Exception {
//        int central, bajo, alto;
//        double valorCentral; // Cambiado a double para manejar precios
//        bajo = 0;
//        alto = a.length - 1;
//        while (bajo <= alto) {
//            central = (bajo + alto) / 2;
//            Method method = a[central].getClass().getMethod("get" + atributo);
//            valorCentral = (Double) method.invoke(a[central]); // Cambiado a Double
//            Method claveMethod = clave.getClass().getMethod("get" + atributo);
//            double valorClave = (Double) claveMethod.invoke(clave); // Cambiado a Double
//            if (valorClave == valorCentral)
//                return central;
//            else if (valorClave < valorCentral)
//                alto = central - 1;
//            else
//                bajo = central + 1;
//        }
//        return -1;
//    }
//
//    public LinkedList<Auto> buscarPrecioMayor(LinkedList<Auto> lista, String text, Double precio) throws Exception {
//        LinkedList<Auto> lo = this.quickSort(0, text, lista); // Asegúrate de tener implementado quickSort
//        Auto[] a = lo.toArray(); // Convertir la lista a un array de Autos
//        LinkedList<Auto> result = new LinkedList<>();
//        Auto autoReferencia = new Auto();
//        autoReferencia.setPrecio(precio);
//        int indice = busquedaBin(a, autoReferencia, "Precio"); // Usar busquedaBin con el array de Autos
//        if (indice != -1) {
//            for (int i = indice; i < a.length && a[i].getPrecio() >= precio.doubleValue(); i++) {
//                result.add(a[i]);
//            }
//            for (int i = indice - 1; i >= 0 && a[i].getPrecio() > precio.doubleValue(); i--) {
//                result.addFirst(a[i]); // Agregar al principio para mantener el orden
//            }
//        }
//        return result;
//    }
//
//
//    public LinkedList<Auto> buscarMarca(LinkedList<Auto> lista, String text, Marca marca) throws Exception {
//        LinkedList<Auto> lo = this.quickSort(0, text, lista);
//        Auto[] a = lo.toArray(); // Convertir la lista a un array de Autos
//        LinkedList<Auto> result = new LinkedList<>();
//
//        // Crear un objeto de referencia con la marca específica para la búsqueda
//        Auto autoReferencia = new Auto();
//        autoReferencia.setId_marca(marca.getId());
//
//        int indice = busquedaBin(a, autoReferencia, "Id_marca"); // Usar busquedaBin con el array de Autos
//
//        // Verificar si se encontró la marca en la lista ordenada
//        if (indice != -1) {
//            // Iterar sobre la lista ordenada y agregar todos los elementos con la marca específica
//            for (int i = indice; i < a.length && a[i].getId_marca().equals(marca.getId()); i++) {
//                result.add(a[i]);
//            }
//            for (int i = indice - 1; i >= 0 && a[i].getId_marca().equals(marca.getId()); i--) {
//                result.addFirst(a[i]); // Agregar al principio para mantener el orden
//            }
//        }
//        return result;
//    }

     //Metodo de Busqueda: LINEAL BINARIA
    
    public LinkedList<Auto> busquedaLinealBinaria(LinkedList<Auto> lista, String text, Double precio) throws Exception {
        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
        Auto[] a = lo.toArray();
        LinkedList<Auto> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (a[i].getPrecio().doubleValue() <= precio.doubleValue()) {
                result.add(a[i]);
            }
        }
        return result;
    }

}
