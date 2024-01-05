package modelo;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Tarea {
    private Integer id;
    private Byte archivo;
    private Date fechaEntrega;
    private String comentario;

    public Tarea(Integer id, Byte archivo, Date fechaEntrega, String comentario) {
        this.id = id;
        this.archivo = archivo;
        this.fechaEntrega = fechaEntrega;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getArchivo() {
        return archivo;
    }

    public void setArchivo(Byte archivo) {
        this.archivo = archivo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    } 

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
          
    
}
