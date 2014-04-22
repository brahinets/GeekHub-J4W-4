<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>...people count...</title>
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

        <div class="col-md-9 ">
            <div class="container">
                <div class="listMenu col-md-6">
                    <form name="advancedSearch" action="/searchAdvanced" method="get">
                    <input class="form-control" type="search" placeholder="Name | Surname">

                    <c:forEach items="${usersList}" var="human">
                        <div class="row">
                            <div class="col-md-1 listItem listPhoto">
                                <a href="/user/${human.id}">
                                    <c:choose>
                                        <c:when test="${human.avatar != null}">
                                            <img src="data:image/png;base64,${human.avatar}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="<c:url value="/res/img/noAvatar.png"/>"/>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="col-md-2 listItem listShort">
                                <div>${human.name} ${human.surname}</div>
                                <div>${human.whereFrom}</div>
                                <c:choose>
                                    <c:when test="${human.isOnline}">
                                        <div><span class="online">Online</span></div>
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="col-md-1 listItem listAction">
                                <ul class="list-group">
                                    <li class="list-group-item list-group-item-text"><a href="/user/${human.id}">Profile</a></li>

                                    <c:choose>
                                        <c:when test="${myID != human.id}">
                                            <c:choose>
                                                <c:when test="${human.canWrite == true}">
                                                    <li class="list-group-item list-group-item-info"><a href="/mail/write/${human.id}">Wtite message</a></li>
                                                </c:when>
                                            </c:choose>

                                        </c:when>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${myID != human.id}">
                                            <c:choose>
                                                <%--<li>Checkins<strong>344</strong></li>--%>
                                                <c:when test="${human.relation == 'friend'}">
                                                    <li class="list-group-item list-group-item-danger"><a href="/friend/delete/${human.id}">Delete friend</a></li>
                                                </c:when>
                                                <c:when test="${human.relation == 'subscriber'}">
                                                    <li class="list-group-item list-group-item-danger"><a href="/subscriber/delete/${human.id}">Unsubscribe</a></li>
                                                </c:when>
                                                <c:when test="${human.relation == 'subscribed'}">
                                                    <li class="list-group-item list-group-item-warning"><a href="/friend/submit/${human.id}">Submit friend</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="list-group-item list-group-item-success"><a href="/friend/add/${human.id}">Add friend</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>

                                </ul>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
				</div>

				<div class="col-md-3 searchMenu">
					<div class="criteria">
						<label class="control-label">Country</label>
						<select class="form-control">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<hr>

					<div class="criteria">
						<label class="control-label">City</label>
						<select class="form-control">
						  <option>1</option>
						  <option>2</option>
						  <option>3</option>
						  <option>4</option>
						  <option>5</option>
						</select>
					</div>
					<hr>

					<div class="criteria">
						<label class="control-label">Sex</label>
						<ul class="nav nav-pills nav-justified">
  							<li>
  								<div class="radio">
									<label><input type="radio" name="sex" value="any" checked>Any</label>
								</div>
							</li>

  							<li>
  								<div class="radio">
									<label><input type="radio" name="sex" value="male">Male</label>
								</div>
							</li>
  							<li>
  								<div class="radio">
  									<label><input type="radio" name="sex" value="female">Female</label>
								</div>
							</li>
						</ul>
					</div>
					<hr>

					<div class="criteria">
						<label class="control-label">Age</label>
						<ul class="nav nav-pills nav-justified">
							<li>
								<select class="form-control">
									<option>whereFrom</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</li>

							<li>
								<select class="form-control">
									<option>to</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</li>
						</ul>
					</div>
					<hr>

				</div>
			</div>
		</div>
	</div>
	<!-- body end -->

</div>
</body>
</html>