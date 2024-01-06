
package controlador.pis.dao;

import com.thoughtworks.xstream.XStream;
import controlador.TDA.listas.LinkedList;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 *
 * @author alexg
 */
public class DataAccessObject <T> implements TransferObjects<T>{
    
    private XStream xstream;
    private Class clazz;
    private String URL;
    
    public DataAccessObject(Class<T> clazz) {
        xstream = Connection.getXstream();
        this.clazz = clazz;
        URL = Connection.getURL() + this.clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean save(T data) {
        LinkedList<T> list = listAll();
        list.add(data);
        try {
            this.xstream.toXML(list, new FileOutputStream(URL));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(T data, Integer index) {
        LinkedList<T> list = listAll();
            try {
                list.update(data, index);
                this.xstream.alias(list.getClass().getName(), LinkedList.class);
                this.xstream.toXML(list, new FileOutputStream(URL));
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }  
    }

    @Override
    public LinkedList<T> listAll() {
        //guardar una ListaEnlazada
        LinkedList<T> list = new LinkedList<>();
        try {
            list = (LinkedList<T>)xstream.fromXML(new FileReader(URL));
        } catch (Exception e) {
            System.out.println("LISTAR: " + e.getMessage());
        }
        return list;
    }
    
    public Integer generar_id() {
        return listAll().getSize() + 1;
    }
}
