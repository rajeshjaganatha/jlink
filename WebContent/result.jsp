<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jlink</title>
</head>
<body>
<form action="newvalues.do" method="post" >

<table cellspacing="1" cellpadding="4" border="1">
<tr>
					<td>Name</td>	 
				 	<td>Value</td>
				 	<td>Type</td>
				 	<td>New Value</td>
</tr>
 				<c:forEach items="${ParametersList}" var="i" varStatus="loop" >
 				<c:choose>
    			<c:when test="${i.type == 0}">
       							<tr>
					<td><c:out value="${i.name}"/></td>	 
				 	<td><c:out value="${i.value}"/></td>
				 	<td><c:out value="String"/></td>
				 	<td><input type="text" name="${i.name}" value="${i.value}" /></td>
				 	</tr>
    			</c:when>
    			    			<c:when test="${i.type == 1}">
       							<tr>
					<td><c:out value="${i.name}"/></td>	 
				 	<td><c:out value="${i.value}"/></td>
				 	<td><c:out value="Integer"/></td>
				 	<td><input type="text" name="${i.name}" value="${i.value}" /></td>
				 	</tr>
    			</c:when>
    			    			<c:when test="${i.type == 2}">
       							<tr>
					<td><c:out value="${i.name}"/></td>	 
				 	<td><c:out value="${i.value}"/></td>
				 	<td><c:out value="Boolean"/></td>
				 	<td><select name="${i.name}" >
					<option value="true">True</option>
					<option value="false">False</option>
					</select></td>
				 	</tr>
    			</c:when>
    			    			<c:when test="${i.type == 3}">
       							<tr>
					<td><c:out value="${i.name}"/></td>	 
				 	<td><c:out value="${i.value}"/></td>
				 	<td><c:out value="Real Number"/></td>
				 	<td><input type="text" name="${i.name}" value="${i.value}" /></td>
				 	</c:when>
    			<c:otherwise>
        			No comment sir...
    			</c:otherwise>
				</c:choose>
			</c:forEach>
			<tr><td style="color:red;font-size: large;" colspan="3">${Error}</td>
						<tr>
				<td>
					<input type="submit" title="Submit" value="SUBMIT" class="button_example"/> 
				</td>
			</tr>
			
						</table>
						</form>
			<a href="file/${NewFilename}">Download File</a>
</body>
</html>