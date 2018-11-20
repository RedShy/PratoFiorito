<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>GAME PAGE</h1>
 <table style="width:20%">
 <c:forEach var = "i" begin = "0" end = "${ game.getSize() - 1}">
 	<tr>
 		 <c:forEach var = "j" begin = "0" end = "${ game.getSize() - 1 }">
 		 	<td><c:out value = "${game.getCell(i,j)}"/></td>
 		 </c:forEach>
 	</tr>
 </c:forEach>
</table> 
</body>
</html>