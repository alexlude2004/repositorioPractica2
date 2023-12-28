
package modelo;

/**
 *
 * @author alexg
 */
public class Auto {
    private Integer id;
    private String color;
    private Double precio;
    private Integer anio;
    private String modelo;
    private Integer id_marca;

    public Auto(Integer id, String color, Double precio, Integer anio, String modelo, Integer id_marca) {
        this.id = id;
        this.color = color;
        this.precio = precio;
        this.anio = anio;
        this.modelo = modelo;
        this.id_marca = id_marca;
    }

    public Auto() {
        
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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the id_marca
     */
    public Integer getId_marca() {
        return id_marca;
    }

    /**
     * @param id_marca the id_marca to set
     */
    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }
    
    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public Boolean comparar(Auto c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("color")) {
                    return  getColor().compareTo(c.getColor()) > 0;
                }
                else if (field.equalsIgnoreCase("precio")) {
                    return getPrecio().doubleValue() > c.getPrecio().doubleValue();
                }
                else if (field.equalsIgnoreCase("anio")) {
                    return getAnio().intValue() > c.getAnio().intValue();
                }
                else if (field.equalsIgnoreCase("modelo")) {
                    return getModelo().compareTo(c.getModelo()) > 0;
                }
                else if (field.equalsIgnoreCase("id_marca")) {
                    return getId_marca().intValue() > c.getId_marca().intValue();
                }

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("color")) {
                    return  getColor().compareTo(c.getColor()) < 0;
                }
                else if (field.equalsIgnoreCase("precio")) {
                    return getPrecio().doubleValue() < c.getPrecio().doubleValue();
                }
                else if (field.equalsIgnoreCase("anio")) {
                    return getAnio().intValue() < c.getAnio().intValue();
                }
                else if (field.equalsIgnoreCase("modelo")) {
                    return getModelo().compareTo(c.getModelo()) < 0;
                }
                else if (field.equalsIgnoreCase("id_marca")) {
                    return getId_marca().intValue() < c.getId_marca().intValue();
                }
            default:
                return null;
        }
    }    
    
    @Override
    public String toString() {
        return modelo;
    }
    
}
