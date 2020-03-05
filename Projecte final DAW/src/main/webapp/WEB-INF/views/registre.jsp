<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registre</title>       
    </head>
    <body class="body-registre">
        <jsp:include page="capsalera.jsp"/> 
        <div id="contenidor" class="contenidor">
            <div id="main">
                <form:form modelAttribute="formRegistre" class="box registre cos_contingut" action="registre">
                    <h1>Registre</h1>
                    <fieldset class="caixetes">
                        <h3 class="titol-apartats">Dades personals</h3>
                        <form:input id="nom" path="nom" type="text" class="form:input-large" pattern="^[a-zA-Z_??????\s]*$" 
                                    title="Nom?s s'accepten lletres" placeholder="Nom" required="true"/>
                        <form:input id="cognoms" path="cognoms" type="text" class="form:input-large" pattern="^[a-zA-Z_??????\s]*$"
                                    title="Nom?s s'accepten lletres" placeholder="Cognoms" required="true"/>            
                        <form:input id="email" path="email" type="email" class="form:input-large" placeholder="Email" required="true"
                                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>      
                        <form:input id="contrasenya" path="contrasenya" type="password" class="form:input-large"  
                                    placeholder="Contrasenya" pattern=".{6,}" title="M?nim 6 caracters" required="true"/>
                        <input id="contrasenya2" type="password" pattern=".{6,}" placeholder="Repetir contrasenya" title="M?nim 6 caracters" required/>
                    </fieldset>
                    <%--<fieldset class="caixetes">
                        <h3 class="titol-apartats">Dades càmera <c:if test="${1==1}">(opcional)</c:if></h3>
                        <p class="titol-descripcio"> (Afegeix més càmeres un cop registrat.)</p>

                        <select class="form-control" data-live-search="true" id="sel-camera">
                            <option value="">Escollir càmera</option>
                            <optgroup label="Dericam">						
                                <option value="Dericam Càmara WIFI 1080">Dericam Camera WIFI 1080</option>
                            </optgroup>
                            <optgroup label="SZSINOCAM">
                                <option value="1080p Audio Camera">1080p Audio Camera</option>
                                <option value="1080p 400w Camera" >1080p 400w Camera</option>
                            </optgroup>
                            <optgroup label="NexGadget">						
                                <option value="NexGadget C?mara IP">NexGadget Camera IP</option>
                            </optgroup>
                        </select>            
                    </fieldset>--%>    
                    <input type="submit"  value="Registre">
                    <c:if test="${not empty error}">
                        <div class="alert alert-warning" role="alert">
                            Aquest email està donat de alta, torna-ho a provar amb un altre compte.
                        </div>
                    </c:if>
                    <div class="logejarte">
                        <p>Ja ets usuari registrat? Llavors fes <a href="<spring:url value= '/login'/>">Login</a>!</p>
                    </div>
                </form:form> 

            </div>
        </div>


        <jsp:include page="footer.jsp"/>  

        <script type="text/javascript">
            $('#contrasenya, #contrasenya2').on('keyup', function () {
                if ($('#contrasenya').val() == $('#contrasenya2').val()) {
                    $('#btnAdd').prop("disabled", false);
                } else
                    $('#btnAdd').prop("disabled", true);
            });
        </script>
    </body>

</html>
