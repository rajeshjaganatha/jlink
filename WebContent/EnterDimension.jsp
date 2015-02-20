<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%@ page import="java.lang.Runtime.*" %>
<%@ page import="java.io.*"%>
<%@ page import="bean.*"%>
<hr>
<%
    out.println(System.getProperty("java.library.path"));
    out.println("<br><br>");
    out.println(System.getProperty("java.class.path"));
%>
<hr>
<%
    System.out.println("jspexample started.");
    
    String[] args = new String[1];
    args[0] = request.getParameter("proe_cmd");
    
    System.out.println("proe_cmd = " + args[0]);
    
    try {
    	

        AsyncInstallTest.main(args);
    } catch (Exception e) {
        out.print(e.getStackTrace());
    }

    System.out.println("jspexample finished.");
%>
<hr>
Done!!
 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.io.*,java.util.*" %>
  <%@ page import="bean.UploadFile" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>J Link</title>
<script type="text/javascript" src="js/common.js"></script> 
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="title">JLink Test</div>

<form action="EnterDimensions.do" method="POST" >
	<fieldset><legend><b>Fields</b></legend>
		<table class="tabledefault" >
			<tr>
				<td style="font-weight: bold;">Enter Values</td>
				<td height="10"></td>
				</tr>
				<tr>
					<td width="100" align="left">Length</td>
					<td width="100" align="left"><input type="text" name="LENGTH" /></td>
	
				</tr>
				<tr>
					<td width="100" align="left">Height</td>
					<td width="100" align="left"><input type="text" name="HEIGHT" /></td>
				</tr>
				
				
 				<c:forEach items="${counts}" var="i" >
				<tr>
					<td><c:out value="${i}"/></td>	
				</tr>
			</c:forEach>
			
<%-- 			<c:forEach begin="0" end="${modelitems.getarraysize()}" varStatus="loop">
    <tr>
    <c:out value = "${modelitems.get[loop.index].GetName()}"/>
    </tr>

 </c:forEach>  
			 --%>
			<tr>
				<td>
					<input type="submit" title="Submit" value="SUBMIT" class="button_example"/> 
				</td>
			</tr>
		</table>
	</fieldset>

		


	
</form>

</body>
</html>