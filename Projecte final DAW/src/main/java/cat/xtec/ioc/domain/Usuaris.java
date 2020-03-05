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
@Table(name = "usuaris")
public class Usuaris implements Serializable {

    public Usuaris(Integer codi, String nom, String cognoms, String email, String contrasenya) {
        this.codi = codi;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.contrasenya = contrasenya;
    }
     public Usuaris() {
     }

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

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi",updatable=false,nullable=false)
    private Integer codi;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "nom")    
    private String nom;
    
    @NotNull
    @Size(max = 150)
    @Column(name = "cognoms")
    private String cognoms;
    
    @NotNull
    @Size(max = 150)
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Size(max = 150)
    @Column(name = "contrasenya")
    private String contrasenya;

}

