/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CookieValue;

/**
 *
 * @author cristianpuntisabates
 */

@Controller
@RequestMapping("/")
public class ContactController {

    @Autowired
    private MailSender mailSender;
    
    @RequestMapping(value = "/sendEmail.do", method = RequestMethod.POST)
    public String doSendEmail(HttpServletRequest request)  throws ServletException, IOException { 
    
        
        try {
            // S'agafa l'input del form
            String nom = request.getParameter("nom");
            String recipientAddress = request.getParameter("email");
            String subject = request.getParameter("assumpte");
            String mobil = request.getParameter("mobil");
            String message = request.getParameter("missatge");

//      S'imprimeix la informació pel debug
            System.out.println("nom:" + nom);
            System.out.println("Per: " + recipientAddress);
            System.out.println("Assumpte: " + subject);
            System.out.print("Telèfon mòbil: " + mobil);
            System.out.println("Missatge: " + message);
//	Es crea un objecte simple de l'email.
            SimpleMailMessage email = new SimpleMailMessage();
            email.setText(nom);
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(mobil);
            email.setText(message);

//	S'envia l'email	
            mailSender.send(email);

            // Es reenvia a la vista anomenada "Result"
            return "redirect:/";
        } catch (MailException message) {
            System.out.println("Ho sentim, el teu email no s'ha pogut enviat per l'error següent:" + message);

            return "redirect:/";

        }

    }

}
