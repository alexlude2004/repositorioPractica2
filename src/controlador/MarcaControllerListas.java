
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
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
