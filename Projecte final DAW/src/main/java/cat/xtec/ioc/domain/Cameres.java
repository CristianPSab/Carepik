package cat.xtec.ioc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cameres")
public class Cameres implements Serializable {

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCoditipocamera() {
        return coditipocamera;
    }

    public void setCoditipocamera(Integer coditipocamera) {
        this.coditipocamera = coditipocamera;
    }

    public Integer getCodiusuari() {
        return codiusuari;
    }

    public void setCodiusuari(Integer codiusuari) {
        this.codiusuari = codiusuari;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi", unique = true, nullable = false)
    private Integer codi;
    
    @NotNull
    @Size(max = 200)
    @Column(name = "nom")
    private String nom;

    @NotNull
    @Column(name = "coditipocamera")
    private Integer coditipocamera;
    
    @NotNull
    @Column(name = "codiusuari")
    private Integer codiusuari;

    public Cameres(Integer codi, String nom, Integer coditipocamera, Integer codiusuari) {
        this.codi = codi;
        this.nom = nom;
        this.coditipocamera = coditipocamera;
        this.codiusuari = codiusuari;
    }

    public Cameres() {
        this.codi = 0;
        this.nom = "";
        this.coditipocamera = 0;
        this.codiusuari = 0;
    }
   

    

}

