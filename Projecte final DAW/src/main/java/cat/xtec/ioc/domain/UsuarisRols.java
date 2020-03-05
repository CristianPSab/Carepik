package cat.xtec.ioc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "usuarisrols")
public class UsuarisRols implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi",updatable=true,nullable=false)
    private Integer codi;
    
    @NotNull
    @Column(name = "admin")    
    private Boolean admin;
    
    @NotNull
    @Column(name = "gestor")    
    private Boolean gestor;
    
    @NotNull
    @Column(name = "lector")    
    private Boolean lector;
    
    @NotNull
    @Column(name = "codiusuari")    
    private Integer codiusuari;
    
    @NotNull
    @Column(name = "codiusuarirol")    
    private Integer codiusuarirol;
    
    @Transient
    private String email;

    @Transient
    private String nomcomplet;
    
    @Transient
    private int enumRol;

    public int getEnumRol() {
        return enumRol;
    }

    public void setEnumRol(int enumRol) {
        this.enumRol = enumRol;
    }

    public String getEmail() {
        return email;
    }
    
    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuarisRols(Integer codi, Boolean admin, Boolean gestor, Boolean lector, Integer codiusuari, Integer codiusuarirol,String email,String nomcomplet) {
        this.codi = codi;
        this.admin = admin;
        this.gestor = gestor;
        this.lector = lector;
        this.codiusuari = codiusuari;
        this.codiusuarirol = codiusuarirol;
        this.email = email;
        this.enumRol = 0;
        this.nomcomplet = "";
    }
    
    public UsuarisRols(){
        this.admin = true;
        this.gestor = false;
        this.lector = false;
        this.enumRol = 0;
        this.codi = 0;
    }

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getGestor() {
        return gestor;
    }

    public void setGestor(Boolean gestor) {
        this.gestor = gestor;
    }

    public Boolean getLector() {
        return lector;
    }

    public void setLector(Boolean lector) {
        this.lector = lector;
    }

    public Integer getCodiusuari() {
        return codiusuari;
    }

    public void setCodiusuari(Integer codiusuari) {
        this.codiusuari = codiusuari;
    }

    public Integer getCodiusuarirol() {
        return codiusuarirol;
    }

    public void setCodiusuarirol(Integer codiusuarirol) {
        this.codiusuarirol = codiusuarirol;
    }
    
    

}