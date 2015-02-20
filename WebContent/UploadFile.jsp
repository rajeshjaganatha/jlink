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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function changeFiletypeselection(){
				var fileTypes = document.getElementById("fileType").value;
				if(fileTypes != "part"){
					document.getElementById("file").accept = ".zip";
					return true;
				} else{
					document.getElementById("file").accept = ".prt.*";
					return true;
				}
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>J Link</title>
<script type="text/javascript" src="js/common.js"></script> 
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="title">Upload File to Change Parameters</div>
<br>

        <form method="post" action="upload.do" enctype="multipart/form-data">
        <table cellspacing="1" cellpadding="4" border="1">
        <tr>
        <td>Type of File (Assembly/Part):</td>
        <td><select id="fileType" name="fileType" onchange="changeFiletypeselection();">
					<option value="part">Part</option>
					<option value="assembly">Assembly</option>
					<option value="drawing">Drawing</option>
		</select></td>
		</tr>

		<tr>
		<td>Choose File:</td>
        <td><input type="file" name="file" id="file" accept = ".prt.*"/></td> 
        </tr>
        </table>
        <br>
        <input type="submit" value="Upload" name="upload" id="upload"/>
        
        
        </form>

</body>
</html>