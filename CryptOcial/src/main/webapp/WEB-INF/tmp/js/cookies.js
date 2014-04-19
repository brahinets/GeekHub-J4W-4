function setCookie(cname,cvalue,exhours) {
    var d = new Date();
    d.setTime(d.getTime()+(exhours*60*60*1000));
    var expires = "expires="+d.toGMTString();

    document.cookie = cname + "=" + cvalue + "; " + expires;

    return "Now cookie '"+ cname + " = " + cvalue + " : " + expires;
}


function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');

    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();

        if (c.indexOf(name) == 0)
            return cname + " = " +c.substring(name.length,c.length);
    }

    return "No such cookie";
}


function deleteCookie(cname) {
    var d = new Date();
    d.setTime(d.getTime()+(0));
    var expires = "expires="+d.toGMTString();
    var cvalue = getCookie(cname);
    document.cookie = cname + "=" + cvalue + "; " + expires;

    return "Cookie '"+ cname + " ' deleted";
}
