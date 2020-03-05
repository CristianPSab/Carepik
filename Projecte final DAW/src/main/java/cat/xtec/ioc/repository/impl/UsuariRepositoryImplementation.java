package cat.xtec.ioc.repository.impl;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.domain.UsuarisToken;
import cat.xtec.ioc.repository.UsuariRepository;
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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;

@Transactional
@Repository
public class UsuariRepositoryImplementation implements UsuariRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Usuaris GetUsuari(int codi) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("codi", codi));
        Object res = criteria.uniqueResult();
        if (res == null) {
            throw new NoSuchElementException("No trobat");
        }

        Usuaris us = (Usuaris) res;
        return us;
    }

    @Override
    public String Login(Usuaris u) {
        //encriptar contrasenya
        String contrasenya = "";
        try {
            contrasenya = sha1(u.getContrasenya());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariRepositoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", u.getEmail()));
        criteria.add(Restrictions.eq("contrasenya", contrasenya));
        Object us = criteria.uniqueResult();
        if (us == null) {
            return "";
        } else {
            Usuaris usuari = (Usuaris) us;

            //eliminem tokens existents
            UsuarisToken ud = new UsuarisToken();
            Query query = getSession().createQuery("delete UsuarisToken where codiusuari = :codiusuari");
            query.setParameter("codiusuari", usuari.getCodi());
            int result = query.executeUpdate();

            //crearem una token
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder sb = new StringBuilder();
            Random rnd = new Random();
            while (sb.length() < 8) {
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                sb.append(SALTCHARS.charAt(index));
            }
            String rd = sb.toString();

            //Guardem la token
            UsuarisToken ut = new UsuarisToken();
            ut.setToken(rd);
            ut.setCodiusuari(usuari.getCodi());
            getSession().saveOrUpdate(ut);
            return rd;
        }
    }

    @Override
    public String Registre(Usuaris us) {
        if (us.getCodi() == null) {
            //comprobar que no existeix el email en BBDD
            int IdUsuari = getCodiUsuariByEmail(us.getEmail());
            String pss = us.getContrasenya();
            
            if (IdUsuari != 0) {
                //Existeix email
                return "Aquest email està donat de alta";
            }

            try {
                us.setContrasenya(sha1(us.getContrasenya()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuariRepositoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                String dir = us.getNom() + us.getCognoms().replaceAll(" ", "");
                crearDirectoriFTP(dir);
                AddUsuariFTP(us.getNom(), pss, dir);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UsuariRepositoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return "1:" + ex.getLocalizedMessage();
            } catch (TransformerException ex) {
                Logger.getLogger(UsuariRepositoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return "2:" + ex.getLocalizedMessage();
            }
            getSession().saveOrUpdate(us);
        } else {
            getSession().merge(us);
        }
         

        return "";
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Usuaris.class);
    }

    public String sha1(String input) throws NoSuchAlgorithmException {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException e) {
            return "Error";
        }
        return sha1;
    }

    @Override
    public int getCodiByToken(String token) {
        Query query = getSession().createQuery("select object(o) from UsuarisToken as o where o.token = :token");
        query.setParameter("token", token);
        List<UsuarisToken> result = (List<UsuarisToken>) query.getResultList();

        if (result.size() == 0) {
            return 0;
        } else {
            return result.get(0).getCodiusuari();
        }
    }

    public int getCodiUsuariByEmail(String email) {
        Query query = getSession().createQuery("select object(o) from Usuaris as o where o.email = :email");
        query.setParameter("email", email);
        List<Usuaris> result = (List<Usuaris>) query.getResultList();
        if (result.size() == 0) {
            return 0;
        } else {
            return result.get(0).getCodi();
        }
    }

    public void AddUsuariFTP(String usuari, String pass, String dir) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        /*Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        try {
            //agafar llista usuaris
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File("C:/Program Files (x86)/FileZilla Server/FileZilla Server.xml");
            dom = db.parse(file);

            Node users = dom.getElementsByTagName("Users").item(0);

            //Crear nou usuari
            Element user = dom.createElement("User");
            user.setAttribute("Name", dir);

            //crearem un SALT
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder sb = new StringBuilder();
            Random rnd = new Random();
            while (sb.length() < 64) {
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                sb.append(SALTCHARS.charAt(index));
            }
            String SALT = sb.toString();

            Element password = dom.createElement("Option");
            password.setAttribute("Name", "Pass");
            String sha512String = Sha512(pass + SALT).toUpperCase();
            password.setTextContent(sha512String);
            user.appendChild(password);

            Element salt = dom.createElement("Option");
            salt.setAttribute("Name", "Salt");
            salt.setTextContent(SALT);
            user.appendChild(salt);

            Element group = dom.createElement("Option");
            group.setAttribute("Name", "Group");
            user.appendChild(group);

            Element Bypass = dom.createElement("Option");
            Bypass.setAttribute("Name", "Bypass server userlimit");
            Bypass.setTextContent("0");
            user.appendChild(Bypass);

            Element UserLimit = dom.createElement("Option");
            UserLimit.setAttribute("Name", "User Limit");
            UserLimit.setTextContent("0");
            user.appendChild(UserLimit);

            Element IPLimit = dom.createElement("Option");
            IPLimit.setAttribute("Name", "IP Limit");
            IPLimit.setTextContent("0");
            user.appendChild(IPLimit);

            Element Enabled = dom.createElement("Option");
            Enabled.setAttribute("Name", "Enabled");
            Enabled.setTextContent("1");
            user.appendChild(Enabled);

            Element Comments = dom.createElement("Option");
            Comments.setAttribute("Name", "Comments");
            user.appendChild(Comments);

            Element ForceSsl = dom.createElement("Option");
            ForceSsl.setAttribute("Name", "ForceSsl");
            ForceSsl.setTextContent("0");
            user.appendChild(ForceSsl);

            Element IpFilter = dom.createElement("IpFilter");

            Element IpFilterDisallowed = dom.createElement("Disallowed");
            Element IpFilterAllowed = dom.createElement("Allowed");

            IpFilter.appendChild(IpFilterDisallowed);
            IpFilter.appendChild(IpFilterAllowed);
            user.appendChild(IpFilter);

            Element Permissions = dom.createElement("Permissions");

            Element Permission = dom.createElement("Permission");

            Permission.setAttribute("Dir", "C:\\carepik\\" + dir);

            Element FileRead = dom.createElement("Option");
            FileRead.setAttribute("Name", "FileRead");
            FileRead.setTextContent("1");
            Permission.appendChild(FileRead);
            Element FileWrite = dom.createElement("Option");
            FileWrite.setAttribute("Name", "FileWrite");
            FileWrite.setTextContent("1");
            Permission.appendChild(FileWrite);
            Element FileDelete = dom.createElement("Option");
            FileDelete.setAttribute("Name", "FileDelete");
            FileDelete.setTextContent("1");
            Permission.appendChild(FileDelete);
            Element FileAppend = dom.createElement("Option");
            FileAppend.setAttribute("Name", "FileAppend");
            FileAppend.setTextContent("1");
            Permission.appendChild(FileAppend);
            Element DirCreate = dom.createElement("Option");
            DirCreate.setAttribute("Name", "DirCreate");
            DirCreate.setTextContent("1");
            Permission.appendChild(DirCreate);
            Element DirDelete = dom.createElement("Option");
            DirDelete.setAttribute("Name", "DirDelete");
            DirDelete.setTextContent("1");
            Permission.appendChild(DirDelete);
            Element DirList = dom.createElement("Option");
            DirList.setAttribute("Name", "DirList");
            DirList.setTextContent("1");
            Permission.appendChild(DirList);
            Element DirSubdirs = dom.createElement("Option");
            DirSubdirs.setAttribute("Name", "DirSubdirs");
            DirSubdirs.setTextContent("1");
            Permission.appendChild(DirSubdirs);
            Element IsHome = dom.createElement("Option");
            IsHome.setAttribute("Name", "IsHome");
            IsHome.setTextContent("1");
            Permission.appendChild(IsHome);
            Element AutoCreate = dom.createElement("Option");
            AutoCreate.setAttribute("Name", "AutoCreate");
            AutoCreate.setTextContent("0");
            Permission.appendChild(AutoCreate);

            Permissions.appendChild(Permission);

            user.appendChild(Permissions);

            Element SpeedLimits = dom.createElement("SpeedLimits");
            SpeedLimits.setAttribute("DlType", "0");
            SpeedLimits.setAttribute("DlLimit", "10");
            SpeedLimits.setAttribute("ServerDlLimitBypass", "0");
            SpeedLimits.setAttribute("UlLimit", "10");
            SpeedLimits.setAttribute("UlType", "0");
            SpeedLimits.setAttribute("ServerUlLimitBypass", "0");

            Element Upload = dom.createElement("Upload");
            Element Download = dom.createElement("Download");

            SpeedLimits.appendChild(Upload);
            SpeedLimits.appendChild(Download);

            user.appendChild(SpeedLimits);

            //afegir a llista
            users.appendChild(user);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(file.getPath());
            Source input = new DOMSource(dom);

            transformer.transform(input, output);*/
        try {
            Runtime.getRuntime().exec("sh /home/jordi/crear_usuaris.sh " + dir + " " + pass);

        } catch (IOException ex) {

        }
    }

    public String Sha512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String crearDirectoriFTP(String nom) {
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

}
