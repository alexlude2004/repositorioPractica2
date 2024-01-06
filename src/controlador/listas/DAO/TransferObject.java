
package controlador.listas.DAO;

import controlador.TDA.listas.LinkedList;

/**
 *
 * @author Asus
 */
public interface TransferObject <T>{
    public Boolean save(T data);
    public Boolean update(T data, Integer index);
    public LinkedList<T> listAll();
    public T find(Integer id);
    
}
