<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>





<c:if test="${index==0}">
    <footer id="footer" class="bg-dark" >
        <div class="d-flex align-items-end" >
            <div class="footer-column1">
                <ul class="list-inline mb-2">
                    <li class="list-inline-item">
                        Copyright © 2019 Tots els drets reservats. | <a href="<spring:url value= '/grup2'/>">Qui som.</a>
                    </li>
                </ul>    
            </div>
            <div class="footer-column2">
                <ul class="list-inline mb-2">
                    <li class="list-inline-item">
                        <a href="<spring:url value= '/politicacookies'/>">Política de cookies</a> |
                        <a href="<spring:url value= '/politicaprivacitat'/>">Política de privacitat</a>
                    </li>
                </ul>    
            </div> 
        </div>


    </footer>
    <div class="alert alert-primary alert-dismissible fade show" role="alert" id="consentir_cookies">
        Aquest lloc web fa servir cookies pròpies faciliar-te una experiència de navegació òptima i recabar 
        informació anònima per millorar i adaptar-nos a les teves preferències i pautes de navegació. Navegar sense acceptar 
        les cookies limitarà la visibilitat i funcions del web. Per a més informació consulteu l'avis legal. 
        <button type="button" class="close" data-dismiss="alert" aria-label="Close" id="tancarCookies" data-toggle="tooltip" title="Acceptar i tancar">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if> 


<script src="<c:url value="https://code.jquery.com/jquery-3.3.1.slim.min.js"/>" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"/>" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"/>" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="https://dev.sabarca.cat/assets_sabarca/lib/jquery/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cookies.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>