<%--
  Created by IntelliJ IDEA.
  User: Yarik
  Date: 13.04.14
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>CryptOcial</title>
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/global.css"/>" type="text/css">

    <script src="<c:url value="/res/js/jquery-2.1.0.min.js"/>"></script>
    <script src="<c:url value="/res/js/bootstrap.js"/>"></script>
    <script src="<c:url value="/res/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/res/js/jquery.validate.js"/>"></script>

    <script type="text/javascript">
        $(document).ready(function()
        {
            $('#registrationForm').find('input').hover(function()
            {
                $(this).popover('show')
            });

            $("#registrationForm").validate({
                rules:{
                    name : {required:true},
                    surname : {required:true},
                    password:{required:true, minlength: 6},
                    passwordRepeat:{required:true,equalTo: "#password"},
                    email:{required:true,email: true}
                },

                messages:{
                    name:"Enter your first name",
                    surname:"Enter your last name",
                    password:{
                        required:"Enter your password",
                        minlength:"Password must be minimum 6 characters"},
                    passwordRepeat:{
                        required:"Confirm password",
                        equalTo:"Password and Confirm Password must match"},
                    email:{
                        required:"Enter your email address",
                        email:"Enter valid email address"}
                },

                errorClass: "help-inline",
                errorElement: "span",
                highlight:function(element, errorClass, validClass) {
                    $(element).parents('.control-group').addClass('error');
                },
                unhighlight: function(element, errorClass, validClass) {
                    $(element).parents('.control-group').removeClass('error');
                    $(element).parents('.control-group').addClass('success');
                }
            });
        });
    </script>
</head>
<body>
<br><br><br><br>
    <div class="row">
        <div class="col-lg-6 col-sm-offset-3">
            <div class="" id="loginModal">
                <div class="modal-header text-center">
                    <h1 class="slogan"><span class="fontawesome-eye-close icon-left"></span> CryptOcial - We are not NSA</h1>
                </div>
                <div class="modal-body">
                    <div class="well">
                        <ul class="nav nav-tabs nav-justified">
                            <li class="active"><a href="#login" data-toggle="tab"><h4>Login</h4></a></li>
                            <li><a href="#create" data-toggle="tab"><h4>Create Account</h4></a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane active in" id="login">
                                <form class="form-group" action='<c:url value="/login"/>' method="POST" id="loginForm">
                                    <input type="hidden" name="action" value="login"/><br>

                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="text" name="login" placeholder="Login | Email"/>
                                    </div><br>

                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="text" name="password" placeholder="Password"/>
                                    </div>

                                    <hr>
                                    <div class="col-sm-offset-2">
                                        <div class="checkbox">
                                            <label><h4><input type="checkbox" name="rememberMe">Remember Me</h4></label>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <div class="controls input-lg">
                                            <button type="submit" class="btn btn-success 2btn-lg btn-block wHalf center-block">Sign In</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="tab-pane fade" id="create">
                                <form action='<c:url value="/login"/>' class="form-horizontal" id="registrationForm" method='POST'>

                                    <input type="hidden" name="action" value="register"/><br>

                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="text" name="name" placeholder="Name"/>
                                    </div><br>

                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="text" name="surname" placeholder="Surname"/>
                                    </div><br>

                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="text" name="email" placeholder="Email"/>
                                    </div><br>

                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="password" id="password" name="password" placeholder="Password"/>
                                    </div><br>
                                    <div class="control-group ">
                                        <input class="form-control input-lg" type="password" id="passwordRepeat" name="passwordRepeat" placeholder="Repeat Password"/>
                                    </div>

                                    <hr>
                                    <div class="col-sm-offset-2">
                                        <div class="checkbox">
                                            <label><h4><input type="checkbox" name="autoSignIn">Sign In after</h4></label>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <div class="controls input-lg">
                                            <button type="submit" class="btn btn-info btn-lg btn-block wHalf center-block">Sign Up</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
