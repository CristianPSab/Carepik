<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap 4 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

        <!-- CSS -->
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

        <title></title>
    </head>
    <body>
        <header class="header">
            <div class="contenidor logo-nav-container">


                <!--Usuari logat-->

                <a href="<spring:url value= '/'/>" class="logo">
                    <img class="navbar-brand" src="<c:url value="/resources/img/CarePik_imago.png"/>" />
                    <span class="logo-text">Care<span class="logo-span">Pik</span></span>
                </a>

                <div class="grup-menu">
                    <span class="menu-icon"><img src="<c:url value="/resources/img/iconoMenu50-blau.png"/>" /></span>
                    <!-- Navigation -->
                    <nav class="navegacio navbar">
                        <div class="container">
                            <ul>

                                <c:choose>
                                    <c:when test="${index==0}">
                                        <!--usuari no logat-->
                                      
                                            <li role="presentation" class="active">
                                                <a  href="<spring:url value= '/login'/>">
                                                    login
                                                </a>
                                            </li>
                                            <li role="presentation" class="active">
                                                <a  href="<spring:url value= '/registre'/>">
                                                 registre
                                                </a>
                                            </li>
                                        
                                    </c:when> 
                                    <c:otherwise>
                                        <!--Usuari logat-->
                                      
                                            <li><a   href="<spring:url value= '/perfilUsuari'/>">Perfil usuari</a></li>
                                            <li><a   href="<spring:url value= '/mostrar-imatges?data=&inici=&fi='/>">Càmeres</a></li>
                                            <li><a   href="<spring:url value='/logout' />" onclick="return confirm('Estas segur que vols sortir de la sessió?');">Sortir</a></li>
                                           

                                    </c:otherwise>
                                </c:choose>

                            </ul>	
                        </div>
                    </nav>
                </div>
            </div>
        </header>
    </body>
</html>
