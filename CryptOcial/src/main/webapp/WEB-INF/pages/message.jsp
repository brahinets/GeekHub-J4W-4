<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Mail</title>

    <link rel="stylesheet" href="<c:url value="/res/css/mail.css"/>" type="text/css">

    <script src="<c:url value="/res/js/jquery-2.1.0.min.js"/>"></script>
    <script>
/*        $(function() {
            $("#sendMessage").click(function(){

                var $recipient = $('input[name=recipient]').val();
                var $theme = $('input[name=theme]').val();
                var $content = $('textarea[name=content]').val();

                console.log($recipient);
                console.log($theme);
                console.log($content);

                if($content != null && $content != "" && $recipient != null && $recipient != ""){
                    var $save = $("#sendMessage");
                    $save.css("display", "none");

                    var $sendingMessage = $("#sendingMessage");
                    $sendingMessage.css("display","block");
                    $sendingMessage.css("background-image", "url(load.gif)");

                    $.ajax({
                        url: "/mail/write",
                        method : "post",
                        data: {
                            recipient : $recipient,
                            theme : $theme,
                            content : $content
                        },

                        success: function(res){
                            var $resultBlock = $("#resultForm");
                            var $questionsBlock = $("#messageForm");

                            $resultBlock.css({display:"block"});
                            $questionsBlock.css({display:"none"});


                            *//* TODO load list of mail instead mail form *//*
                            *//*window.location = "/mail"*//*


                        },

                        error: function(){
                            console.log("error");
                        }
                    });

                }
            });
        });*/
</script>
</head>

<body >

<div class="container">
    <!-- top nav bar start -->
        <jsp:include page="topNavBar.jsp"/>
    <!-- top nav bar end -->

    <!-- body start -->
    <div class="row">

        <!-- left nav bar start -->
        <jsp:include page="leftNavBar.jsp"/>
        <!-- left nav bar end -->

        <div class="col-md-9 full">
            <form id="messageForm" method="post" action="<c:url value="/mail/write"/>" class="tsc_form_contact_light frame tbar">
                <h3>New Message</h3>

                <div class="row">

                    <div class="col-md-2">
                        <a href="/user/${message.user.id}">
                        <div class="ov-hidden message-user well-vsm">
                            <div>${message.user.name}</div>
                            <div>${message.user.surname}</div>
                        </div>

                        <div class="imgNormal">
                            <img src="data:image/png;base64,${message.user.avatar}"/>
                        </div>
                        </a>

                        <c:choose>
                            <c:when test="${message.user.isOnline}">
                                <div class="text-center">
                                    <span class="online">Online</span>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="col-md-10">
                        <c:choose>
                            <c:when test="${message.text != null}">
                                <input type="hidden" value="${message.sender}" name="recipient"/>

                                <div class="input-group margin-bootom-ten">
                                    <span class="input-group-addon">Theme :  </span>
                                    <input readonly type="text" name="theme" class="form-control" placeholder="Theme (optional)" value="${message.theme}" required >
                                </div>

                                <hr>
                                <div class="input-group none-bg full">
                                    <span class="input-group-addon">Message : </span>
                                    <textarea readonly class="full-width" rows="5">${message.text}</textarea>
                                </div>
                                <hr>
                            </c:when>

                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${fn:length(recipients) == 1}">
                                        <input type="hidden" value="${recipients[0].id}" name="recipient"/>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="input-group">
                                            <select multiple name="recipients" class="form-control" required="">
                                                <c:forEach items="${recipients}" var="recipient">
                                                    <option value="${recipient.id}">${recipient.name} ${recipient.surname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${message.user.id != myID}">
                                        <div class="input-group margin-bootom-ten">
                                            <span class="input-group-addon">@</span>
                                            <input type="text" name="theme" class="form-control" placeholder="Theme (optional)" required >
                                        </div>
                                    </c:when>
                                </c:choose>

                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${message.user.id != myID}">
                                <textarea id="content" name="content" class="form-control message-user" rows="8" placeholder="Send something (required)" required></textarea>
                                <input class="blueButton right" id="sendMessage" type="submit" value="Send Message"/>
                                <div  style="display : none; float:right;  margin-top: 10px;" id="sendingMessage"><img src="<c:url value="/res/img/load.gif"/>"></div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </form>
            <div id="resultForm" style="display : none; margin-top:5px; width:70%">Message sended</div>
        </div>
    </div>
    <!-- body end -->
</div>
</body>
</html>