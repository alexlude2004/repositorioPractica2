
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Marca;

/**
 *
 * @author alexg
 */
public class MarcaControllerListas extends DataAccessObject<Marca> {
    private LinkedList<Marca> marcas = new LinkedList<>();
    private Marca marca;
    
    public MarcaControllerListas() {
        super(Marca.class);
    }

    public Marca getMarca() {
        if (marca == null) 
            marca = new Marca();
        return marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public LinkedList<Marca> getMarcas() {
        if (marcas.isEmpty())
            marcas = this.listAll();
        return marcas;
    }

    /**
     * @param marcas the marcas to set
     */
    public void setMarcas(LinkedList<Marca> marcas) {
        this.marcas = marcas;
    }
    
    public Boolean save() {
        this.marca.setId(generar_id());
        return this.save(marca);
    }
    
    public LinkedList<Marca> ordenar(Integer type, String field, LinkedList<Marca> lista, String algoritmo) throws Exception {
        getMarcas();  
        Integer n = lista.getSize();    
        Marca[] m = lista.toArray();   
        Field faux = Utilidades.getField(Marca.class, field);    
        if (faux != null) {    
            if (algoritmo.equalsIgnoreCase("quickSort")) {
                quickSort(m, 0, n - 1, type, field);    
            } else if (algoritmo.equalsIgnoreCase("mergeSort")) {
                mergeSort(m, 0, n - 1, type, field); 
            } else {
                throw new Exception("El algoritmo de ordenamiento especificado no es válido");      
            }
            lista = lista.toList(m);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }    
    
    // Metodo de Ordenamiento: MERGE SORT
    
    private void mergeSort(Marca[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int m = 0;
        if (inicio < fin) {    
            m = (inicio + fin) / 2;     
            mergeSort(arreglo, inicio, m, type, field);      
            mergeSort(arreglo, m + 1, fin, type, field);     
            merge(arreglo, inicio, m, fin, type, field);       
        }
    }

    private void merge(Marca[] arreglo, int inicio, int m, int fin, Integer type, String field) throws Exception {
        int k = 0;
        int i = inicio;
        int j = m + 1;
        int n = fin - inicio + 1;
        Marca[] b = new Marca[n];
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

    public void quickSort(Marca[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Marca pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Marca aux = arreglo[i];
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

    public static void main(String[] args) {
        MarcaControllerListas mcl = new MarcaControllerListas();
        mcl.getMarca().setId(1);
        mcl.getMarca().setNombre("Honda");
        mcl.getMarca().setEstado(true);
        mcl.save();
        
        mcl.getMarca().setId(2);
        mcl.getMarca().setNombre("Toyota");
        mcl.getMarca().setEstado(true);
        mcl.save();
        
        mcl.getMarca().setId(3);
        mcl.getMarca().setNombre("Chevrolet");
        mcl.getMarca().setEstado(true);
        mcl.save();
        
        mcl.getMarca().setId(4);
        mcl.getMarca().setNombre("Ford");
        mcl.getMarca().setEstado(true);
        mcl.save(); 
        
        mcl.getMarca().setId(5);
        mcl.getMarca().setNombre("Hyundai");
        mcl.getMarca().setEstado(true);
        mcl.save();        
        
        mcl.getMarca().setId(6);
        mcl.getMarca().setNombre("Kia");
        mcl.getMarca().setEstado(true);
        mcl.save();     
        
        mcl.getMarca().setId(7);
        mcl.getMarca().setNombre("Mercedes");
        mcl.getMarca().setEstado(true);
        mcl.save();      
        
        mcl.getMarca().setId(8);
        mcl.getMarca().setNombre("Nissan");
        mcl.getMarca().setEstado(true);
        mcl.save();
    }
    
}
