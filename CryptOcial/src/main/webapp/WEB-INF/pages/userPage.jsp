<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${user.name} ${user.surname}</title>
    <script src="<c:url value="/res/js/like.js"/>"></script>
    <script src="<c:url value="/res/js/actions.js"/>"></script>
</head>

<body>

<div class="container">
	<!-- top nav bar start -->
	    <jsp:include page="topNavBar.jsp"/>
	<!-- top nav bar end -->

	<!-- body start -->
	<div class="row">
		<!-- left nav bar start -->
        <jsp:include page="leftNavBar.jsp"/>
		<!-- left nav bar end -->

		<!-- user block start -->
		<div class="full-width" >
			<div class="col-md-3 left" id="userBlockLeft">
				<div id="userPhoto">
                    <c:choose>
                        <c:when test="${user.avatar != null}">
                            <img src="data:image/png;base64,${user.avatar}"/>
                        </c:when>
                        <c:otherwise>
                            <img src="<c:url value="/res/img/noAvatar.png"/>"/>
                        </c:otherwise>
                    </c:choose>
				</div>


                <!-- user actions block start -->
                <c:choose>
                    <c:when test="${myID != user.id}">
                        <div id="userActions">
                                <c:choose>
                                    <c:when test="${user.relation == 'friend'}">
                                        <a class="btn full-width" href="/friend/delete/${user.id}">
                                            <button class="full-width blueButton" type="submit">
                                                <span class="buttonText">Delete friend</span>
                                            </button>
                                        </a>
                                    </c:when>
                                    <c:when test="${user.relation == 'subscriber'}">
                                        <a class="btn full-width" href="/friend/submit/${user.id}">
                                            <button class="full-width blueButton" type="submit">
                                                <span class="buttonText">Submit friend</span>
                                            </button>
                                        </a>
                                    </c:when>
                                    <c:when test="${user.relation == 'subscribed'}">
                                        <a class="btn full-width" href="/subscriber/delete/${user.id}">
                                            <button class="full-width blueButton" type="submit">
                                                <span class="buttonText">Unsubscribe</span>
                                            </button>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn full-width" href="/friend/add/${user.id}">
                                            <button class="full-width blueButton" type="submit">
                                                <span class="buttonText">Add to friends</span>
                                            </button>
                                        </a>
                                    </c:otherwise>
                                </c:choose>

                            <!-- write message button -->
                            <c:choose>
                                <c:when test="${user.canWrite}">
                                    <a class="btn full-width" href="/mail/write/${user.id}">
                                        <button class="full-width blueButton" type="submit">
                                            <span class="buttonText">Write message</span>
                                        </button>
                                    </a>
                                </c:when>
                            </c:choose>

                            <!-- write blacklist button -->
                            <c:choose>
                                <c:when test="${!user.isInMyBlackListWrite}">
                                    <a class="btn full-width" href="/blacklist/write/add/${user.id}">
                                        <button class="full-width blueButton" type="submit">
                                            <span class="buttonText">Add to Write black list </span>
                                        </button>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn full-width" href="/blacklist/write/delete/${user.id}">
                                        <button class="full-width blueButton" type="submit">
                                            <span class="buttonText">Delete whereFrom Write black list</span>
                                        </button>
                                    </a>
                                </c:otherwise>
                            </c:choose>


                            <!-- post blacklist button -->
                            <c:choose>
                                <c:when test="${!user.isInMyBlackListPost}">
                                    <a class="btn full-width" href="/blacklist/post/add/${user.id}">
                                        <button class="full-width blueButton" type="submit">
                                            <span class="buttonText">Add to Post black list </span>
                                        </button>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn full-width" href="/blacklist/post/delete/${user.id}">
                                        <button class="full-width blueButton" type="submit">
                                            <span class="buttonText">Delete whereFrom Post black list</span>
                                        </button>
                                    </a>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:when>
                </c:choose>
				<!-- user actions block end -->

				<!-- user relation block start -->
				<div id="userRelation">
                    <!-- start of my friends widget -->
					<div class="widget">
						<div class="head">
							<span><a href="<c:url value="/friends"/>">Friends</a></span>
							<span class="count">(${fn:length(user.listFriends)})</span>
						</div>

						<div class="boxy">
                            <c:choose>
                                <c:when test="${fn:length(user.listFriends) > 0}">
                                    <c:forEach items="${user.listFriends}" var="human">
                                        <div class="cell">
                                            <div class="widgetImage">
                                                <c:choose>
                                                    <c:when test="${human.avatar != null}">
                                                        <a href="/user/${human.id}"><img src="data:image/png;base64,${human.avatar}"/></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="<c:url value="/res/img/noAvatar.png"/>"/>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                            <div class="widgetText">
                                                <a href="<c:url value="/user/${human.id}"/>">${human.name} ${human.surname}</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>

                                <c:otherwise>
                                    <div class="well noMargin">No friends</div>
                                </c:otherwise>
                            </c:choose>
						</div>
					</div>
                    <!-- end of my friends widget -->

                    <!-- start of my subscribers widget -->
					<div class="widget">
						<div class="head">
							<span><a href="<c:url value="/subscribers"/>">Subscribers</a></span>
							<span class="count">(${fn:length(user.listOfWhoSubscribedOnMe)})</span>
						</div>

                        <div class="boxy">
                            <c:choose>
                                <c:when test="${fn:length(user.listOfWhoSubscribedOnMe) > 0}">
                                    <c:forEach items="${user.listOfWhoSubscribedOnMe}" var="human">
                                        <div class="cell">
                                            <div class="widgetImage">
                                                <c:choose>
                                                    <c:when test="${human.avatar != null}">
                                                        <a href="/user/${human.id}"><img src="data:image/png;base64,${human.avatar}"/></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="<c:url value="/res/img/noAvatar.png"/>"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="widgetText">
                                                <a href="<c:url value="/user/${human.id}"/>">${human.name} ${human.surname}</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>

                                <c:otherwise>
                                    <div class="well noMargin">Not anyone subscribed on you</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
					</div>
                    <!-- end of my subscribers widget -->

                    <!-- start of I subscribed widget -->
					<div class="widget">
						<div class="head">
							<span><a href="<c:url value="/subscribed"/>">Subscribed</a></span>
							<span class="count">(${fn:length(user.listOnWhoIsubscribed)})</span>
						</div>

                        <div class="boxy">
                            <c:choose>
                                <c:when test="${fn:length(user.listOnWhoIsubscribed) > 0}">
                                    <c:forEach items="${user.listOnWhoIsubscribed}" var="human">
                                        <div class="cell">
                                            <div class="widgetImage">
                                                <c:choose>
                                                    <c:when test="${human.avatar != null}">
                                                        <a href="/user/${human.id}"><img src="data:image/png;base64,${human.avatar}"/></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="<c:url value="/res/img/noAvatar.png"/>"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="widgetText">
                                                <a href="<c:url value="/user/${human.id}"/>">${human.name} ${human.surname}</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>

                                <c:otherwise>
                                    <div class="well noMargin">Not subscribed for anyone yet?</div>
                                </c:otherwise>
                            </c:choose>

                        </div>
					</div>
                    <!-- end of I subscribed widget -->

				</div>
				<!-- user relation block end -->
			</div>


			<div class="col-md-6 left" id="userBlockRight">
				<!-- user status start -->
				<div id="simpleTop">
					<div class="left" id="userName">
						${user.name} ${user.surname}
					</div>
					<div class="right online">
                        <c:choose>
                            <c:when test="${user.isOnline}">
                                Online
                            </c:when>
                        </c:choose>
					</div>
				</div>
				<!-- user status end -->

				<!-- user info start -->
				<div id="userInfo">
					<div id="shortInfo">
						<div class="miniblock">
							<div class="label">Name</div>
							<div class="labeled">${user.name}</div>
						</div>
						<div class="miniblock">
							<div class="label">Surname</div>
							<div class="labeled">${user.surname}</div>
						</div>
						<div class="miniblock">
							<div class="label">Gender</div>
                            <c:choose>
                                <c:when test="${user.gender}">
                                    <div class="labeled">Male</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="labeled">Female</div>
                                </c:otherwise>
                            </c:choose>

						</div>
                        <div class="miniblock">
							<div class="label">Birth Date</div>
							<div class="labeled"><fmt:formatDate type="date" value="${user.birthDate}" /></div>
						</div>
					</div>
				</div>
				<!-- user info end -->

				<!-- wall start -->
				<div id="wall">
					<div id="wallHead">
						<span class="count full-height" id="postsCount">${fn:length(user.posts)} </span><span class="notesCount" > posts</span>
						<span class="right">to other posts</span>
					</div>

                    <c:choose>
                        <c:when test="${user.canPost}">
                            <form name="addPost" action="<c:url value="/post/add"/>" method="post">
                                <div id="submitBlock" <%--ondblclick="hidePostButton(addPostButton)"--%>>
                                    <textarea id="addPost" <%--onfocus="showPostButton(addPostButton)" --%> placeholder="What's new?" name="content" required="required"></textarea>
                                    <button id="addPostButton" type="submit" class="blueButton" <%--style="display:none;"--%>>Post</button>
                                    <input type="hidden" value="${user.id}" name="recipient">
                                </div>
                            </form>
                        </c:when>
                    </c:choose>


					<div id="postBlock">
                        <c:forEach items="${user.posts}" var="post">
                            <div id="post${post.id}" class="post">
                                <div class="imgMini">
                                <c:choose>
                                    <c:when test="${user.avatar != null}">
                                        <img src="data:image/png;base64,${post.user.avatar}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value="/res/img/noAvatar.png"/>"/>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${post.user.isOnline}">
                                        <span class="online">Online</span>
                                    </c:when>
                                </c:choose>
                                </div>

                                <div class="postInfo">
                                    <div class="textName">
                                        <a href="/user/${post.user.id}">${post.user.name} ${post.user.surname}</a>
                                    </div>
                                    <div class="text">
                                            ${post.content}
                                    </div>
                                    <div class="replyBlock">
                                        <div class="dateBlock">date</div>
                                        <div class="separator" style="float:left;">|</div>
                                        <c:choose>
                                            <c:when test="${post.user.id == myID}">
                                                <span class="action" onclick="postDelete(${post.id})">Delete</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="action" onclick="postSpam(${post.id}, true)">It spam</span>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="likeBlock" onclick="like(${post.id}, 'post')">
                                        <c:choose>
                                            <c:when test="${post.isLiked}">
                                                <span id="likeBlock${post.id}" style="display: none;">
                                                    Like <span class="entypo-thumbs-up"></span> ${post.likesCount-1}
                                                </span>
                                                <span id="dislikeBlock${post.id}">
                                                    Unlike <span class="entypo-thumbs-down"></span> ${post.likesCount}
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <span id="likeBlock${post.id}">
                                                    Like <span class="entypo-thumbs-up"></span> ${post.likesCount}
                                                </span>
                                                <span id="dislikeBlock${post.id}" style="display: none;">
                                                    Unlike <span class="entypo-thumbs-down"></span> ${post.likesCount+1}
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
					</div>
				</div>
				<!-- wall end -->
			</div>
            <!-- right user block end -->
	  	</div>
	  	<!-- user block end -->
	</div>
	<!-- body end -->

</div>
</body>
</html>