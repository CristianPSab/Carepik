<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <title>Registre Camera</title>
    </head>
    <body>
        <jsp:include page="capsalera.jsp"/> 
        <div id="contenidor" class="contenidor">
            <div id="main">
                    
                    <form:form modelAttribute="camera" class="box registre cos_contingut"  action="camera">
                        <h1>Registrar Càmera</h1>
                        <fieldset class="caixetes">                       
                            <form:hidden path="codi" />
                            
                            <div class="form-group">
                                <label class="control-label col-lg-4" for="coditipocamera">
                                    Model de càmera:
                                </label>
                                <form:select path="coditipocamera" class="form-control" id="coditipocamera">
                                    <form:option value="0">Escollir càmera</form:option>
                                    <optgroup label="SZSINOCAM">
                                        <form:option value="1">1080p Audio Camera</form:option>
                                    </optgroup>
                                    <optgroup label="NexGadget">						
                                        <form:option value="2">NexGadget Camera IP</form:option>
                                    </optgroup>
                                </form:select> 
                            </div>

                            <div class="form-group">
                                <label class="control-label col-lg-4" for="nom">
                                    Nom de la càmera:
                                </label>
                                <div class="col-lg-12">  
                                    <form:input id="nom" path="nom" type="text" class="form:input-large"  
                                                required="true" placeholder="Nom de la càmera"/>
                                </div>
                            </div>
                            

                            <input type="submit" id="btnAdd" class="btn btn-primary" value ="Desar"/>
                            <a href="<spring:url value= '/perfilUsuari'/>"><input type="button" id="btnBack" class="btn btn-warning" value="Cancel·lar" /></a>
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>       

        <%--  <jsp:include page="footer.jsp"/>   --%>
    </body>
</html>
