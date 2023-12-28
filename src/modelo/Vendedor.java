
package modelo;

/**
 *
 * @author alexg
 */
public class Vendedor {
    private Integer id;
    private String dni;
    private String ruc;
    private String apellidos;
    private String nombres;
    private String direccion;
    private String telefono;
    private String correo;

    public Vendedor(Integer id, String dni, String ruc, String apellidos, String nombres, String direccion, String telefono, String correo) {
        this.id = id;
        this.dni = dni;
        this.ruc = ruc;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Vendedor() {
        
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
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    @Override
    public String toString() {
        return nombres + " " + apellidos;
    }

    public Boolean comparar(Vendedor c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("dni")) {
                    return  getDni().compareTo(c.getDni()) > 0;
                }
                else if (field.equalsIgnoreCase("ruc")) {
                    return getRuc().compareTo(c.getRuc()) > 0;
                }
                else if (field.equalsIgnoreCase("apellidos")) {
                    return getApellidos().compareTo(c.getApellidos()) > 0;
                }
                else if (field.equalsIgnoreCase("nombres")) {
                    return getNombres().compareTo(c.getNombres()) > 0;
                }
                else if (field.equalsIgnoreCase("direccion")) {
                    return getDireccion().compareTo(c.getDireccion()) > 0;
                }
                else if (field.equalsIgnoreCase("telefono")) {
                    return getTelefono().compareTo(c.getTelefono()) > 0;
                }
                else if (field.equalsIgnoreCase("correo")) {
                    return getCorreo().compareTo(c.getCorreo()) > 0;
                }                

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("dni")) {
                    return  getDni().compareTo(c.getDni()) < 0;
                }
                else if (field.equalsIgnoreCase("ruc")) {
                    return getRuc().compareTo(c.getRuc()) < 0;
                }
                else if (field.equalsIgnoreCase("apellidos")) {
                    return getApellidos().compareTo(c.getApellidos()) < 0;
                }
                else if (field.equalsIgnoreCase("nombres")) {
                    return getNombres().compareTo(c.getNombres()) < 0;
                }
                else if (field.equalsIgnoreCase("direccion")) {
                    return getDireccion().compareTo(c.getDireccion()) < 0;
                }
                else if (field.equalsIgnoreCase("telefono")) {
                    return getTelefono().compareTo(c.getTelefono()) < 0;
                }
                else if (field.equalsIgnoreCase("correo")) {
                    return getCorreo().compareTo(c.getCorreo()) < 0;
                }   
            default:
                return null;
        }
    }      
    
}
