<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Imatges càmera</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    </head>
    <body class="body-registre">
        <jsp:include page="capsalera.jsp"/> 
        <div class="contenidor imatgesCameres">

            <div id="desplegable-dies" >

                <div class="col-3 col-sm-2 col-md-2 col-lg-1">
                    <h3>Dia: </h3>
                    <div class="botonsColumnes">
                        <form action="/carepik/mostrar-imatges" method="get">
                            <input id="avui" path="data" name="data" style="display:none;"/>
                            <input id="in" path="inici" name="inici" style="display:none;"/>
                            <input id="fin" path="fi" name="fi" style="display:none;"/>
                            <input type="submit"  value="Avui" class="btn btn-primary btn-info ordre inputsFiltre"/>
                        </form>
                    </div>  
                </div>
                <div class="col-9 col-sm-10 col-md-10 col-lg-6">
                    <h3>Filtrar per día i hora:</h3>
                    <div class="botonsColumnes">
                        <form action="/carepik/mostrar-imatges" method="get" class="filtresData">
                            <label for="data">Data: </label>
                            <input id="data" path="data" name="data" data-inputmask="'alias': 'date'" style="width:100px;"/>
                            <label for="inici">Inici: </label>
                            <input id="inici" path="inici" name="inici" style="width:60px;"/>
                            <label for="fi">Fi: </label>
                            <input id="fi" path="fi" name="fi" style="width:60px;" />
                            <input type="submit" class="btn btn-primary btn-info ordre inputsFiltre" value="Cercar" />                        
                        </form>
                    </div>                  
                </div>
                <div class="col-12 col-sm-5 col-md-5 col-lg-2">
                    <h3>Horari:  </h3>
                    <div class="botonsColumnes">
                        <button onclick="funcioAscendent()" class="btn btn-primary btn-info ordre" id="ordreAsc" name="Asc" style="font-size: 1.2em;">Asc <i class="fas fa-angle-up"></i></button>
                        <button onclick="funcioDescendent()" class="btn btn-primary btn-info ordre" id="ordreDesc" name="Desc" style="font-size: 1.2em;">Desc <i class="fas fa-angle-down"></i></button>
                    </div>
                </div>   

                <div class="col-12 col-sm-7 col-md-7 col-lg-3">
                    <h3>Columnes: </h3>
                    <div class="botonsColumnes">
                        <button type="submit" class="btn btn-primary btn-info" id="1col" name="col1" style="font-size: 1.2em;">1<img src="<c:url value="/resources/img/col1.png" />" /></button>
                        <button type="submit" class="btn btn-primary btn-info" id="2col" name="col2" style="font-size: 1.2em;">2<img src="<c:url value="/resources/img/col2.png" />" /></button>
                        <button type="submit" class="btn btn-primary btn-info" id="3col" name="col3" style="font-size: 1.2em;">3<img src="<c:url value="/resources/img/col3.png" />" /></button>
                        <button type="submit" class="btn btn-primary btn-info" id="4col" name="col4" style="font-size: 1.2em;">4<img src="<c:url value="/resources/img/col4.png" />" /></button>
                    </div>
                </div>           
            </div> 

            
            <div class="row">
                <div class="col-0 col-xl-3 col-lg-3 col-md-0 col-sm-0"></div>
                <div class="col-8 col-xl-5 col-lg-6 col-md-8 col-sm-8">
                    <h1 class="text-center w-100 tempsEscollit">${titol}</h1>
                </div>
                <div class="col-1 col-xl-3 col-lg-2 col-md-2 col-sm-2"></div> 
                <div class="col-2 col-xl-1 col-lg-1 col-md-2 col-sm-1 margeLateral botonsColumnes">
                    <button onclick="toggleEliminar()" id="toggler" type="button" class="btn btn-danger botoBorrar" style="font-size: 1.2em;">Esborrar</button>
                </div>
            </div>     

            <div class="row col-12" id="imatgesOrdreAsc">
                <ul id="tabulA" class="nav nav-tabs" style="border-bottom: 0 !important;z-index:2">
                    <c:forEach items="${img}" var="cam" varStatus="camloop">
                        <c:forEach items="${cam}" var="x" varStatus="loop">
                            <c:if test="${loop.index == 0}">
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#A${fn:replace(x, ' ', '')}" >${x}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </ul>
                <div class="tab-content border" >
                    <c:forEach items="${img}" var="cam" varStatus="camloop">                              
                        <c:forEach items="${cam}" var="imatge" varStatus="loop"> 
                            <c:if test="${loop.index == 0}">
                                <c:if test="${camloop.index == 0}">
                                    <div id="A${fn:replace(imatge, ' ', '')}" class="tab-pane active"><br>
                                    </c:if>
                                    <c:if test="${camloop.index != 0}">
                                        <div id="A${fn:replace(imatge, ' ', '')}" class="tab-pane"><br>

                                        </c:if>
                                        <div class="row">
                                        </c:if>
                                        <c:if test="${loop.index != 0}">
                                            <div class=" imatgesEspai col-12 col-md-4 col-lg-3 p-4" style="position:relative;">
                                                <c:if test="${not fn:contains(cam[0],'Lector') && not fn:contains(cam[0],'Gestor')}">
                                                    <button class="boto-eliminar btn btn-danger rounded-circle" style="position:absolute;right:26px;top:26px;display:none;" onclick="eliminarImatge(this)"><i class="fas fa-times"></i></button>
                                                </c:if>
                                                
                                                
                                                <img class="img-responsive d-flex justify-content-center" style="width: 100%" src='<spring:url value="${imatge}"/>'/>
                                            </div>                                        
                                        </c:if>

                                    </c:forEach>

                                    <c:if test="${fn:length(cam) gt 0}">
                                    </div>
                                </div>

                            </c:if>


                        </c:forEach>
                </div>

            </div>


            <div class="row col-12" id="imatgesOrdreDesc">
                <ul id="tabulD" class="nav nav-tabs" style="border-bottom: 0 !important;z-index:2">
                    <c:forEach items="${img}" var="cam" varStatus="camloop">
                        <c:forEach items="${cam}" var="x" varStatus="loop">
                            <c:if test="${loop.index == 0}">
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#D${fn:replace(x, ' ', '')}">${x}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </ul>
                <div class="tab-content border">
                    <c:forEach items="${imgRev}" var="cam" varStatus="camloop">                              
                        <c:forEach items="${cam}" var="imatge" varStatus="loop"> 
                            <c:if test="${loop.index == 0}">
                                <c:if test="${camloop.index == 0}">
                                    <div id="D${fn:replace(imatge, ' ', '')}" class="tab-pane active"><br>
                                    </c:if>
                                    <c:if test="${camloop.index != 0}">
                                        <div id="D${fn:replace(imatge, ' ', '')}" class="tab-pane"><br>

                                        </c:if>
                                        <div class="row">
                                        </c:if>
                                        <c:if test="${loop.index != 0}">
                                            <div class=" imatgesEspai col-12 col-md-4 col-lg-3 p-4" style="position:relative;">
                                                <c:if test="${not fn:contains(cam[0],'Lector') && not fn:contains(cam[0],'Gestor')}">
                                                    <button class="boto-eliminar btn btn-danger rounded-circle" style="position:absolute;right:26px;top:26px;display:none;" onclick="eliminarImatge(this)"><i class="fas fa-times"></i></button>
                                                </c:if>
                                                <img class="img-responsive d-flex justify-content-center" style="width: 100%" src='<spring:url value="${imatge}"/>'/>
                                            </div>                                        
                                        </c:if>

                                    </c:forEach>

                                    <c:if test="${fn:length(cam) gt 0}">
                                    </div>
                                </div>

                            </c:if>


                        </c:forEach>
                    </div>
                </div>

            </div>    
        </div>


        <!-- Botó per tornar a dalt quan hi ha scroll -->
        <button onclick="funcioPujar()" id="aDalt" title="Ves al principi" class="fas fa-arrow-up"></button>    


        <jsp:include page="footer.jsp"/>
        <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/jquery.inputmask.bundle.js"></script>
        <script src="https://momentjs.com/downloads/moment.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/scriptCol.js" />"></script>
        <script type="text/javascript">
            $(":input").inputmask();
            $("#inici").inputmask({"mask": "99:99"});
            $("#fi").inputmask({"mask": "99:99"});
            $("#data").val(moment(new Date()).format("DD/MM/YYYY"));
            $("#inici").val("00:00");
            $("#fi").val("23:59");
        </script>

        <script type="text/javascript">
            function eliminarImatge(ths) {
                //console.log($($(ths).parent().find("img")[0]).attr("src"));
                img = $($(ths).parent().find("img")[0]).attr("src").split("resources/")[1];
                var obj = {
                    imatge: img
                }
                $.ajax({
                    type: "POST",
                    url: "/carepik/mostrar-imatges",
                    data: {imatge: img}
                }).done(function () {
                    $(ths).parent().remove();
                });
            };

            function toggleEliminar(ths) {
                if ($("#toggler").text() == "Esborrar") {
                    $("#toggler").text("Finalitzar");
                    $("#toggler").addClass("botoBorrarActiu");
                } else {
                    $("#toggler").text("Esborrar");
                    $("#toggler").removeClass("botoBorrarActiu");
                }
                $(".boto-eliminar").toggle();
            };

            $(document).ready(function () {
                $($("#tabulA .nav-item .nav-link")[0]).addClass("active");
                $($("#tabulD .nav-item .nav-link")[0]).addClass("active");
                $($("#imatgesOrdreAsc .tab-pane")[0]).addClass("active");
                $($("#imatgesOrdreDesc .tab-pane")[0]).addClass("active");
                
                $("#tabulA .nav-item").on('click',function(){
                    $("#tabulD .nav-item a").removeClass("active");
                    $($("#tabulD .nav-item a")[$(this).index()]).addClass("active");
                    $("#imatgesOrdreDesc .tab-pane").removeClass("active");
                    $($("#imatgesOrdreDesc .tab-pane")[$(this).index()]).addClass("active");
                });
                
                $("#tabulD .nav-item").on('click',function(){
                    $("#tabulA .nav-item a").removeClass("active");
                    $($("#tabulA .nav-item a")[$(this).index()]).addClass("active");
                    $("#imatgesOrdreAsc .tab-pane").removeClass("active");
                    $($("#imatgesOrdreAsc .tab-pane")[$(this).index()]).addClass("active");
                });
            });
            


        </script>

    </body>
</html>
