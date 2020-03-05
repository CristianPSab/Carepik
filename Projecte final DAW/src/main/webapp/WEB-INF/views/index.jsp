<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:choose>
            <c:when test="${index==0}">
                <title>Benvinguda</title>
            </c:when> 
            <c:otherwise>
                <title>Home</title>
            </c:otherwise>
        </c:choose>


    </head>
    <body>
        <jsp:include page="capsalera.jsp"/> 


        <div class="overlay " style="margin-top: 105px">
            <div class="container jumbotron h-50 p-1">
                <div class="row  justify-content-center">

                    <div class="row  justify-content-center col-xl-6 ">


                        <c:choose>
                            <c:when test="${index==0}"> 
                                <hgroup>
                                    ${benvinguda} 
                                    <!--<a href="<%--<c:url value='/download/a' />--%>">Baixar el manual d'usuari</a>--> 
                                    <h2 class="text-md-justify">El teu repositori d’imatges online.</h2>
                                </hgroup>

                                <a href="<spring:url value= '/'/>" class="logo">
                                    <img class="img-responsive  justify-content-center " style="width: 300px" src='<spring:url value="/resources/img/image${imageCode}.jpg"/>'/>

                                </a>
                                    <a target="_blank" href="<c:url value="/resources/manualUsuari/Manual_Carepik.pdf"/>" > <p class="text-md-justify text-success">Ets nou a CarePik, consulta el nostre <span style="color:#363B7C">Manual d'usuari</span></p></a>        <br/>


                                <!-- Contact Section -->
                                <div class="container" >
                                    <div class="row">
                                        <div class="col-lg-12 text-lg-center" style="margin-top: 30px">
                                            <h2 class="section-heading text-lg-center">Contacti amb nosaltres</h2>
                                            <h3 class="section-subheading text-muted text-lg-center">Vull sol·licitar informació:</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="row-lg-9 ">
                                            <form name="sentMessage" action="mailto:cpunti27@gmail.com?subject=Formulari de contacte" method="post" enctype="text/plain">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="nom" placeholder="El teu nom *"  >
                                                            <p class="help-block text-danger" ></p>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="email" class="form-control" name="email" placeholder="El teu email *"  >
                                                            <p class="help-block text-danger" ></p>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="tel" class="form-control" name="mobil" placeholder="El teu mòbil *"  >
                                                            <p class="help-block text-danger" ></p>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input type="tel" class="form-control" name="assumpte" placeholder="Assumpte *"  >
                                                            <p class="help-block text-danger" ></p>
                                                        </div>
                                                        <div class="form-group">
                                                            <textarea class="form-control" name="missatge" placeholder="Missatge *"  ></textarea>
                                                            <p class="help-block text-danger" ></p>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="col-lg-12 text-center">
                                                        <div id="success"></div>
                                                        <input type="submit" class="btn btn-xl-12 text-light bg-info" value="Envia un missatge" />
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                </section> 





                            </c:when> 
                            <c:otherwise>
                                <div class="h-100 d-inline-block" style="margin-top:100px">
                                    <hgroup>
                                        <h1 class='text-md-center' style='color:blue'>${benvinguda2} </h1><h1 class='text-md-center' style='color:orangered'>${nom} ${cognom}</h1>
                                    </hgroup>
                                    <a href="<spring:url value= '/'/>" class="logo">
                                        <img class="img-responsive  justify-content-center" style="width: 500px" src='<spring:url value="/resources/img/image${imageCode}.jpg"/>'/>

                                    </a>

                                    <br>
                                    <a target="_blank" href="<c:url value="/resources/manualUsuari/Manual_Carepik.pdf"/>" > <p class="text-md-justify text-success">Ets nou a CarePik, consulta el nostre <span style="color:#363B7C">Manual d'usuari</span></p></a>        <br/>

                                    

                                </div>


                            </c:otherwise>
                        </c:choose>





                    </div>
                </div>

            </div>

        </div>







        <jsp:include page="footer.jsp"/>
    </body>
</html>