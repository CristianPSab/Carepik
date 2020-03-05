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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private UsuariService usuariService;

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("login");
        int codi = usuariService.getCodiByToken(token);

        modelview.getModelMap().addAttribute("index", codi);
        return modelview;
    }
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object checklogin(@CookieValue(value = "token", required = false) String token, @RequestParam("email") String email, @RequestParam("contrasenya") String contrasenya, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuaris us = new Usuaris(0, "", "", email, contrasenya);
        String res = usuariService.Login(us);
        if (res == "") {
            ModelAndView modelview = new ModelAndView("login");
            modelview.getModelMap().addAttribute("error", "true");
            int codi = usuariService.getCodiByToken(token);

            modelview.getModelMap().addAttribute("index", codi);
            return modelview;
        } else {
            response.addCookie(new Cookie("token", res));
            return "redirect:/";
        }

    }
    
    


}
