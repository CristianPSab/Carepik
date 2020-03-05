/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Cameres;
import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.domain.UsuarisRols;
import cat.xtec.ioc.service.HomeService;
import cat.xtec.ioc.service.UsuariService;
import cat.xtec.ioc.service.UsuariRolsService;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.objects.NativeString.substring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marina
 */
@Controller
public class MostrarImatgesController {

    @Autowired
    private UsuariService usuariService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private UsuariRolsService usuarirolsService;

    @RequestMapping(value = "/mostrar-imatges", method = RequestMethod.GET)
    public ModelAndView MostrarImatges(@CookieValue(value = "token", required = false) String token, @RequestParam("inici") String inici, @RequestParam("fi") String fi, @RequestParam("data") String data, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        ModelAndView modelview = new ModelAndView("mostrar-imatges");

        int codi = usuariService.getCodiByToken(token);
        if (inici != "") {
            inici = inici + ":00";
        }
        if (fi != "") {
            fi = fi + ":00";
        }

        //PERFIL
        Usuaris us = homeService.Llegir(codi);

        //CONVIDATS
        List<UsuarisRols> rols = usuarirolsService.LlistarRols(codi);
        

        //Jordi

        String ruta = "C:/Users/Jordi//Documents";
        //String ruta = "C:/Users/Jordi//Documents";
        
        //Tania

        //String ruta = "D:/SYNC/DAW/CURS/4at_semesrte/PROJECTE/GIT_carepik";

        //Marina
        //D:/Documentos/NetBeansProjects

        
        //Cristian
        //  /Users/cristianpuntisabates/NetBeansProjects/carepik/src/main/webapp/WEB-INF/resources/img/cam/


        //File folder = new File("D:/Documentos/NetBeansProjects/carepik/src/main/webapp/WEB-INF/resources/img/cam");
        List<File> listUsers = new ArrayList<File>();
        List<String> listNameUsers = new ArrayList<String>();
        List<String> listNomRols = new ArrayList<String>();

        File folder = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + us.getNom().replaceAll(" ", "") + us.getCognoms().replaceAll(" ", ""));

        listUsers.add(folder);
        listNameUsers.add(us.getNom().replaceAll(" ", "") + us.getCognoms().replaceAll(" ", ""));
        listNomRols.add("");

        //File[] listofFolders = folder.listFiles();
        //ArrayList alRev = new ArrayList<String>();
        DateFormat dia = new SimpleDateFormat("dd");
        DateFormat mes = new SimpleDateFormat("MM");
        DateFormat any = new SimpleDateFormat("yyyy");

        Date avui = new Date();
        Date auxAvui = (Date) avui.clone();

        if (data.length() > 0) {
            try {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
                avui = format.parse(data);
            } catch (Exception ex) {

            }

        }

