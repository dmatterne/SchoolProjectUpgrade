<%
String module = request.getParameter("module") ;
String function = request.getParameter("function") ;

if (function == null)
	function = "index" ;

if (module != null)
{
	System.out.println("modules/" + module + "/" + function + ".jsp" );
	pageContext.forward("modules/" + module + "/" + function + ".jsp" ) ;
}
else
{ 
	%>
	<%@ page contentType="text/html" %>
	<jsp:useBean id="staticPage" class="beans.StaticPage" >
		<jsp:setProperty name="staticPage" property="user_id" value='<%=session.getAttribute("userId")%>'/>
	</jsp:useBean>
	
	<% 
		staticPage.authorizeAll();
		staticPage.setTitel("Start Page");
		String content = "<H2>Welcome</h2>"
							+ "<div align=\"center\"><img src=\"images/groepsfoto.jpg\" align=\"absmiddle\" >";
	
	%>
	</div>
	
	<%=staticPage.wrapDesign(content)%>
	<%
}
%>