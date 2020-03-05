<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<!DOCTYPE html>
<html lang="ca">
    <head>
        <title>Convidats</title>      
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    </head>

    <body >
        <!-- LLISTAT DE CONVIDATS -->
        <div >
            <section >
                <div class="rols">                        
                    <div class="row">
                        <c:forEach items="${rols}" var="rol">
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 d-inline-block mb-1">
                                <div style="border:2px solid lightgray;border-radius:15px;">
                                    <div class="d-flex" style="text-align:left;">
                                        <div class="col-3">
                                            <c:if test="${rol.getAdmin()==true}"><span><i class="fas fa-crown admin pl-0"></i></span></c:if>
                                            <c:if test="${rol.getGestor()==true}"><span><i class="fas fa-tasks gestor pl-0"></i></span></c:if>
                                            <c:if test="${rol.getLector()==true}"><span><i class="fas fa-book-reader lector pl-0"></i></span></c:if>
                                            </div>
                                            <div class="col-9 pl-2 pt-3 puntsTall">
                                            <c:if test="${rol.getAdmin()==true}"><span>Administrador</span></c:if>
                                            <c:if test="${rol.getGestor()==true}"><span>Gestor</span></c:if>
                                            <c:if test="${rol.getLector()==true}"><span>Lector</span></c:if>
                                            <br/><span style="font-size:1rem;" title="${rol.getEmail()}">${rol.getEmail()}</span>
                                        </div>
                                    </div>
                                    <div class="col-12 pb-2" style="text-align:left;">
                                        <a href=" <spring:url value= '/rol?codi=${rol.getCodi()}&error=' /> " class="btn btn-primary detall mb-2">
                                            <i class="fas fa-pencil-alt"></i></span> Detall
                                        </a>
                                        <a href="javascript:void(0);" class="btn btn-danger eliminar mb-2" onclick="javascript:if (confirm('Segur que vols eliminar aquest registre?')) {
                                                        window.location.href = '/carepik/delrol?codi=${rol.getCodi()}';
                                                    }">
                                            <i class="fas fa-trash-alt"></i></span> Eliminar
                                        </a>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="pb-2" style="margin-bottom:5px;text-align: left;">
                        <a href='/carepik/rol?codi=0&error='>          
                            <button class="btn btn-success afegir botoMes"><i class="fas fa-plus"></i> Afegir Convidat</button>
                        </a>
                    </div>
                </div>
            </section>
        </div>  
    </body>
</html>
