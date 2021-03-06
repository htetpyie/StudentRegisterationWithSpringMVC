<% if(request.getSession().getAttribute("isLogin") != null &&request.getSession().getAttribute("isLogin").equals("Okay") )
		request.getRequestDispatcher("MNU001.jsp").forward(request, response);
	%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/test.css" var="testCss"/>
<link href="${testCss}" rel="stylesheet">
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${error}</p>
          </div>
        </div>
        <form class="login-form" action="/StudentManagementWithSpringMVC/Login" method="post" name="confirm">
          <input type="text" name="id" placeholder="User ID" value="" autofocus />
          <input type="password" name="password" value="" placeholder="Password" />
          <button>login</button>
          <p class="message">Not registered? <a href="/StudentManagementWithSpringMVC/displayUserRegister">Create an account</a></p>
        </form>
      </div>
    </div>
</body>

</html>