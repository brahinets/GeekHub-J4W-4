<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>...Number of messages...</title>

    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/global.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/user.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/mail.css"/>" type="text/css">

    <style>    </style>

    <script src="<c:url value="/res/js/jquery-2.1.0.min.js"/>"></script>
    <script src="<c:url value="/res/js/dragAndDrop.js"/>"></script>
<script>

    //    $(function() {
    //        $("#sendMessage").click(function(){
    //
    //            var $recipient = $('input[name=recipient]').val();
    //            var $theme = $('input[name=theme]').val();
    //            var $content = $('textarea[name=content]').val();
    //
    //            console.log($recipient);
    //            console.log($theme);
    //            console.log($content);
    //
    //            if($content != null && $content != "" && $recipient != null && $recipient != ""){
    //                var $sendMessage = $("#sendMessage");
    //                $sendMessage.css("display", "none");
    //
    //                var $sendingMessage = $("#sendingMessage");
    //                $sendingMessage.css("display","block");
    //                $sendingMessage.css("background-image", "url(load.gif)");
    //
    //                var object = {};
    //
    //                object.recipient = $recipient;
    //                object.theme = $theme;
    //                object.content = $content;
    //
    //                $.ajax({
    //                    url: "/message/writeForm",
    //                    data: {
    //                        json: JSON.stringify(object)
    //                    },
    //
    //                    success: function(){
    //                        var $resultBlock = $("#resultForm");
    //                        var $questionsBlock = $("#messageForm");
    //
    //                        $resultBlock.css({display:"block"});
    //                        $questionsBlock.css({display:"none"});
    //                    },
    //
    //                    error: function(){
    //                        console.log("error");
    //                    }
    //                });
    //
    //            } else {
    //                console.log("some is empty");
    //            }
    //
    //
    //
    //// width: 50px;   padding: 0 15px; background-size:cover; background-position: center;  height: 40px;
    //
    //
    //
    //        });
    //    });
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
            <form action="/mail/write" method="POST" id="messageForm" class="tsc_form_contact_light frame tbar">
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
                                <textarea id="content" name="content" class="form-control" rows="8" placeholder="Send something (required)" required></textarea>

                                <input class="blueButton right" id="sendMessage" type="submit" value="Send Message"/>
                                <div  style="display : none; float:right;  margin-top: -10px;" id="sendingMessage"><img src="/res/img/load.gif"></div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </form>
            <div id="resultForm" style="display : none; background-image: url('/res/img/load.gif')">Message sended</div>
        </div>
    </div>
    <!-- body end -->
</div>
</body>
</html>