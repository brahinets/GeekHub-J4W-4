<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>...Number of messages...</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mail.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" type="text/css">
</head>
<body>

    <jsp:include page="../pages/header.jsp" />
    <jsp:include page="topNavbar.jsp" />

    <%--<div id="content" class="clearfix">--%>
        <%--<div id="mailBlock">--%>


            <%--<div id="mailNavi">--%>

                <%--<span ><a href="mail/in">In</a></span>--%>
                <%--<span ><a href="mail/out">Out</a></span>--%>
                <%--<!-- 		<div>--%>
                            <%--<div class="col1"><a href="mail/in">In</a></div>--%>
                            <%--<div class="col2"><a href="mail/out">Out</a></div>--%>
                        <%--</div>		 -->--%>
            <%--</div>--%>


            <%--&lt;%&ndash;<div id="oneMessage">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div id="oneMessage">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div id="oneMessage">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div id="oneMessage">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div id="oneMessage">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

        <%--</div>--%>

    <%--</div>--%>


    <%--&lt;%&ndash;<div id = "mailPart" >&ndash;%&gt;--%>
        <%--&lt;%&ndash;<div id = "mailBar" >&ndash;%&gt;--%>
            <%--&lt;%&ndash;Mail bar&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<div id = "mailList">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:forEach items="${mailList}" var="message">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div id="messageInList">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div id="mailPhoto">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;photo&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;${message.photo}&ndash;%&gt;&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<span id="mailFrom">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;${message.sender}&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<span id="mailContent">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;${message.text}&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<span id="mailActions">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;delete&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>

        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>


<%--&lt;%&ndash;${message}&ndash;%&gt;--%>
<%--&lt;%&ndash;${message1}&ndash;%&gt;--%>
    ${fn:length(mail) > 0}
    <div id="content" class="clearfix">
        <div id="mailNavi">
            <span ><a href="/mail/in">In</a></span>
            <span ><a href="/mail/out">Out</a></span>
            <!-- 		<div>
                        <div class="col1"><a href="mail/in">In</a></div>
                        <div class="col2"><a href="mail/out">Out</a></div>
                    </div>		 -->
        </div>

        <div id="mailBlock">
            <c:choose>
                <c:when test="${action == 'in'}">
                    <c:forEach items="${mailList}" var="mail">
                        <div id="oneMessage">
                            ${mail.text}
                        </div>
                    </c:forEach>
                </c:when>

                <c:when test="${action == 'out'}">
                    <c:forEach items="${mailList}" var="mail">
                        <div id="oneMessage">
                                ${mail.text}
                        </div>
                    </c:forEach>
                </c:when>

                <c:when test="${action == 'write'}">
                    write message form
                </c:when>

                <c:when test="${action == 'read'}">
                    read message id= ${message.theme}
                </c:when>

                <c:otherwise>
                    <div>Otherwise</div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>


</body>
</html>
