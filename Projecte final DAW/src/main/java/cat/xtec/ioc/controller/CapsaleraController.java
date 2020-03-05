package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Usuaris;

import java.io.IOException;
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

@Controller
@RequestMapping("/")

public class CapsaleraController {
    
    
    @RequestMapping(value = "/capsalera", method = RequestMethod.GET)
    public ModelAndView handleRequest(  HttpServletRequest request)
            throws ServletException, IOException {



//        ModelAndView modelview = new ModelAndView("capsalera");
         ModelAndView modelview = new ModelAndView("redirect:/");
     
//        session.setAttribute("email", u.getEmail());

        return modelview;
    }
    

}
