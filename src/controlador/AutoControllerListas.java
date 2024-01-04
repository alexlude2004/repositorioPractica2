
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.exception.VacioException;
import controlador.autos.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Auto;
import modelo.Marca;

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

//    public LinkedList<Auto> buscarPrecioMayor(LinkedList<Auto> lista, String text, double precio) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        // You need to decide which Auto object you want to search for
//        Auto clave = new Auto();
////        int indice = busquedaBin(a, clave, comparator);
//
//        LinkedList<Auto> result = new LinkedList<>();
//        if (indice != -1) {
//            for (int i = indice; i < lo.size() && lo.get(i).getPrecio() >= precio; i++) {
//                result.add(lo.get(i));
//            }
//
//            for (int i = indice - 1; i >= 0 && lo.get(i).getPrecio() > precio; i--) {
//                result.addFirst(lo.get(i));
//            }
//        }
//        return result;
//    }

    public LinkedList<Auto> busquedaBinariaPrecioMayor(LinkedList<Auto> lista, String text, Double precio) throws Exception {
        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
        Auto[] a = lo.toArray();
        LinkedList<Auto> result = new LinkedList<>();
        int left = 0;
        int right = lo.getSize() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid].getPrecio().doubleValue() > precio.doubleValue()) {
                // Agrega el auto encontrado a la lista de resultados
                result.add(a[mid]);
                // Busca a la izquierda del punto medio
                int temp = mid - 1;
                while (temp >= 0 && a[temp].getPrecio().doubleValue() > precio.doubleValue()) {
                    result.add(a[temp]);
                    temp--;
                }
                // Busca a la derecha del punto medio
                temp = mid + 1;
                while (temp < a.length && a[temp].getPrecio().doubleValue() > precio.doubleValue()) {
                    result.add(a[temp]);
                    temp++;
                }
                break;
            }
            if (a[mid].getPrecio().doubleValue() <= precio.doubleValue()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }


