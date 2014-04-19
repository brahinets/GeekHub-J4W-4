<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${user.name} ${user.surname}</title>
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/global.css"/>" type="text/css">
	<link rel="stylesheet" href="<c:url value="/res/css/user.css"/>" type="text/css">
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


                <c:choose>
                    <c:when test="${myID != user.id}">
                        <div id="userActions">
                            <!-- ADD|DELETE relation button -->
                            <button class="blueButton full-width" >
                                <c:choose>
                                    <%--<li>Checkins<strong>344</strong></li>--%>
                                    <c:when test="${user.relation == 'friend'}">
                                        <span class="typicons-minus buttonIcon"></span>
                                        <span class="buttonText"><a href="/friend/delete/${user.id}">Delete friend</a></span>

                                    </c:when>
                                    <c:when test="${user.relation == 'subscriber'}">
                                        <span class="typicons-minus buttonIcon"></span>
                                        <span class="buttonText"><a href="/friend/submit/${user.id}">Submit friend</a></span>
                                    </c:when>
                                    <c:when test="${user.relation == 'subscribed'}">
                                        <span class="typicons-minus buttonIcon"></span>
                                        <span class="buttonText"><a href="/subscriber/delete/${user.id}">Unsubscribe</a></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="typicons-minus buttonIcon"></span>
                                        <span class="buttonText"><a href="/friend/add/${user.id}">Add to friends</a></span>
                                    </c:otherwise>
                                </c:choose>
                            </button >


                            <!-- write message button -->
                            <c:choose>
                                <c:when test="${user.canWrite}">
                                    <button class="blueButton full-width">
                                        <span class="typicons-minus buttonIcon"></span>
                                        <span class="buttonText"><a href="/mail/write/${user.id}">Write message</a></span>
                                    </button>
                                </c:when>
                            </c:choose>
                        </div>
                    </c:when>
                </c:choose>
				<!-- user actions block start -->



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
                                                    <c:when test="${user.avatar != null}">
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
                                                    <c:when test="${user.avatar != null}">
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
                                                    <c:when test="${user.avatar != null}">
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
							<div class="labeled">gender</div>
						</div>
                        <div class="miniblock">
							<div class="label">Birth Date</div>
							<div class="labeled"><fmt:formatDate type="date" value="${user.birthdate}" /></div>
						</div>
					</div>
				</div>
				<!-- user info end -->	

				<!-- wall start -->	
				<div id="wall">
					<div id="wallHead">
						<span class="notesCount">1234posts</span>
						<span class="right">to other posts</span>
					</div>

                    <c:choose>
                        <c:when test="${user.canPost || myID == user.id}">
                            <div id="submitBlock" ondblclick="hidePostButton(addPostButton)">
                                <textarea id="addPost" onfocus="showPostButton(addPostButton)" placeholder="What's new?"></textarea>
                                <button id="addPostButton" class="blueButton" style="display:none;">Post</button>
                            </div>
                        </c:when>
                    </c:choose>

					
					<div id="postBlock">
						<div id="post1" class="post">
							<div class="imgMini">
								<img src="<c:url value="/res/img/mini.jpg"/>"/>
								<span class="online">Online</span>
							</div>
							<div class="postInfo">
								<div class="textName">
									<a href="asdsf">Yarik</a>
								</div>
								<div class="text">
									sadfgs sadfgs sadfgs sadfgs 									sadfgs sadfgs sadfgs sadfgs 									sadfgs sadfgs sadfgs sadfgs 		sadfgs sadfgs sadfgs sadfgs 									sadfgs sadfgs sadfgs sadfgs 									sadfgs sadfgs sadfgs sadfgs 		sadfgs sadfgs sadfgs sadfgs 									sadfgs sadfgs sadfgs sadfgs 							
								</div>
								<div class="replyBlock">
									<div class="dateBlock">date</div>
									<div class="separator" style="float:left;">|</div>
									<div class="commentLink">comment link</div>
									<div class="likeBlock">like block</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- wall end -->									
				

			</div>
	  	</div>
	  	<!-- user block end -->
	</div>
	<!-- body end -->

</div>
</body>
</html>