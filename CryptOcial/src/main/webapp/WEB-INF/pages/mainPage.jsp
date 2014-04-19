<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>...Number of messages...</title>
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
		<div class="full" >
			<div class="col-md-3 left" id="userBlockLeft">
				<div id="userPhoto">
					<img src="<c:url value="/res/img/user_avatar_s.jpg"/>"/>
				</div>
				
				<!-- user actions block start -->
				<div id="userActions">
					<div class="blueButton">
						<span class="typicons-edit buttonIcon"></span>
						<span class="buttonText">Write message</span>
					</div>
					
					<div class="blueButton">
						<span class="typicons-plus buttonIcon"></span>
						<span class="buttonText">Add to friends</span>
					</div>
					
					<div class="blueButton">
						<span class="typicons-plus buttonIcon"></span>
						<span class="buttonText">Add to friends</span>
					</div>
					
					<div class="blueButton">
						<span class="typicons-minus buttonIcon"></span>
						<span class="buttonText">Delete friend</span>
					</div>
					
					<div class="blueButton">
						<span class="typicons-times buttonIcon"></span>
						<span class="buttonText">Unsubscribe</span>
					</div>
				</div>
				<!-- user actions block end -->	
				
				<!-- user relation block start -->	
				<div id="userRelation">
					<div class="widget">
						<div class="head">
							<span><a href="/friends">Friends</a></span>
							<span class="count">(1234)</span>
						</div>

						<div class="boxy">
						   <div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>									
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
						</div>
					</div>

					<div class="widget">
						<div class="head">
							<span><a href="/friends">Friends</a></span>
							<span class="count">(1234)</span>
						</div>

						<div class="boxy">
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
						   <div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>									
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
						</div>
					</div>

					<div class="widget">
						<div class="head">
							<span><a href="/friends">Friends</a></span>
							<span class="count">(1234)</span>
						</div>

						<div class="boxy">
						   <div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
							<div class="cell">
								<div class="widgetImage">
									<a href="#"><img src="<c:url value="/res/img/mini.jpg"/>" alt="name surname"/></a>
								</div>									
								<div class="widgetText">
									<a href="/user/id">name surname</a>
								</div>
							</div>
						</div>
					</div>
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
						Online
					</div>
				</div>
				<!-- user status end -->	

				<!-- user info start -->	
				<div id="userInfo">
					<div id="shortInfo">
						<div class="miniblock">
							<div class="label">name</div>
							<div class="labeled">yarik</div>
						</div>
						<div class="miniblock">
							<div class="label">name</div>
							<div class="labeled">yarik</div>
						</div>
						<div class="miniblock">
							<div class="label">name</div>
							<div class="labeled">yarik</div>
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
					
					<div id="submitBlock" ondblclick="hidePostButton(addPostButton)">
						<textarea id="addPost" onfocus="showPostButton(addPostButton)" placeholder="What's new?"></textarea>
						<button id="addPostButton" class="blueButton" style="display:none;">Post</button>
					</div>
					
					<div id="postBlock">
						<div id="post1" class="post">
							<div class="postImage">
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