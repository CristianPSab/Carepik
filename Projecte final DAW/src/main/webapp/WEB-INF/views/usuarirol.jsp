<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<!DOCTYPE html>
<html lang="ca">
    <head>
        <title>Convidat</title>
    </head>
    
    <body class="body-registre">
        <jsp:include page="capsalera.jsp"/>
        <div class="contenidor">
            <div id="main">
    
                <form:form modelAttribute="rol" class="box registre cos_contingut" action="rol">           
                    <h1>Afegir / modificar Convidat</h1>
                    <fieldset class="caixetes">
                        <form:hidden path="codi" />
                    
                        <label class="control-label col-12" for="email">
                            Introdueix el email de la persona que vols afegir (la persona ha d'estar registrada previament a l'aplicació):
                        </label>
                        <div class="col-lg-12">
                            <form:input id="email" path="email" type="email" class="form:input-large" placeholder="Email" required="true"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>
                        </div>
                        <c:if test="${not empty error}">
                            <div class="alert alert-warning" role="alert">
                                Email introduït no vàlid, torna-ho a provar.
                            </div>
                        </c:if>
                        <br/>
                        <label class="control-label col-12" for="email">
                            Marca el rol que li vols donar a aquest usuari:
                        </label>
                        <div class="form-check">
                            <label class="form-check-label"> ${enumRol}
                                <form:radiobutton class="form-check-input" name="opcions" path="enumRol" value="0" />Administrador <span style="font-size:12px;">(otorgues tots els permisos).</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <form:radiobutton   class="form-check-input" name="opcions" path="enumRol" value="1" />Gestor <span style="font-size:12px;">(otorgues permisos de lectura i escriptura).</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <form:radiobutton   class="form-check-input" name="opcions" path="enumRol" value="2" />Lector <span style="font-size:12px;">(otorgues permisos de lectura).</span>
                            </label>
                        </div>

                    </fieldset>
                    <input type="submit" id="btnAdd" class="btn btn-primary" value ="Desar"/>
                    <a href="<spring:url value= '/perfilUsuari'/>"><input type="button" id="btnBack" class="btn btn-warning" value="Cancel·lar" /></a>
                </form:form>
            </div>        
        </div> 
       <%-- <jsp:include page="footer.jsp"/> --%>

    
    </body>
</html>
