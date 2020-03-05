/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Usuaris;
import java.util.List;

/**
 *
 * @author cristianpuntisabates
 */
public interface HomeService {
    public Usuaris Llegir(int codi);
    public String getNomUsuariByCodi(String nom);
//    public String Logout(Usuaris us);



}
