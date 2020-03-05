package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.service.UsuariService;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarisController {
    @Autowired
    private UsuariService usuariService;
    
    @RequestMapping(value = "/registre", method = RequestMethod.GET)
    public ModelAndView Registre(@CookieValue(value="token",required=false) String token,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("registre");
        try{
            int codiusuari = usuariService.getCodiByToken(token);
            Usuaris formRegistre = new Usuaris(0,"","","","");
            modelview.getModelMap().addAttribute("formRegistre", formRegistre);
            modelview.getModelMap().addAttribute("index",  0);

            return modelview;
        }catch(Exception ex){
            Usuaris formRegistre = new Usuaris(0,"","","","");
            modelview.getModelMap().addAttribute("formRegistre", formRegistre);
            return modelview;
        }
       
    }
    
    @RequestMapping(value = "/registre", method = RequestMethod.POST)
    public Object Registre(@ModelAttribute("formRegistre") Usuaris nouUsuari,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //GUARDAR USUARI
        String pss = nouUsuari.getContrasenya();
        String res = usuariService.Registre(nouUsuari);
        if (res == ""){
            nouUsuari.setContrasenya(pss);
            String res2 = usuariService.Login(nouUsuari);
            
            response.addCookie(new Cookie("token", res2));
            return "redirect:/cameres";
        }else{
            //Email repetit
            ModelAndView modelview = new ModelAndView("registre");
            Usuaris formRegistre = new Usuaris(0,res,"","","");
            modelview.getModelMap().addAttribute("formRegistre", formRegistre);
            modelview.getModelMap().addAttribute("error", "true");
            modelview.getModelMap().addAttribute("index",  0);

            return modelview;
        } 
    }
       
}
