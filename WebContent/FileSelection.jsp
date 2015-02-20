<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<script type='text/javascript'> 
function fileselection(select){
	var selection = select.name;
	alert("Do you want to open: "+selection+"?");
	return selection;
	document.forms[0].action="openfile.do";
	document.forms[0].submit();
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jlink</title>
</head>
<body>
<form action="openfile.do" method="post" >
<table cellspacing="1" cellpadding="4" border="1">
<tr>
<td>Part/Assembly Names</td>
<td>Select the file to be opened</td>
</tr>


 				<c:forEach items="${FileList}" var="i" varStatus="loop" >
 				<%-- <a onclick='javascript:fileselection(this)' href="<s:url action="openfile.do"/>" id="i" name="${i}">${i}</a>
 				<a href="<s:url action="openfile.do"/>">${i}</a> --%>
				<tr>
				<td>${i}</td>
				<td><input type="radio" name="action" id="${i}" value="${i}"/></td>
				</tr>				
 				</c:forEach>
 				
 				</table>
<input type="submit" title="Submit" value="SUBMIT" class="button_example"/> 
						</form>

</body>
</html>