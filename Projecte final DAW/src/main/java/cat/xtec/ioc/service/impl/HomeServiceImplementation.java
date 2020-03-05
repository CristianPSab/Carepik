/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.repository.HomeRepository;
import cat.xtec.ioc.service.HomeService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristianpuntisabates
 */
@Service
public class HomeServiceImplementation implements HomeService {

    @Autowired
    private HomeRepository homeRepository;

    @Override
    public Usuaris Llegir(int codi) {
        return homeRepository.Llegir(codi);
    }

    @Override
    public String getNomUsuariByCodi(String nom) {
        return homeRepository.getNomUsuariByCodi(nom);
    }

//    @Override
//    public String Logout(Usuaris us){
//        return homeRepository.Logout(us);
//    }

}
