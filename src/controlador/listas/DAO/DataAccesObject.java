package controlador.listas.DAO;
import com.thoughtworks.xstream.XStream;
import controlador.TDA.listas.LinkedList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 *
 * @author Asus
 */
public class DataAccesObject<T> implements TransferObject<T>{
    private XStream xstream;
    private Class clazz;//Se puede manipular los datos u objetos(no recuerdo) de la clase
    private String URL;

    public DataAccesObject(Class<T> clazz){
        xstream = Connection.getXstream();
        this.clazz = clazz;
        URL = Connection.getURL()+this.clazz.getSimpleName()+".json";
    }
            
    
    @Override
    public Boolean save(T data) {
        LinkedList<T> list = listAll();
        list.add(data);
        try {
            this.xstream.toXML(list, new FileOutputStream(URL));
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Error en save: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(T data, Integer index) {
        //LinkedList<LLanta> list = (LinkedList<LLanta>)listall();
        LinkedList<T> list = listAll();
        try {
            list.update(data, index);
            this.xstream.alias(list.getClass().getName(), LinkedList.class);
            this.xstream.toXML(list, new FileOutputStream(URL));
            return true;
//            lc.getLlanta().setId_marca(UtilVistaLista.getcomboMarcas(cbxmarca).getId());
//            list.get(index).setAro(data.);
//            lc.getLlanta().setAro(Integer.parseInt(txtaro.getText()));
//            lc.getLlanta().setCodigo(txtcodigo.getText());
//            lc.getLlanta().setDescripcion(txtdescripcion.getText());
//            lc.getLlanta().setPrecio(Double.parseDouble(txtprecio.getText()));
//            lc.getLlanta().setPresion(Double.parseDouble(txtpresion.getText()));
//            lc.getLlanta().setStock(Integer.parseInt(txtstock.getText()));
        } catch (Exception e) {
            System.out.println("Error en update: "+e.getMessage());
            return false;
        }
    }
    
    public Boolean borrar (Integer index){
        LinkedList<T> list = listAll();
        try{
            list.delete(index);
            this.xstream.alias(list.getClass().getName(), LinkedList.class);
            this.xstream.toXML(list, new FileOutputStream(URL));
            return true;
        }catch(Exception e){
            System.out.println("Error en eliminar: "+e.getMessage());
            return false;
        }
    }

    @Override
    public LinkedList<T> listAll() {
        //Recordar que se guarda una lista enlazada.
        LinkedList<T> list = new LinkedList<>();
        try {
            //Desde xstream, q es el driver, saco el dato
            list = (LinkedList<T>)xstream.fromXML(new FileReader(URL));
        } catch (Exception e) {
            System.out.println("Error en listall: "+e.getMessage());
        }
        return list;
    }

    @Override
    public T find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Integer generated_id(){
        return listAll().getSize() + 1;
    }
}
