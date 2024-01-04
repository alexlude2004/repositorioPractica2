package modelo;

/**
 *
 * @author alexg
 */
public class Materia {
    private Integer id;
    private String nombre;

    public Materia() {
    }

    public Materia(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Boolean comparar(Materia c, String field, Integer type) {
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

}