        //convidats
        for (UsuarisRols rol : rols) {
            String nomRol = "";
            try {
                if (rol.getLector() == true) {
                    nomRol = " - Lector";
                    DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                    if (f.format(avui).equals(f.format(auxAvui)) == false) {
                        continue;
                    }
                }
                if (rol.getGestor()== true) {
                    nomRol = " - Gestor";
                }
                File f = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + rol.getNomcomplet().replaceAll(" ", ""));
                listUsers.add(f);
                listNameUsers.add(rol.getNomcomplet().replaceAll(" ", ""));
                listNomRols.add(nomRol);
            } catch (Exception ex) {

            }
        }
       
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List<List<String>> listOfListsRev = new ArrayList<List<String>>();
        //ArrayList al = new ArrayList<String>();

        for (int k = 0; k < listUsers.size(); k++) {
            File[] listofFolders = listUsers.get(k).listFiles();
            for (int j = 0; j < listofFolders.length; j++) {
                ArrayList al = new ArrayList<String>();
                if (listofFolders[j].getName().substring(0, 1).equals("1")) {
                    //camera tipus 1
                    try {
                        File folder1 = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui));
                        File[] listOfFiles = folder1.listFiles();
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (i == 0) {
                                al.add(listofFolders[j].getName().substring(1) + listNomRols.get(k));
                            }
                            try {
                                if (inici.length() == 0 && fi.length() == 0) {
                                    al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui) + "/" + listOfFiles[i].getName());
                                } else {
                                    int hora = Integer.parseInt(listOfFiles[i].getName().substring(listOfFiles[i].getName().length() - 10, listOfFiles[i].getName().length() - 4));
                                    if (Integer.parseInt(inici.replaceAll(":", "")) <= hora && hora <= Integer.parseInt(fi.replaceAll(":", ""))) {
                                        al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui) + "/" + listOfFiles[i].getName());
                                    }
                                }
                            } catch (Exception ex) {
                                al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui) + "/" + listOfFiles[i].getName());
                            }

                        }
                    } catch (Exception ex) {

                    }

                }
                if (listofFolders[j].getName().substring(0, 1).equals("2")) {
                    //camera tipus 2
                    try {
                        File folder2 = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images");
                        File[] listOfFiles = folder2.listFiles();
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (i == 0) {
                                al.add(listofFolders[j].getName().substring(1)+ listNomRols.get(k));
                            }
                            try {
                                if (inici.length() == 0 && fi.length() == 0) {
                                    al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                } else {
                                    String hora = listOfFiles[i].getName().substring(listOfFiles[i].getName().length() - 12, listOfFiles[i].getName().length() - 6);
                                    if ((inici.replaceAll(":", "")).compareTo(hora) < 0 && hora.compareTo((fi.replaceAll(":", ""))) < 0) {
                                        al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                    }
                                }
                            } catch (Exception ex) {
                                al.add(ex.getMessage() + "xxx" + listOfFiles[i].getName());
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
                if (listofFolders[j].getName().substring(0, 1).equals("3")) {
                    //camera tipus 3
                    try {
                        File folder3 = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images");
                        File[] listOfFiles = folder3.listFiles();
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (i == 0) {
                                al.add(listofFolders[j].getName().substring(1)+ listNomRols.get(k));
                            }
                            try {
                                if (inici.length() == 0 && fi.length() == 0) {
                                    al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                } else {
                                    String hora = listOfFiles[i].getName().substring(listOfFiles[i].getName().length() - 12, listOfFiles[i].getName().length() - 6);
                                    if ((inici.replaceAll(":", "")).compareTo(hora) < 0 && hora.compareTo((fi.replaceAll(":", ""))) < 0) {
                                        al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                    }
                                }
                            } catch (Exception ex) {
                                al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
                listOfLists.add(al);
            }

        }

        for (int k = 0; k < listUsers.size(); k++) {
            File[] listofFolders = listUsers.get(k).listFiles();
            if(k==1){
                
            }
            for (int j = 0; j < listofFolders.length; j++) {
                ArrayList al = new ArrayList<String>();
                if (listofFolders[j].getName().substring(0, 1).equals("1")) {
                    
                    //camera tipus 1
                    try {
                        File folder1 = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui));
                        File[] listOfFiles = folder1.listFiles();
                      
                        for (int i = listOfFiles.length - 1; i > -1; i--) {
                            if (i == listOfFiles.length - 1) {
                                al.add(listofFolders[j].getName().substring(1) + listNomRols.get(k));
                            }
                            try {
                                if (inici.length() == 0 && fi.length() == 0) {
                                    al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui) + "/" + listOfFiles[i].getName());
                                } else {
                                    int hora = Integer.parseInt(listOfFiles[i].getName().substring(listOfFiles[i].getName().length() - 10, listOfFiles[i].getName().length() - 4));
                                    if (Integer.parseInt(inici.replaceAll(":", "")) <= hora && hora <= Integer.parseInt(fi.replaceAll(":", ""))) {
                                        al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui) + "/" + listOfFiles[i].getName());
                                    }
                                }
                            } catch (Exception ex) {
                                al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + "/" + dia.format(avui) + "/" + listOfFiles[i].getName());
                            }

                        }
                    } catch (Exception ex) {

                    }

                }

                if (listofFolders[j].getName().substring(0, 1).equals("2")) {

                    //camera tipus 2
                    try {                       //D:\SYNC\DAW\CURS\4at_semesrte\PROJECTE\GIT_carepik\carepik\src\main\webapp\WEB-INF\resources\img\cam\TaniaCiutadAlvira
                        File folder2 = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images");
                        File[] listOfFiles = folder2.listFiles();
                        for (int i = listOfFiles.length - 1; i > -1; i--) {
                            if (i == listOfFiles.length - 1) {
                                al.add(listofFolders[j].getName().substring(1) + listNomRols.get(k));
                            }
                            try {
                                if (inici.length() == 0 && fi.length() == 0) {
                                    al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                } else {
                                    String hora = listOfFiles[i].getName().substring(listOfFiles[i].getName().length() - 12, listOfFiles[i].getName().length() - 6);
                                    if ((inici.replaceAll(":", "")).compareTo(hora) < 0 && hora.compareTo((fi.replaceAll(":", ""))) < 0) {
                                        al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                    }
                                }
                            } catch (Exception ex) {
                                al.add(ex.getMessage() + "xxx" + listOfFiles[i].getName());
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
                if (listofFolders[j].getName().substring(0, 1).equals("3")) {
                    //camera tipus 3
                    try {
                        File folder3 = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images");
                        File[] listOfFiles = folder3.listFiles();
                        for (int i = listOfFiles.length - 1; i > -1; i--) {
                            if (i == listOfFiles.length - 1) {
                                al.add(listofFolders[j].getName().substring(1) + listNomRols.get(k));
                            }
                            try {
                                if (inici.length() == 0 && fi.length() == 0) {
                                    al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                } else {
                                    String hora = listOfFiles[i].getName().substring(listOfFiles[i].getName().length() - 12, listOfFiles[i].getName().length() - 6);
                                    if ((inici.replaceAll(":", "")).compareTo(hora) < 0 && hora.compareTo((fi.replaceAll(":", ""))) < 0) {
                                        al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                                    }
                                }
                            } catch (Exception ex) {
                                al.add("/resources/img/cam/" + listNameUsers.get(k) + "/" + listofFolders[j].getName() + "/" + any.format(avui) + mes.format(avui) + dia.format(avui) + "/images/" + listOfFiles[i].getName());
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
                listOfListsRev.add(al);
            }
        }
        modelview.getModelMap().addAttribute("img", listOfLists);
        modelview.getModelMap().addAttribute("imgRev", listOfListsRev);

        if (data == "") {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
            modelview.getModelMap().addAttribute("titol", "Día: Avuí");
        } else {
            inici = inici.substring(0, 5);
            fi = fi.substring(0, 5);
            modelview.getModelMap().addAttribute("titol", "Día: " + data + ", " + inici + " - " + fi);
        }

        return modelview;

    }

    @RequestMapping(value = "/mostrar-imatges", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    void Submit(@RequestParam("imatge") String imatge) {
        //Jordi
        String ruta = "C:/Users/Jordi//Documents";

        //Tania
        //String ruta = "D:/SYNC/DAW/CURS/4at_semesrte/PROJECTE/GIT_carepik";
        //Marina
        //D:/Documentos/NetBeansProjects
        File file = new File(ruta + "/carepik/src/main/webapp/WEB-INF/resources/" + imatge);
        file.delete();
    }

}
