
package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Cameres;
import cat.xtec.ioc.service.CameraService;
import cat.xtec.ioc.service.UsuariService;
import java.io.IOException;
import java.util.List;
import java.util.Random;
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
public class CameresController {
    @Autowired
    private CameraService cameraService;
    
    @Autowired
    private UsuariService usuariService;
    
    @RequestMapping(value = "/cameres", method = RequestMethod.GET)
    public ModelAndView Cameres(@CookieValue("token") String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("cameres");
        int codi = usuariService.getCodiByToken(token);
        List<Cameres> ll = cameraService.Llistar(codi);
        modelview.getModelMap().addAttribute("cameres", ll);
        return modelview;
    }
    
    @RequestMapping(value = "/camera", method = RequestMethod.GET)
    public ModelAndView LlegirCamera(@CookieValue("token") String token, @RequestParam("codi") int codi,@RequestParam("error") String error, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("camera");
        int codiusuari = usuariService.getCodiByToken(token);
       
        if(codi == 0){
            modelview.getModelMap().addAttribute("camera", new Cameres());
        }else{
            Cameres cm = cameraService.Llegir(codi, codiusuari);
            modelview.getModelMap().addAttribute("camera", cm); 
        }
        modelview.getModelMap().addAttribute("error",error);
        return modelview;
    }
    
    @RequestMapping(value = "/camera", method = RequestMethod.POST)
    public Object GuardarCamera(@CookieValue("token") String token, @ModelAttribute("camera") Cameres camera,RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //GUARDAR CAMERA
        
        camera.setCodiusuari(usuariService.getCodiByToken(token));
        String res = cameraService.Guardar(camera);
        if (res == ""){
            //Tot be ->redirigir a llista
            return "redirect:/perfilUsuari";
        }else{            
            redirectAttributes.addAttribute("codi", camera.getCodi());
            redirectAttributes.addAttribute("error", "true");
            String red = "redirect:/camera";
            return red;
        }
    }
    @RequestMapping(value = "/delcamera", method = RequestMethod.GET)
    
    public Object EliminarCamera(@CookieValue("token") String token, @RequestParam("codi") int codi,RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int codiusuari = usuariService.getCodiByToken(token);
            Cameres camera = cameraService.Llegir(codi, codiusuari);
            String res = cameraService.Eliminar(camera);
            return "redirect:/perfilUsuari";
    }
   
}
