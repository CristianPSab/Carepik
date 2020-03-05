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
@Table(name = "usuaristokens")
public class UsuarisToken implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi",updatable=false,nullable=false)
    private Integer codi;

    public UsuarisToken(Integer codi, String token, Integer codiusuari) {
        this.codi = codi;
        this.token = token;
        this.codiusuari = codiusuari;
    }
    
    public UsuarisToken(){
        
    }

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCodiusuari() {
        return codiusuari;
    }

    public void setCodiusuari(Integer codiusuari) {
        this.codiusuari = codiusuari;
    }
    
    @Column(name = "token",updatable=false,nullable=false)
    private String token;
    
    @Column(name = "codiusuari",updatable=false,nullable=false)
    private Integer codiusuari;
}
