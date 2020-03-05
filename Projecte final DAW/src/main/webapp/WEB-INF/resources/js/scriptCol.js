jQuery('document').ready(function () {

    //Botons canvi columnes d'imatges.
    document.getElementById("1col").addEventListener("click", columna1, false);
    document.getElementById("2col").addEventListener("click", columna2, false);
    document.getElementById("3col").addEventListener("click", columna3, false);
    document.getElementById("4col").addEventListener("click", columna4, false);

    function columna1() {
        $("div.imatgesEspai").removeClass().addClass("imatgesEspai col-12 col-sm-12 col-md-12 col-lg-12");
    }
    function columna2() {
        $("div.imatgesEspai").removeClass().addClass("imatgesEspai col-6 col-sm-6 col-md-6 col-lg-6");
    }
    function columna3() {
        $("div.imatgesEspai").removeClass().addClass("imatgesEspai col-4 col-sm-4 col-md-4 col-lg-4");
    }
    function columna4() {
        $("div.imatgesEspai").removeClass().addClass("imatgesEspai col-3 col-sm-3 col-md-3 col-lg-3");
    }




    /* Funcions per quan l'usuari fa scroll 200 pixels, apareix el botó */
    window.onscroll = function () {
        funcioScroll();
    };

    function funcioScroll() {
        if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
            document.getElementById("aDalt").style.display = "block";
        } else {
            document.getElementById("aDalt").style.display = "none";
        }
    };

});

/* al clicar al botó puja a dalt de tot de la pàgina */
function funcioPujar() {
    $('body,html').animate({scrollTop : 0}, 600);
    return false;
};


  
    
/* Funcio per ordenar les imatges ascendents o descendents en horari */
function funcioAscendent(){
    document.getElementById("imatgesOrdreDesc").style.display = "none";
    document.getElementById("imatgesOrdreAsc").style.display = "flex";
};

function funcioDescendent(){
    document.getElementById("imatgesOrdreAsc").style.display = "none";
    document.getElementById("imatgesOrdreDesc").style.display = "flex";
};

