<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add film</title>
    <script src="js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript">
        $("#submit").click(function(){
            window.close();
            window.open("/film");
        });
    </script>
</head>
<body>
    <div>
        <form action="/film" method="post">
            <input type="number" name="id" value="${film.id}" hidden><br>

            <label for="name">Film name:</label>
            <input type="text" id="name" name="name" value="${film.name}" required><br>

            <label for="year">year:</label>
            <input type="number" id="year" name="year" value="${film.year}" required><br>

            <input type="submit" id="submit" value="Save">
        </form>
    </div>
</body>
</html>
