package modelo;

/**
 *
 * @author Asus
 */
public class Cuenta {
    private String correo;
    private String clave;
    private Boolean estado;

    public Cuenta() {
    }

    public Cuenta(String correo, String clave, Boolean estado) {
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
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
