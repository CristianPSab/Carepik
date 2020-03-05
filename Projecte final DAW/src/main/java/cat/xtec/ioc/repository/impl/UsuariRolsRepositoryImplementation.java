package cat.xtec.ioc.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.domain.UsuarisRols;
import cat.xtec.ioc.domain.UsuarisToken;
import cat.xtec.ioc.repository.UsuariRolsRepository;
import org.hibernate.Session;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.*;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;

@Transactional
@Repository
public class UsuariRolsRepositoryImplementation implements UsuariRolsRepository {
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    private Criteria createEntityCriteria() {
        return getSession().createCriteria(UsuarisRols.class);
    }

    @Override
    public String Guardar(UsuarisRols ur) {
        int codiusuarirol = getCodiUsuariByEmail(ur.getEmail());
        if (codiusuarirol == 0){
            return "No existeix l'usuari.";
        }else{
            //comprovar si està donat d'alta:
            if((EstaDonatDeAlta(ur.getCodiusuari(), codiusuarirol) == true) && ur.getCodi()== 0){
                return "Ja està donat de alta aquest usuari";
            }
            
            ur.setCodiusuarirol(codiusuarirol);
            if (ur.getCodi() == 0){
                getSession().saveOrUpdate(ur); 
            }else{
              getSession().merge(ur);   
            }
            
            return "";
        }
        
    }

    @Override
    public String Eliminar(UsuarisRols ur) {
        getSession().delete(ur);
        return "";
    }

    @Override
    public UsuarisRols Llegir(int codi,int codiusuari) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codi", codi));
        criteria.add(Restrictions.eq("codiusuari", codiusuari));
        Object res = criteria.uniqueResult();
        if (res == null){
            throw new NoSuchElementException("No trobat");
        }
        
        UsuarisRols us = (UsuarisRols) res;
        us.setEmail(getEmailUsuariByCodi(us.getCodiusuarirol()));
        return us;
    }

    @Override
    public List<UsuarisRols> Llistar(int codiusuari) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codiusuari", codiusuari));
        
        List<UsuarisRols> ll = (List<UsuarisRols>) criteria.list();
        for(UsuarisRols u : ll){
            u.setEmail(getEmailUsuariByCodi(u.getCodiusuarirol()));
            u.setNomcomplet(getNomCompletUsuariByCodi(u.getCodiusuari()));
        }
        return ll;
    }
    
    @Override
    public List<UsuarisRols> LlistarRols(int codiusuari) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codiusuarirol", codiusuari));
        
        List<UsuarisRols> ll = (List<UsuarisRols>) criteria.list();
        for(UsuarisRols u : ll){
            u.setEmail(getEmailUsuariByCodi(u.getCodiusuarirol()));
            u.setNomcomplet(getNomCompletUsuariByCodi(u.getCodiusuari()));
        }
        return ll;
    }
    
    public int getCodiUsuariByEmail(String email){
        Query query = getSession().createQuery("select object(o) from Usuaris as o where o.email = :email");
        query.setParameter("email", email);
        List<Usuaris> result = (List<Usuaris>) query.getResultList();
        if(result.size() == 0){
            return 0;
        }else{
            return result.get(0).getCodi();
        }
    }
    
    public String getEmailUsuariByCodi(int codi){
        Query query = getSession().createQuery("select object(o) from Usuaris as o where o.codi = :codi");
        query.setParameter("codi", codi);
        List<Usuaris> result = (List<Usuaris>) query.getResultList();
        if(result.size() == 0){
            return "";
        }else{
            return result.get(0).getEmail();
        }
    }
    
    public String getNomCompletUsuariByCodi(int codi){
        Query query = getSession().createQuery("select object(o) from Usuaris as o where o.codi = :codi");
        query.setParameter("codi", codi);
        List<Usuaris> result = (List<Usuaris>) query.getResultList();
        if(result.size() == 0){
            return "";
        }else{
            return result.get(0).getNom() + " " + result.get(0).getCognoms();
        }
    }
    
    public Boolean EstaDonatDeAlta(int codiusuari, int codiusuarirol){
        Query query = getSession().createQuery("select object(o) from UsuarisRols as o where o.codiusuarirol = :codiusuarirol and o.codiusuari= :codiusuari");
        query.setParameter("codiusuarirol", codiusuarirol);
        query.setParameter("codiusuari", codiusuari);
        List<UsuarisRols> result = (List<UsuarisRols>) query.getResultList();
        if(result.size() == 0){
            return false;
        }else{
            return true;
        }
    }
    
}
