<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>${user.name} ${user.surname}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css" type="text/css">
</head>

<body>

    <jsp:include page="../pages/header.jsp" />
    <jsp:include page="topNavbar.jsp" />




    <div id="content" >
    <div id="userBlock">
        <div id="userBlockLeft">

            <div id="userPhoto">
                <img src="img/user_avatar.jpg"/>
            </div>


            <c:choose>
                <c:when test="${myID == user.id}">

                </c:when>

                <c:otherwise>
                    <c:choose>
                        <%--<li>Checkins<strong>344</strong></li>--%>
                        <c:when test="${relation == 'friend'}">
                            <button><a href="/friend/delete/${user.id}"><li >Delete friend</li></a></button>
                        </c:when>
                        <c:when test="${relation == 'subscriber'}">
                            <button><a href="/subscriber/delete/${user.id}"><li >Unsubscribe</li></a></button>
                        </c:when>
                        <c:otherwise>
                            <button> <a href="/subscriber/add/${user.id}"><li >Add to friends</li></a></button>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${canWrite == true}">
                            <button><a href="/mail/write/${user.id}">WriteMessage</a></button>
                        </c:when>
                        <c:otherwise>
                            <button>Cant write</button>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>





            <div id="userRelation">
                <div class="widget">
                    <div class="head">
                        <span><a href="/friends">Friends</a></span>
                    </div>

                    <div class="boxy">
                        <div class="friendList">
                            <c:forEach items="${user.listFriends}" var="friend">
                                <div class="friend">
                                    <a href="#">
                                        <img src="img/friend_avatar_default.jpg" width="30" height="30" alt="${friend.name}"/>
                                    </a>
                                    <span class="friendly">
                                        <a href="/user/${friend.id}">
                                            ${friend.name} ${friend.surname}
                                        </a>
                                    </span>
                                </div>
                            </c:forEach>



                        </div>
                    </div>
                </div>


                <div class="widget">
                    <div class="head">
                        <span><a href="">My Subscribers</a></span>
                    </div>

                    <div class="boxy">
                        <div class="friendList">
                            <c:forEach items="${user.listOfWhoSubscribedOnMe}" var="man">
                                <div class="friend">
                                    <a href="#">
                                        <img src="img/friend_avatar_default.jpg" width="30" height="30" alt="${man.name}"/>
                                    </a>
                                    <span class="friendly">
                                        <a href="/user/${man.id}">
                                                ${man.name} ${man.surname}
                                        </a>
                                    </span>
                                </div>
                            </c:forEach>



                        </div>
                    </div>
                </div>


                <div class="widget">
                    <div class="head">
                        <span><a href="/friends">I am Subscribed</a></span>
                    </div>

                    <div class="boxy">
                        <div class="friendList">
                            <c:forEach items="${user.listOnWhoIsubscribed}" var="man">
                                <div class="friend">
                                    <a href="#">
                                        <img src="img/friend_avatar_default.jpg" width="30" height="30" alt="${man.name}"/>
                                    </a>
                                    <span class="friendly">
                                        <a href="/user/${man.id}">
                                                ${man.name} ${man.surname}
                                        </a>
                                    </span>
                                </div>
                            </c:forEach>



                        </div>
                    </div>
                </div>



            </div>
        </div>

        <div id="userBlockRight">
            asdgfgfdg
        </div>
    </div>
    </div>








		<%--<section id="left">--%>
			<%--<div id="userStats" class="clearfix">--%>
				<%--<div class="pic">--%>
					<%--<a href="#"><img src="img/user_avatar.jpg" width="150" height="150" /></a>--%>
				<%--</div>--%>

				<%--<div class="data">--%>
					<%--<h1>${user.name} ${user.surname}</h1>--%>
					<%--<h3>San Francisco, CA</h3>--%>
					<%--<h4><a href="http://spyrestudios.com/">http://spyrestudios.com/</a></h4>--%>
					<%--<div class="socialMediaLinks">--%>
						<%--<a href="http://twitter.com/jakerocheleau" rel="me" target="_blank"><img src="img/twitter.png" alt="@jakerocheleau" /></a>--%>
						<%--<a href="http://gowalla.com/users/JakeRocheleau" rel="me" target="_blank"><img src="img/gowalla.png" /></a>--%>
					<%--</div>--%>
					<%--<div class="sep"></div>--%>
					<%--<ul class="numbers clearfix">--%>

                        <%--<c:choose>--%>
                            <%--<c:when test="${myID == user.id}">--%>

                            <%--</c:when>--%>

                            <%--<c:otherwise>--%>
                                <%--<c:choose>--%>
                                    <%--&lt;%&ndash;<li>Checkins<strong>344</strong></li>&ndash;%&gt;--%>
                                    <%--<c:when test="${relation == 'friend'}">--%>
                                        <%--<a href="/friend/delete/${user.id}"><li >Delete friend</li></a>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${relation == 'subscriber'}">--%>
                                        <%--<a href="/subscriber/delete/${user.id}"><li >Unsubscribe</li></a>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<a href="/subscriber/add/${user.id}"><li >Add to friends</li></a>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>

                                <%--<c:choose>--%>
                                    <%--<c:when test="${canWrite == true}">--%>
                                        <%--<a href="/mail/write/${user.id}"><li class="nobrdr">WriteMessage</li></a>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<li class="nobrdr">Cant write</li>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>

                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>





					<%--</ul>--%>
				<%--</div>--%>
			<%--</div>--%>

			<%--<h1>About Me:</h1>--%>
			<%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
		<%--</section>--%>

		<%--<section id="right">--%>
			<%--&lt;%&ndash;<div class="gcontent">&ndash;%&gt;--%>
				<%--&lt;%&ndash;<div class="head"><h1>Badges(3)</h1></div>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<div class="boxy">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<p>Keep working to unlock badges!</p>&ndash;%&gt;--%>
					<%--&lt;%&ndash;&ndash;%&gt;--%>
					<%--&lt;%&ndash;<div class="badgeCount">&ndash;%&gt;--%>
						<%--&lt;%&ndash;<a href="#"><img src="img/foursquare-badge.png" /></a>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<a href="#"><img src="img/foursquare-badge.png" /></a>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<a href="#"><img src="img/foursquare-badge.png" /></a>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
					<%--&lt;%&ndash;&ndash;%&gt;--%>
					<%--&lt;%&ndash;<span><a href="#">See all�</a></span>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
			<%--&lt;%&ndash;</div>&ndash;%&gt;--%>

			<%--<div class="gcontent">--%>
                <%--&lt;%&ndash;<c:set var="count" value="${6}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:if test="${user.countFriends < 6}">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<c:set var="count" value="${user.countFriends}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<c:forEach begin="0" end="${count}" varStatus="friend">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="friend">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Jerry K." />&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<span class="friendly">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<a href="/user/${u}">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;Jerry K.&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>




                <%--<div class="head"><h1><a href="#">Friends...</a></h1></div>--%>
				<%--<div class="boxy">--%>

					<%--<div class="friendslist clearfix">--%>

                        <%--<c:forEach items="${user.listFriends}" var="friend">--%>
                            <%--<div class="friend">--%>
                                <%--<a href="#">--%>
                                    <%--<img src="img/friend_avatar_default.jpg" width="30" height="30" alt="${friend.name}"/>--%>
                                <%--</a>--%>
                                <%--<span class="friendly">--%>
                                    <%--<a href="/user/${friend.id}">--%>
                                            <%--${friend.name} ${friend.surname}--%>
                                    <%--</a>--%>
                                <%--</span>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
						<%--&lt;%&ndash;<div class="friend">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Katie F." /></a><span class="friendly"><a href="#">Katie F.</a></span>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
						<%--&lt;%&ndash;&ndash;%&gt;--%>
						<%--&lt;%&ndash;<div class="friend">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Ash K." /></a><span class="friendly"><a href="#">Ash K.</a></span>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
						<%--&lt;%&ndash;&ndash;%&gt;--%>
						<%--&lt;%&ndash;<div class="friend">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Sponge B." /></a><span class="friendly"><a href="#">Sponge B.</a></span>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
						<%--&lt;%&ndash;&ndash;%&gt;--%>
						<%--&lt;%&ndash;<div class="friend">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Frank" /></a><span class="friendly"><a href="#">Frank</a></span>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
						<%--&lt;%&ndash;&ndash;%&gt;--%>
						<%--&lt;%&ndash;<div class="friend">&ndash;%&gt;--%>
							<%--&lt;%&ndash;<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Alexa S." /></a><span class="friendly"><a href="#">Alexa S.</a></span>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

            <%--<div class="gcontent">--%>
                <%--<div class="head"><h1>Groups</h1></div>--%>
                <%--<div class="boxy">--%>
                    <%--<p>Groups</p>--%>

                    <%--<div class="friendslist clearfix">--%>
                        <%--<div class="friend">--%>
                            <%--<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Jerry K." /></a><span class="friendly"><a href="#">Jerry K.</a></span>--%>
                        <%--</div>--%>

                        <%--<div class="friend">--%>
                            <%--<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Katie F." /></a><span class="friendly"><a href="#">Katie F.</a></span>--%>
                        <%--</div>--%>

                        <%--<div class="friend">--%>
                            <%--<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Ash K." /></a><span class="friendly"><a href="#">Ash K.</a></span>--%>
                        <%--</div>--%>

                        <%--<div class="friend">--%>
                            <%--<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Sponge B." /></a><span class="friendly"><a href="#">Sponge B.</a></span>--%>
                        <%--</div>--%>

                        <%--<div class="friend">--%>
                            <%--<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Frank" /></a><span class="friendly"><a href="#">Frank</a></span>--%>
                        <%--</div>--%>

                        <%--<div class="friend">--%>
                            <%--<a href="#"><img src="img/friend_avatar_default.jpg" width="30" height="30" alt="Alexa S." /></a><span class="friendly"><a href="#">Alexa S.</a></span>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<span><a href="#">See all...</a></span>--%>
                <%--</div>--%>
            <%--</div>--%>
		<%--</section>--%>
	<%--</div>--%>
</body>
</html>