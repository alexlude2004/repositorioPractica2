
package controlador;

import controlador.TDA.listas.LinkedList;
import controlador.autos.dao.DataAccessObject;
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
       
}
