package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Cameres;
import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.domain.UsuarisRols;
import cat.xtec.ioc.service.CameraService;
import cat.xtec.ioc.service.HomeService;
import cat.xtec.ioc.service.UsuariRolsService;
import cat.xtec.ioc.service.UsuariService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerfilController {
    
    @Autowired
    private UsuariRolsService usuariRolsService;
    
    @Autowired
    private CameraService cameraService;
    
    @Autowired
    private UsuariService usuariService;
    
    @Autowired
    private HomeService homeService;
    
    @RequestMapping(value = "/perfilUsuari", method = RequestMethod.GET)
    public ModelAndView perfil(@CookieValue("token") String token,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("perfil");
        modelview.getModelMap().addAttribute("saluda", "Les teves dades!");
        int codi = usuariService.getCodiByToken(token);
        
        //PERFIL
        Usuaris us = homeService.Llegir(codi);
        modelview.getModelMap().addAttribute("formRegistre",us);
        
        //CONVIDATS
        List<UsuarisRols> ll = usuariRolsService.Llistar(codi);
        modelview.getModelMap().addAttribute("rols", ll);
        
        //CAMERES
        List<Cameres> llc = cameraService.Llistar(codi);
        modelview.getModelMap().addAttribute("cameres", llc);
        
        return modelview;
    }
    
    @RequestMapping(value = "/perfilUsuari", method = RequestMethod.POST)
    public String Registre(@ModelAttribute("formRegistre") Usuaris nouUsuari,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //GUARDAR USUARI
        String res = usuariService.Registre(nouUsuari);
        return "redirect:/perfilUsuari";
    }
    

}