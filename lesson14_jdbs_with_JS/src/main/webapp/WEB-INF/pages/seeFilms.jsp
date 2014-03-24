<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Films</title>
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/film.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            getListOfFilms();
        });
    </script>
</head>
<body>
    <div>
        <div>Films</div><br>
        <button onclick="window.open('/actor');">Actors list</button>
        <button onclick="window.open('/film/add');">Add Film</button>
        <div id="filmsList"></div>
    </div>
</body>
</html>