//    public LinkedList<Auto> busquedaBinariaMarca(LinkedList<Auto> lista, String text, Marca marca) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int left = 0;
//        int right = lo.getSize() - 1;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (a[mid].getId_marca().equals(marca.getId())) {
//                // Agrega el auto encontrado a la lista de resultados
//                result.add(a[mid]);
//                // Busca a la izquierda del punto medio
//                int temp = mid - 1;
//                while (temp >= 0 && a[temp].getId_marca().equals(marca.getId())) {
//                    result.add(a[temp]);
//                    temp--;
//                }
//                // Busca a la derecha del punto medio
//                temp = mid + 1;
//                while (temp < a.length && a[temp].getId_marca().equals(marca.getId())) {
//                    result.add(a[temp]);
//                    temp++;
//                }
//                break;
//            }
//            if (a[mid].getId_marca().compareTo(marca.getId()) < 0) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return result;
//    }

    //Busqueda Binaria - Precio menor
    public LinkedList<Auto> busquedaBinariaPrecioMenor(LinkedList<Auto> lista, String text, Double precio) throws Exception {
        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
        Auto[] a = lo.toArray();
        LinkedList<Auto> result = new LinkedList<>();
        int left = 0;
        int right = lo.getSize() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid].getPrecio().doubleValue() < precio.doubleValue()) {
                // Agrega el auto encontrado a la lista de resultados
                result.add(a[mid]);
                // Busca a la izquierda del punto medio
                int temp = mid - 1;
                while (temp >= 0 && a[temp].getPrecio().doubleValue() < precio.doubleValue()) {
                    result.add(a[temp]);
                    temp--;
                }
                // Busca a la derecha del punto medio
                temp = mid + 1;
                while (temp < a.length && a[temp].getPrecio().doubleValue() < precio.doubleValue()) {
                    result.add(a[temp]);
                    temp++;
                }
                break;
            }
            if (a[mid].getPrecio().doubleValue() >= precio.doubleValue()) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public LinkedList<Auto> busquedaBinariaModelo(LinkedList<Auto> lista, String text, String modelo) throws Exception {
        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
        Auto[] a = lo.toArray();
        LinkedList<Auto> result = new LinkedList<>();
        int left = 0;
        int right = lo.getSize() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid].getModelo().startsWith(modelo)) {
                // Agrega el auto encontrado a la lista de resultados
                result.add(a[mid]);
                // Busca a la izquierda del punto medio
                int temp = mid - 1;
                while (temp >= 0 && (a[temp].getModelo().startsWith(modelo))) {
                    result.add(a[temp]);
                    temp--;
                }
                // Busca a la derecha del punto medio
                temp = mid + 1;
                while (temp < a.length && (a[temp].getModelo().startsWith(modelo))) {
                    result.add(a[temp]);
                    temp++;
                }
                break;
            }
            if (a[mid].getModelo().compareTo(modelo) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

//    public LinkedList<Auto> busquedaBinariaColor(LinkedList<Auto> lista, String text, String color) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int left = 0;
//        int right = lo.getSize() - 1;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (a[mid].getColor().startsWith(color)) {
//                // Agrega el auto encontrado a la lista de resultados
//                result.add(a[mid]);
//                // Busca a la izquierda del punto medio
//                int temp = mid - 1;
//                while (temp >= 0 && (a[temp].getColor().startsWith(color))) {
//                    result.add(a[temp]);
//                    temp--;
//                }
//                // Busca a la derecha del punto medio
//                temp = mid + 1;
//                while (temp < a.length && (a[temp].getColor().startsWith(color))) {
//                    result.add(a[temp]);
//                    temp++;
//                }
//                break;
//            }
//            if (a[mid].getColor().compareTo(color) < 0) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return result;
//    }

//    public LinkedList<Auto> busquedaBinariaMarca(LinkedList<Auto> lista, String text, Marca marca) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int inicio = 0;
//        int fin = lo.getSize() - 1;
//        while (inicio <= fin) {
//            int medio = inicio + (fin - inicio) / 2;
//            if (a[medio].getId_marca().equals(marca.getId())) {
//                result.add(a[medio]);
//                break;
//            }
//            if (a[medio].getId_marca().compareTo(marca.getId()) < 0) {
//                inicio = medio + 1;
//            } else {
//                fin = medio - 1;
//            }
//        }
//        return result;
//    }
//    
//    public LinkedList<Auto> busquedaBinariaPrecio(LinkedList<Auto> lista, String text, Double precio) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int inicio = 0;
//        int fin = lo.getSize() - 1;
//        while (inicio <= fin) {
//            int medio = inicio + (fin - inicio) / 2;
//            if (a[medio].getPrecio().equals(precio)) {
//                result.add(a[medio]);
//                break;
//            }
//            if (a[medio].getPrecio().compareTo(precio) < 0) {
//                inicio = medio + 1;
//            } else {
//                fin = medio - 1;
//            }
//        }
//        return result;
//    }
//
//    public LinkedList<Auto> busquedaBinariaModelo(LinkedList<Auto> lista, String text, String modelo) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int inicio = 0;
//        int fin = lo.getSize() - 1;
//        while (inicio <= fin) {
//            int medio = inicio + (fin - inicio) / 2;
//            if (a[medio].getModelo().equals(modelo)) {
//                result.add(a[medio]);
//                break;
//            }
//            if (a[medio].getModelo().compareTo(modelo) < 0) {
//                inicio = medio + 1;
//            } else {
//                fin = medio - 1;
//            }
//        }
//        return result;
//    }
//    
//    public LinkedList<Auto> busquedaBinariaColor(LinkedList<Auto> lista, String text, String color) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int inicio = 0;
//        int fin = lo.getSize() - 1;
//        while (inicio <= fin) {
//            int medio = inicio + (fin - inicio) / 2;
//            if (a[medio].getColor().equals(color)) {
//                result.add(a[medio]);
//                break;
//            }
//            if (a[medio].getColor().compareTo(color) < 0) {
//                inicio = medio + 1;
//            } else {
//                fin = medio - 1;
//            }
//        }
//        return result;
//    }   
//    
//    public LinkedList<Auto> busquedaBinariaAnio(LinkedList<Auto> lista, String text, Integer anio) throws Exception {
//        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
//        Auto[] a = lo.toArray();
//        LinkedList<Auto> result = new LinkedList<>();
//        int inicio = 0;
//        int fin = lo.getSize() - 1;
//        while (inicio <= fin) {
//            int medio = inicio + (fin - inicio) / 2;
//            if (a[medio].getAnio().equals(anio)) {
//                result.add(a[medio]);
//                break;
//            }
//            if (a[medio].getAnio().compareTo(anio) < 0) {
//                inicio = medio + 1;
//            } else {
//                fin = medio - 1;
//            }
//        }
//        return result;
//    }       

    public LinkedList<Auto> busquedaBinaria(LinkedList<Auto> lista, String text, Comparable clave, String tipo) throws Exception {
        LinkedList<Auto> lo = this.ordenar(0, text, lista, "quicksort");
        Auto[] a = lo.toArray();
        LinkedList<Auto> result = new LinkedList<>();
        int inicio = 0;
        int fin = lo.getSize() - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Comparable valorActual;
            switch (tipo) {
                case "marca":
                    valorActual = a[medio].getId_marca();
                    break;
                case "precio":
                    valorActual = a[medio].getPrecio();
                    break;
                case "modelo":
                    valorActual = a[medio].getModelo();
                    break;
                case "color":
                    valorActual = a[medio].getColor();
                    break;
                case "anio":
                    valorActual = a[medio].getAnio();
                    break;
                default:
                    throw new Exception("Tipo de búsqueda no válido");
            }
            if (valorActual.equals(clave)) {
                result.add(a[medio]);
                break;
            }
            if (valorActual.compareTo(clave) < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return result;
    }

    
    
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
