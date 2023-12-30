package modelo;

/**
 *
 * @author Asus
 */
public class Curso {
    private String codigo;
    private Integer ciclo;
    private String paralelo;

    public Curso() {
    }

    public Curso(String codigo, Integer ciclo, String paralelo) {
        this.codigo = codigo;
        this.ciclo = ciclo;
        this.paralelo = paralelo;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }
    
    
}
