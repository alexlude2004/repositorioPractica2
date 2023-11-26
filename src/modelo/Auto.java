
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
    
    @Override
    public String toString() {
        return " Modelo: " + modelo + " ----- Color: " +  color + " ----- Anio: " + anio.toString() + " ----- Precio: $" + precio.toString();
    }
    
}
