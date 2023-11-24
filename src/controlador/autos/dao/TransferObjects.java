
package controlador.autos.dao;

import controlador.TDA.listas.LinkedList;

/**
 *
 * @author alexg
 */
public interface TransferObjects <T> {
    public Boolean save(T data);
    public Boolean update(T data, Integer index);
    public LinkedList<T> listAll();
    
}
