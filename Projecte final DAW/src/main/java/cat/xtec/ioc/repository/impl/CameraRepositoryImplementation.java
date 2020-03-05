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
import cat.xtec.ioc.domain.Cameres;
import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.repository.CameraRepository;
import org.hibernate.Session;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.*;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


@Transactional
@Repository
public class CameraRepositoryImplementation implements CameraRepository{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public String Guardar(Cameres cm) {
        if (cm.getCodi() == 0){
            Usuaris us = getUsuariByCodi(cm.getCodiusuari());
            //Crear carpeta en FTP
            crearDirectoriFTP((us.getNom() + us.getCognoms()).replaceAll(" ", "") + "/"+ cm.getCoditipocamera() + cm.getNom());
            getSession().save(cm); 
            
        }else{
          getSession().merge(cm);   
        }

        return "";
        
    }

    @Override
    public String Eliminar(Cameres cm) {
        Usuaris us = getUsuariByCodi(cm.getCodiusuari());
        //Eliminar carpeta FTP
        EliminarDirectoriFTP((us.getNom() + us.getCognoms()).replaceAll(" ", "") + "/"+ cm.getCoditipocamera() + cm.getNom());
        getSession().delete(cm);
        return "";
    }

    @Override
    public Cameres Llegir(int codi,int codiusuari) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codi", codi));
        criteria.add(Restrictions.eq("codiusuari", codiusuari));
        Object res = criteria.uniqueResult();
        if (res == null){
            throw new NoSuchElementException("No trobat");
        }
        
        Cameres cm = (Cameres) res;
        return cm;
    }

    @Override
    public List<Cameres> Llistar(int codiusuari) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codiusuari", codiusuari));
        
        List<Cameres> ll = (List<Cameres>) criteria.list();
        return ll;
    }
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Cameres.class);
    }
    
    private String crearDirectoriFTP(String nom){
        String server = "localhost";
        int port = 21;
        String user = "admin";
        String pass = "1234";
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                return "Error de connexió";
            }
            //login
            boolean success = ftpClient.login(user, pass);
            if (!success) {
                return "Error en el login";
            }
            // Crear directori
            String dirToCreate = "/" + nom;
            success = ftpClient.makeDirectory(dirToCreate);
            if (success) {
            } else {
                return "Error al crear directori";
            }
            // tancar sessió
            ftpClient.logout();
            ftpClient.disconnect();
            return "";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
    
    private String EliminarDirectoriFTP(String nom){
        String server = "localhost";
        int port = 21;
        String user = "admin";
        String pass = "1234";
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                return "Error de connexió";
            }
            //login
            boolean success = ftpClient.login(user, pass);
            if (!success) {
                return "Error en el login";
            }
            // Eliminar directori
            String dirToRemove = "/" + nom;
            success = ftpClient.removeDirectory(dirToRemove);
            if (success) {
            } else {
                return "Error al crear directori";
            }
            // tancar sessió
            ftpClient.logout();
            ftpClient.disconnect();
            return "";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
    
    public Usuaris getUsuariByCodi(int codi){
        Query query = getSession().createQuery("select object(o) from Usuaris as o where o.codi = :codi");
        query.setParameter("codi", codi);
        List<Usuaris> result = (List<Usuaris>) query.getResultList();
        if(result.size() == 0){
            return new Usuaris();
        }else{
            return result.get(0);
        }
    }
   
}
