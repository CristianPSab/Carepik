/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.repository.UsuariRepository;
import cat.xtec.ioc.service.UsuariService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariServiceImplementation implements UsuariService{
    @Autowired
    private UsuariRepository usuariRepository;
    
    


    @Override
    public String Login(Usuaris us) {        
        return usuariRepository.Login(us);
    }
    
    @Override
    public String Registre(Usuaris us){
        return usuariRepository.Registre(us);
    }
    
    @Override
    public int getCodiByToken(String token){
        return usuariRepository.getCodiByToken(token);
    }

    @Override
    public Usuaris GetUsuari(int codi) {
        return usuariRepository.GetUsuari(codi);
    }
}

