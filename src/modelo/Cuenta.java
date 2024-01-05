package modelo;

/**
 *
 * @author Asus
 */
public class Cuenta {
    private Integer id;
    private String correo;
    private String clave;
    private Boolean estado;

    public Cuenta() {
    }

    public Cuenta(Integer id, String correo, String clave, Boolean estado) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        //Comentario en rama Bayron
        System.out.println("Cosas por borrar");
        System.out.println("Cosas por borrar 2");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
     
}
