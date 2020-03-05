package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.service.UsuariService;
import cat.xtec.ioc.service.HomeService;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cristianpuntisabates
 */
@Controller
public class HomeController {

    @Autowired
    private UsuariService usuariService;
    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequest(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("index");

        modelview.getModelMap().addAttribute("benvinguda2", "Benvingut ");
        int codi = usuariService.getCodiByToken(token);
        if (codi == 0) {
            modelview.getModelMap().addAttribute("index", new Usuaris());

        } else {
            Usuaris us = homeService.Llegir(codi);
            modelview.getModelMap().addAttribute("index", us);
            modelview.getModelMap().addAttribute("nom", us.getNom());
            modelview.getModelMap().addAttribute("cognom", us.getCognoms());

        }
//            

        modelview.getModelMap().addAttribute("index", codi);

        Random rn = new Random();
        int imageCode = rn.nextInt(5) - 1;
        if (imageCode <= 0) {
            imageCode++;

        }

        modelview.getModelMap().addAttribute("imageCode", imageCode);

        modelview.getModelMap().addAttribute("benvinguda", "<h1 class='text-md-center' style='color:blue'>Benvinguts a CarePik!</h1>");
        return modelview;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        ModelAndView modelview = new ModelAndView("logout");

        int codi = usuariService.getCodiByToken(token);
        modelview.getModelMap().addAttribute("index", codi);

        if (session != null) {
            session.invalidate();//Antiga sessió invalidada
        }

        for (Cookie cookie : request.getCookies()) {
            if ("token".equals(cookie.getName())) {
                //Eliminar una cookie

                Cookie userNameCookieRemove = new Cookie(cookie.getName(), "");
                userNameCookieRemove.setMaxAge(0);
                response.addCookie(userNameCookieRemove);
            }
        }
        return "redirect:/";
    }

}
