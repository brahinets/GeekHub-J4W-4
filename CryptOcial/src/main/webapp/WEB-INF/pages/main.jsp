<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        /*@import url(http://weloveiconfonts.com/api/?family=entypo);*/

        /* entypo */
        [class*="entypo-"]:before {
            font-family: 'entypo', sans-serif;
        }

        ::selection {background:transparent;color:#0e76a8;}

        a{
            color: #FFFFFF;
            text-decoration: none;
        }

        body {
            text-align:center;
            /*background:url(http://developer.jmbarcelon.com/Images/blueblurlights.jpg)top center;*/
        }

        p {
            margin:40;
            color:transparent;
            font-size:5em;
            text-shadow: 1px 3px 6px #EFFDFE,
            0px 0px 0px #888,
            1px 4px 6px #EFFDFE;
        }
        #widget {
            background:#eee;
            color:#fff;
            width:300px;
            height:300px;
            border:5px solid #eee;
            -webkit-border-radius: 200px;
            border-radius: 200px;
            box-shadow:0px 0px 15px #444;
            position:absolute;
            left:0;
            right:0;
            margin:0px auto;
            z-index:2;
            overflow:hidden;
        }
        .left {
            float:left;
        }

        .right {
            float:right;
        }

        #right-top:hover, #left-top:hover, #left-center:hover, #right-center:hover, #bottom:hover {
            color:rgba(255,255,255,0.7);
            cursor:pointer;
        }

        #center:hover {
            color:rgba(255,255,0,0.1);
            cursor:pointer;
        }

        #center {
            padding-top:5%;
            color:rgba(154, 226, 137, 0.8);
            width:47%;
            height:42%;
            background:#ccc;
            border:5px solid #eee;
            -webkit-border-radius: 100px;
            border-radius: 100px;
            box-shadow:inset 0px 0px 105px #999;
            position:absolute;
            left:0;
            right:0;
            margin:75px auto;
            z-index:2;
            overflow:hidden;
            font-size:9em;
            text-shadow:0px 0px 15px #999;
        }

        #left-top {
            color:rgba(154, 226, 137, 0.8);
            background:#1368EB;
            -webkit-border-radius:202px 0px 0px 0px;
            border-radius:202px 0px 0px 0px;
            padding-top:10%;
            padding-bottom:10%;
            font-size:3.5em;
            margin-bottom:1%;
            width:49%;
            height:15%;
            overflow:hidden;
            float: left;
        }
        #right-top{
            color:rgba(154, 226, 137, 0.8);
            background: #D3402A;
            -webkit-border-radius:0px 202px 0px 0px;
            border-radius:0px 202px 0px 0px;
            padding-top:10%;
            padding-bottom:10%;
            font-size:3.5em;
            margin-bottom:1%;
            width:49%;
            height:15%;
            overflow:hidden;
            float: right;
        }
        #right-center{
            color:rgba(154, 226, 137, 0.8);
            background: #FFBB02;
            -webkit-border-radius:10px 0px 0px 0px;
            border-radius:10px 0px 0px 0px;
            padding-top:10%;
            padding-bottom:10%;
            font-size:3.5em;
            margin-bottom:1%;
            width:49%;
            height:15%;
            overflow:hidden;
            float: right;
        }
        #left-center{
            color:rgba(154, 226, 137, 0.8);
            background: #009C58;
            -webkit-border-radius:10px 0px 0px 0px;
            border-radius:10px 0px 0px 0px;
            padding-top:10%;
            padding-bottom:10%;
            font-size:3.5em;
            margin-bottom:1%;
            width:49%;
            height:15%;
            overflow:hidden;
            float: left;
        }
        #bottom{
            color: rgba(154, 226, 137, 0.8);
            background: #14e2d5;
            -webkit-border-radius:10px 0px 0px 0px;
            border-radius:10px 0px 0px 0px;
            padding-top:5%;
            font-size:3.5em;
            margin-bottom:1%;
            width:100%;
            height: 30%;
            overflow:hidden;
        }



    </style>
</head>
<body>

<p>${user.name}</p>

<div id="widget">
    <a href="user"><div id="center"><span class="entypo-user"></span></div></a>

    <a href=""><div id="left-top" class="size left"><span class="entypo-keyboard" style="margin-left:20%;" ></span></div></a>

    <a href="mail"><div id="right-top"><span class="entypo-search" style="margin-right:20%;" ></span></div></a>

    <a href=""><div id="left-center"><span class="entypo-user-add" style="margin-right:40%;" ></span></div></a>

    <a href=""><div id="right-center"><span class="entypo-users" style="margin-left:40%;" ></span></div></a>

    <a href="logout"><div id="bottom"><span class="entypo-key"></span></div></a>
</div>

</body>
</html>
