package cat.xtec.ioc.controller;

import cat.xtec.ioc.service.UsuariService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FooterController {

    @Autowired
    private UsuariService usuariService;

    @RequestMapping(value = "/footer", method = RequestMethod.GET)
    public ModelAndView handleRequest(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codiusuari = usuariService.getCodiByToken(token);

        ModelAndView modelview = new ModelAndView("footer");
        modelview.getModelMap().addAttribute("index", 0);

        return modelview;
    }

    @RequestMapping(value = "/politicacookies", method = RequestMethod.GET)
    public ModelAndView politicaCookies(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codiusuari = usuariService.getCodiByToken(token);

        ModelAndView modelview = new ModelAndView("politica-de-cookies");
        modelview.getModelMap().addAttribute("index", 0);

        return modelview;
    }

    @RequestMapping(value = "/politicaprivacitat", method = RequestMethod.GET)
    public ModelAndView politicaPrivacitat(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codiusuari = usuariService.getCodiByToken(token);

        ModelAndView modelview = new ModelAndView("politica-de-privacitat");
        modelview.getModelMap().addAttribute("index", 0);

        return modelview;
    }

    @RequestMapping(value = "/grup2", method = RequestMethod.GET)
    public ModelAndView grup2(@CookieValue(value = "token", required = false) String token, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codiusuari = usuariService.getCodiByToken(token);

        ModelAndView modelview = new ModelAndView("grup2");
        modelview.getModelMap().addAttribute("index", 0);

        return modelview;
    }
}
