
package modelo;

/**
 *
 * @author alexg
 */
public class Marca {
    private Integer id;
    private String nombre;
    private Boolean estado;

    public Marca(Integer id, String nombre, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Marca() {
        
    }    
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the estado
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
 
    @Override
    public String toString() {
        return nombre;
    }

    public Boolean comparar(Marca c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) > 0;
                }
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) < 0;
                }
            default:
                return null;
        }
    }   
    
}
