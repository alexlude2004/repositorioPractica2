package modelo;

/**
 *
 * @author Asus
 */
public class Cursa {
   private Integer id_matricula;
   private Integer id_materia;

    public Cursa() {
    }

    public Cursa(Integer id_matricula, Integer id_materia) {
        this.id_matricula = id_matricula;
        this.id_materia = id_materia;
    }

    public Integer getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(Integer id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }
   
   
}
