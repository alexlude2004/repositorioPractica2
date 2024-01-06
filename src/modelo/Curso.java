package modelo;

/**
 *
 * @author Asus
 */
public class Curso {
    private Integer id;
    private Integer id_malla;
    private Integer ciclo;
    private String paralelo;

    public Curso() {
    }

    public Curso(Integer id, Integer id_malla, Integer ciclo, String paralelo) {
        this.id = id;
        this.id_malla = id_malla;
        this.ciclo = ciclo;
        this.paralelo = paralelo;
    }
    
    public Boolean comparar(Curso c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("id_malla")) {
                    return  getId_malla().intValue() > c.getId_malla().intValue();
                }
                else if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo().intValue() > c.getCiclo().intValue();
                }
                else if (field.equalsIgnoreCase("paralelo")) {
                    return getParalelo().compareTo(c.getParalelo()) > 0;
                }
                
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("id_malla")) {
                    return  getId_malla().intValue() < c.getId_malla().intValue();
                }
                else if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo().intValue() < c.getCiclo().intValue();
                }
                else if (field.equalsIgnoreCase("paralelo")) {
                    return getParalelo().compareTo(c.getParalelo()) < 0;
                }            
            default:
                return null;
        }
    }        

    @Override
    public String toString() {
        return ciclo.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_malla() {
        return id_malla;
    }

    public void setId_malla(Integer id_malla) {
        this.id_malla = id_malla;
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
