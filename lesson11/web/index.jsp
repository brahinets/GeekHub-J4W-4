<%--
  Created by IntelliJ IDEA.
  User: Yarik
  Date: 17.02.14
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Translator</title>
    </head>

    <body>
        <form method="POST" action="/translator">
            <div>
                <span><textarea name="textToTranslate" required>${textToTranslate}</textarea ></span>
                <span><input type="submit" name="translate" value="Translate"></span>
            </div>
        </form>
        <div>
            <span><textarea>${translatedText}</textarea ></span>
        </div>
    </body>
</html>
