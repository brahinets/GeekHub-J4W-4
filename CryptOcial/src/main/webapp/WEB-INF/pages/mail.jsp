<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Mail</title>
    <script src="<c:url value="/res/js/DateUtils.js"/>"></script>
    <script src="<c:url value="/res/js/mail.js"/>"></script>
    <script src="<c:url value="/res/js/actions.js"/>"></script>
    <script>
        function getInputMail() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                dataType: "json",
                url: "/mail/in",

                success: function (mailList) {
                    var list = "";
                    for (var i = 0; i < mailList.length; i++) {
                        list += prepareOneRowInMailList(mailList[i], "'input'");
                    }
                    $("#inputCount").text('(' + mailList.length + ')');
                    $("#mail").empty();
                    $("#mail").append(list);
                },
                error: function (error) {
                    alert("error : " + error[0].id);
                }
            });
        }

        function getOutputMail() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                dataType: "json",
                url: "/mail/out",

                success: function (mailList) {
                    var list = "";
                    for (var i = 0; i < mailList.length; i++) {
                        list += prepareOneRowInMailList(mailList[i], "'output'");
                    }

                    $("#outputCount").text('(' + mailList.length + ')');
                    $("#mail").empty();
                    $("#mail").append(list);
                },
                error: function (error) {
                    alert("error : " + error[0].id);
                }
            });
        }
    </script>
</head>

<body onload="getInputMail()">
<div class="container">

    <!-- top nav bar start -->
    <jsp:include page="topNavBar.jsp"/>
    <!-- top nav bar end -->

    <!-- body start -->
    <div class="row">
        <!-- left nav bar start -->
        <jsp:include page="leftNavBar.jsp"/>
        <!-- left nav bar end -->

        <div class="col-md-9">
            <div>
                <ul class="nav nav-tabs nav-justified">
                    <li class="active" id="inputNav">
                        <a onclick="getInputMail()" data-toggle="tab">
                            <h4>Input <span id="inputCount" class="number"></span></h4>
                        </a>
                    </li>

                    <li id="outputNav">
                        <a onclick="getOutputMail()"  data-toggle="tab">
                            <h4>Output <span id="outputCount" class="number"></span></h4>
                        </a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in listMenu full-width padding">
                        <div id="mail"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- body end -->
</div>
</body>
</html>