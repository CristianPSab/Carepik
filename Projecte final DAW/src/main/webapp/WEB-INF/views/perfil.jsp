<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
    <head>
        <title>Perfil Usuari</title>       
    </head>
    <body class="body-registre">
        <jsp:include page="capsalera.jsp"/>
        <div id="contenidor" class="contenidor">
            <div id="main">
                <div class="box registre cos_contingut">
                    <h1>${saluda}</h1>
                    <fieldset class="caixetes">
                        <h3>Dades personals</h3>
                        <form:form modelAttribute="formRegistre" action="perfilUsuari">
                            <fieldset >
                                <form:hidden path="codi" />                                
                                <form:input id="nom" path="nom" type="text" class="form:input-large" pattern="^[a-zA-Z_??????\s]*$" 
                                            title="Nom?s s'accepten lletres" placeholder="Nom" required="true"/>
                                <form:input id="cognoms" path="cognoms" type="text" class="form:input-large" pattern="^[a-zA-Z_??????\s]*$"
                                            title="Nom?s s'accepten lletres" placeholder="Cognoms" required="true"/>            
                                <form:input id="email" path="email" type="email" class="form:input-large" placeholder="Email" required="true"
                                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>                               
                                <form:hidden path="contrasenya" />
                                <%--<form:input id="contrasenya" path="contrasenya" type="password" class="form:input-large"  
                                            placeholder="Contrasenya" pattern=".{6,}" title="M?nim 6 caracters" required="true"/>
                                <input id="contrasenya2" type="password" pattern=".{6,}" placeholder="Repetir contrasenya" title="M?nim 6 caracters" required/>--%>
                            </fieldset>
                            <input type="submit"  value="Desar">
                            <c:if test="${not empty error}">
                                <div class="alert alert-warning" role="alert">
                                    Aquest email està donat de alta, torna-ho a provar amb un altre compte.
                                </div>
                            </c:if>
                            
                        </form:form> 
                    </fieldset>

                    <fieldset class="caixetes">
                        <h3>Llistat de convidats </h3>
                        <jsp:include page="usuarisrols.jsp"/> 

                    </fieldset>

                    <fieldset class="caixetes">
                        <h3>Dades càmeres</h3>
                        <jsp:include page="cameres.jsp"/>
                    </fieldset>
                </div>
            </div>  
                   
        </div>
      
                    
        <!-- Scripts del footer. -->	
        <script src="<c:url value="https://code.jquery.com/jquery-3.3.1.slim.min.js"/>" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"/>" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"/>" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script type="text/javascript" src="<c:url value="https://dev.sabarca.cat/assets_sabarca/lib/jquery/jquery.min.js" />"></script>       
        <script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
        
    </body>
</html>
