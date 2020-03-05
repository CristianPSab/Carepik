/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import cat.xtec.ioc.repository.HomeRepository;
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
public class HomeRepositoryImplementation implements HomeRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
     protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Usuaris.class);
    }
    
  
    @Override
    public Usuaris Llegir(int codi) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codi", codi));
        Object us = criteria.uniqueResult();
        if (us == null){
            throw new NoSuchElementException("No trobat");
        }
        
        Usuaris usuari = (Usuaris) us;
//        usuari.setNom(getNomUsuariByCodi(usuari.getNom()));
        return usuari;
    }
    
   
    @Override
    public String getNomUsuariByCodi(String nom){
        Query query = getSession().createQuery("select object(o) from Usuaris as o where o.codi = :codi");
        query.setParameter("nom", nom);
        List<Usuaris> result = (List<Usuaris>) query.getResultList();
        if(result.isEmpty()){
            return "";
        }else{
            return result.get(0).getNom();
        }
    }
//
//   @Override
//    public String Logout(Usuaris u) {
//        //encriptar contrasenya
//        String contrasenya = "";
//        try {
//            contrasenya = sha1(u.getContrasenya());
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(UsuariRepositoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Criteria criteria = createEntityCriteria();
//        criteria.add(Restrictions.eq("email", u.getEmail()));
//        criteria.add(Restrictions.eq("contrasenya", contrasenya));
//        Object us = criteria.uniqueResult();
//        if (us == null) {
//            return "";
//        } else {
//            Usuaris usuari = (Usuaris) us;
//
//            //eliminem tokens existents
//            UsuarisToken ud = new UsuarisToken();
//            Query query = getSession().createQuery("delete UsuarisToken where codiusuari = :codiusuari");
//            query.setParameter("codiusuari", usuari.getCodi());
//            int result = query.executeUpdate();
//
//       
//            return String.valueOf(result);
//        }
//    }
//    
//      public String sha1(String input) throws NoSuchAlgorithmException {
//        String sha1 = null;
//        try {
//            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
//            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
//            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
//        } catch (UnsupportedEncodingException e) {
//            return "Error";
//        }
//        return sha1;
//    }


    
  
   

}
