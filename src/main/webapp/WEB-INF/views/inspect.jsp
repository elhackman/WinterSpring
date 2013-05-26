<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${welcomeMessage}
</h1>

<P>  Last 10 requests. </P>
<table border="1"><tr><td>TIME</td><td>METHOD</td><td>HEADER</td><td>BODY</td></tr> ${lastreq} </table>

</body>
</html>
