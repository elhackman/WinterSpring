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

<P>  Time: ${serverTime}. </P>
<P>  Method: ${method} </P>
<P>  Header: ${header} </P>
<P>  Body: ${body} </P>

</body>
</html>
