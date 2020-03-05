<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body class="body-login">
        <jsp:include page="capsalera.jsp"/> 
        <div id="contenidor-login" class="contenidor">
            <div id="main">
                <form class="box login cos_contingut" action="" method="POST">
                    <h1>Log in</h1>
                    <input type="text" name="email" placeholder="Email">
                    <input type="password" name="contrasenya" placeholder="Contrasenya">
                    <input type="submit" value="Entrar">
                    <c:if test="${not empty error}">
                        <div class="alert alert-warning" role="alert">
                            Oops! Email o contrasenya incorrectes, torna-ho a provar.
                        </div>
                    </c:if>
                    <div class="logejarte">
                        <p>Encara no tens un compte creat? <a href="<spring:url value= '/registre'/>">Registra't</a></p>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
