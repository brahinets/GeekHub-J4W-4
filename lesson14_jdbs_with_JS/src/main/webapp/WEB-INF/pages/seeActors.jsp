<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actors</title>
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/actor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            getListOfActors();
        });
    </script>
</head>
<body>
    <div>
        <div>Actors</div><br>
        <button onclick="window.open('/film');">Films list</button>
        <button onclick="window.open('/actor/add');">Add Actor</button>
        <div id="actorsList"></div>
    </div>
</body>
</html>