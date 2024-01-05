package modelo;

/**
 *
 * @author alexg
 */
public class Materia {
    private Integer id;
    private Integer id_curso;
    private String nombre;
    
    public Materia() {
    }

    public Materia(Integer id, Integer id_curso, String nombre) {
        this.id = id;
        this.id_curso = id_curso;
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
                else if (field.equalsIgnoreCase("id_curso")) {
                    return getId_curso().intValue() > c.getId_curso().intValue();
                }
                
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) < 0;
                }
                else if (field.equalsIgnoreCase("id_curso")) {
                    return getId_curso().intValue() < c.getId_curso().intValue();
                }                
            default:
                return null;
        }
    }       

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
          
}
