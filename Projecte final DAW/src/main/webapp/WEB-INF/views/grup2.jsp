<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
    <head>
        <title>Qui Som</title>     
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body class="body-registre">
        <jsp:include page="capsalera.jsp"/>
        <div id="contenidor"  class="contenidor">
            <div id="main">
                <div class="cos_contingut">
                    <h1>Qui som: Grup2</h1>
                    <h2>Som un grup de 4 persones que desenvolupem aplicacions web. </h2>
                    <h3>Aquests som nosaltres!!</h3>
                
                    <div class="grup2caixes">
                        <div class="col">
                            <h4>Marina Espinosa i Salat</h4>
                            <p>Desenvolupadora web, amb preferencies cap al front-end i el disseny. Experiència en 
                            llenguatges html, css, javascript, jquery, java, mysql.</p>
                            <img class="personesGrup2" src="<c:url value="/resources/img/marina.png"/>" />
                        </div>
                        <div class="col">
                            <h4>Jordi Pino i Esteve</h4>
                            <p>Desenvolupador web full stack des de fa més de 3 anys, 
                                sobretot en els llenguatges de .net i java pel backend i html, css, js, angularjs pel frontend</p>
                            <img class="personesGrup2" src="<c:url value="/resources/img/jordi.png"/>" />
                        </div>
                        <div class="col">
                            <h4>Tània Ciutad i Alvira</h4>
                            <p>Desenvolupadora web, amb experiència de dos anys en temes front-end i
                            back end, sobretot amb tecnologies html, css, javascript, jquery, PHP, mysql, java.</p>
                            <img class="personesGrup2" src="<c:url value="/resources/img/tania.png"/>" />
                        </div>
                        <div class="col">
                            <h4>Cristian Puntí i Sabatés</h4>
                            <p>Desenvolupament web front i back, amb pràctica en tecnologies 
                                html5, css3, javascript, jquery, java i mysql. Coneixements en 
                                .net, C# i PHP.</p>
                            <img class="personesGrup2" src="<c:url value="/resources/img/cristian.png"/>" />
                        </div>
                    </div>
                </div>      
            </div>           
        </div>     

        <jsp:include page="footer.jsp"/>  
    </body>
</html>
