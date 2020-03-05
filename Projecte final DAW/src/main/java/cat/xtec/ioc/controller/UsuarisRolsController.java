package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.domain.UsuarisRols;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UsuarisRolsController {
    @Autowired
    private UsuariRolsService usuariRolsService;
    
    @Autowired
    private UsuariService usuariService;
    
   
    @RequestMapping(value = "/rols", method = RequestMethod.GET)
    public ModelAndView Rols(@CookieValue("token") String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("usuarisrols");
        int codi = usuariService.getCodiByToken(token);
        List<UsuarisRols> ll = usuariRolsService.Llistar(codi);
        modelview.getModelMap().addAttribute("rols", ll);
        return modelview;
    }
    
    @RequestMapping(value = "/rol", method = RequestMethod.GET)
    public ModelAndView LlegirRol(@CookieValue("token") String token, @RequestParam("codi") int codi,@RequestParam("error") String error, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("usuarirol");
        int codiusuari = usuariService.getCodiByToken(token);
       
        if(codi == 0){
            modelview.getModelMap().addAttribute("rol", new UsuarisRols());
        }else{
            UsuarisRols us = usuariRolsService.Llegir(codi, codiusuari);
            if(us.getAdmin() == true){
                us.setEnumRol(0);
            }
            if(us.getGestor()== true){
                us.setEnumRol(1);
            }
            if(us.getLector()== true){
                us.setEnumRol(2);
            }
            modelview.getModelMap().addAttribute("rol", us); 
        }
        modelview.getModelMap().addAttribute("error",error);
        return modelview;
    }
    
    @RequestMapping(value = "/rol", method = RequestMethod.POST)
    public Object GuardarRol(@CookieValue("token") String token, @ModelAttribute("rol") UsuarisRols rol,RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (rol.getCodi() == null){
            return "no ha funcionat";
        }
        
        //GUARDAR USUARI
        if (rol.getEnumRol() == 0){
            rol.setAdmin(true);
            rol.setGestor(false);
            rol.setLector(false);
        }
        if (rol.getEnumRol() == 1){
            rol.setAdmin(false);
            rol.setGestor(true);
            rol.setLector(false);
        }
        if (rol.getEnumRol() == 2){
            rol.setAdmin(false);
            rol.setGestor(false);
            rol.setLector(true);
        }
        
        rol.setCodiusuari(usuariService.getCodiByToken(token));
        String res = usuariRolsService.Guardar(rol);
        if (res == ""){
            //Tot be ->redirigir a llista
            return "redirect:/perfilUsuari";
        }else{
            //Email introduit no existeix.
            if(rol.getCodi() == null){
               redirectAttributes.addAttribute("codi", 0); 
            }else{
               redirectAttributes.addAttribute("codi", rol.getCodi());
            }
            
            redirectAttributes.addAttribute("error", "true");
            String red = "redirect:/rol";
            return red;
        }
    }
    @RequestMapping(value = "/delrol", method = RequestMethod.GET)
    
    public Object EliminarRol(@CookieValue("token") String token, @RequestParam("codi") int codi,RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int codiusuari = usuariService.getCodiByToken(token);
            UsuarisRols rol = usuariRolsService.Llegir(codi, codiusuari);
            String res = usuariRolsService.Eliminar(rol);
            return "redirect:/perfilUsuari";
    }
    
   
}
